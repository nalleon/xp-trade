import {
  View,
  Text,
  Image,
  FlatList,
  TextInput,
  TouchableOpacity,
  KeyboardAvoidingView,
  Platform,
  ScrollView,
  StyleSheet,
} from 'react-native';
import React, { useContext, useEffect, useState } from 'react';
import { HomeStackParamList } from '../navigations/stack/PostStackNav';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AppContext } from '../context/AppContext';
import Icon from 'react-native-vector-icons/Ionicons';
import CommentButton from '../components/comment.models/CommentButton';
import CreatePostModal from '../components/post.modals/CreatePostModal';
import CreateCommentModal from '../components/comment.models/CreateCommentModal';
import UseApi from '../hooks/UseApi';
import { Alert } from 'react-native';
import UpdatePostModal from '../components/post.modals/UpdatePostModal';
import UpdateCommentModal from '../components/comment.models/UpdateCommentModal';

type Props = NativeStackScreenProps<HomeStackParamList, 'PostScreen'>;

const PostScreen = ({ navigation }: Props) => {
  const { setCurrentPost, currentPost, currentComment, setCurrentComment, username } = useContext(AppContext);
  const [comments, setComments] = useState([]);
  const [showCommentPostModal, setShowCommentPostModal] = useState(false);
  const [showPostUpdateModal, setShowPostUpdateModal] = useState(false);
  const [showCommentUpdateModal, setShowCommentUpdateModal] = useState(false);
  const [editingComment, setEditingComment] = useState(null);
  const { handleGetPostsComments, handleDeletePost, handleDeleteComment, handleGetPostById } = UseApi();

  const isOwner = currentPost.user.username === username;

  useEffect(() => {
    const interval = setInterval(() => {
      getComments();
    }, 5000);

    return () => clearInterval(interval);
  }, []);


  const getComments = async () => {
    const result = await handleGetPostsComments(currentPost.id);

    if (result != null) {
      setComments(result);
    }
  }



  const deletePost = async (id: number) => {
    if (!id) {
      return;
    }

    const result = await handleDeletePost(id);

    if (result != null) {
      navigation.replace("HomeScreen");
    }

  };

  const deleteComment = async (id: number) => {
    if (!id) {
      return;
    }

    const result = await handleDeleteComment(id);

    if (result != null) {
      getComments();
    }

  };


  const handleOptionsPost = () => {
    Alert.alert(
      '',
      '',
      [
        { text: 'Editar', onPress: () => setShowPostUpdateModal(true) },
        { text: 'Eliminar', onPress: () => deletePost(currentPost.id), style: 'destructive' },
        { text: 'Cancelar', style: 'cancel' },
      ],
      { cancelable: true }
    );
  };



  const handleOptionsComment = (comment) => {
    Alert.alert(
      '',
      '',
      [
        { text: 'Editar', onPress: () => handleStartEditingComment(comment) },
        { text: 'Eliminar', onPress: () => deleteComment(comment?.id), style: 'destructive' },
        { text: 'Cancelar', style: 'cancel' },
      ],
      { cancelable: true }
    );
  };

  const handleStartEditingComment = async (comment) =>{
    setCurrentComment(comment);
    setShowCommentUpdateModal(true);
  }

  const formatDate = (isoString: string) => {
    const date = new Date(isoString);
    return date.toLocaleString('en-GB', {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit',
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit',
      hour12: false,
    }).replace(',', '');
  };

  const renderComment = ({ item }) => {
    const isCommentOwner = item.user.username === username;

    return (
      <View className="bg-[#1E222A] rounded-tl-xl rounded-br-xl px-4 py-3 mt-2 mx-4" key={item.id}>
        <View className="flex-row items-start justify-between">
          <View className="flex-row">
            <Image
              source={
                item.user.profilePicture
                  ? { uri: item.user.profilePicture }
                  : require('../resources/xp-trade.png')
              }
              className="w-8 h-8 rounded-full mr-3 mt-1"
            />
            <View>
              <Text className="text-[#F6F7F7] font-semibold text-sm">
                @{item.user.username}
              </Text>

            </View>
          </View>

          {isCommentOwner && (
            <TouchableOpacity onPress={() => handleOptionsComment(item)}>
              <Icon
                name="ellipsis-vertical-outline"
                size={18}
                color="#F6F7F7"
              />
            </TouchableOpacity>
          )}

        </View>
        <View className="h-px bg-[#2C3038] mb-3" />

        <Text className="text-[#D1D5DB] text-sm mt-1">
          {item.content}
        </Text>
        <View className="flex-row items-end mt-2">
          <Text className="text-xs text-[#8899A6] ml-auto">
            {formatDate(item.creationDate)}
          </Text>
        </View>
      </View>
    );
  };


  const updatePost = async () => {
    const id = currentPost.id;
    const updatedPost = await handleGetPostById(id);
    setCurrentPost(updatedPost);
    setShowPostUpdateModal(false);
  }


  const updateComment = async () => {
    getComments();
    setShowCommentUpdateModal(false);
  }



  return (
    <KeyboardAvoidingView
      style={{ flex: 1 }}
      behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
    >
      <ScrollView
        className="flex-1 bg-[#0F1218]"
        contentContainerStyle={{ paddingBottom: 100 }}
      >
        <View className="bg-[#1E222A] rounded-tl-xl rounded-br-xl p-4 mb-4 mx-4 mt-4">
          <View className="flex-row items-center justify-between mb-2">
            <View className="flex-row items-center">
              <Image
                source={
                  currentPost.user.profilePicture
                    ? { uri: currentPost.user.profilePicture }
                    : require('../resources/xp-trade.png')
                }
                className="w-10 h-10 mr-3"
              />
              <View>
                <Text className="text-[#F6F7F7] font-semibold">@{currentPost.user.username}</Text>
              </View>
            </View>

            {isOwner && (
              <TouchableOpacity onPress={handleOptionsPost}>
                <Icon
                  name="ellipsis-vertical-outline"
                  size={18}
                  color="#F6F7F7"
                />
              </TouchableOpacity>
            )}
          </View>


          <View className="h-px bg-[#2C3038] mb-6" />

          <TouchableOpacity
            className="flex-row items-center bg-[#2C3038] rounded-tr-xl rounded-bl-xl p-2 mb-4"
          >
            <Image
              source={{ uri: currentPost.game.coverArt }}
              className="w-8 h-8 rounded-md mr-3"
            />
            <View className="flex-1">
              <Text className="text-[#F6F7F7] font-semibold text-sm">{currentPost.game.title}</Text>
              <Text className="text-[#9D8D6A] text-xs">Ver detalles del juego</Text>
            </View>
            <Icon name='chevron-forward' size={15} color={'#8899A6'} />

          </TouchableOpacity>

          <Text
            className="text-[#F6F7F7] mb-2"
            style={{
              lineHeight: 20
            }}
          >
            {currentPost.content}
          </Text>

          {currentPost.picture && (
            <Image
              source={{ uri: currentPost.picture }}
              className="w-full rounded-md mb-2"
              resizeMode="contain"
            />
          )}

          <View className="flex-row items-end mt-2">
            <Text className="text-xs text-[#8899A6] ml-auto">
              {formatDate(currentPost.creationDate)}
            </Text>
          </View>
        </View>

        {comments.map((item) => renderComment({ item }))}

      </ScrollView>

      <CreateCommentModal
        visible={showCommentPostModal}
        onClose={() => setShowCommentPostModal(false)}
      />

      <UpdatePostModal
        visible={showPostUpdateModal}
        post={currentPost}
        onClose={() => updatePost()}
      />

      <UpdateCommentModal
        visible={showCommentUpdateModal}
        comment={currentComment}
        onClose={() => updateComment()}
      />

      <CommentButton onPress={() => setShowCommentPostModal(true)} />
    </KeyboardAvoidingView>
  );
};

export default PostScreen;

const styles = StyleSheet.create({});
