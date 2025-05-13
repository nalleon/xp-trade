import { Image, StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useState } from 'react'
import PostButton from '../components/PostButton'
import { FlatList } from 'react-native-gesture-handler'
import { AppContext } from '../context/AppContext'
import { NativeStackScreenProps } from '@react-navigation/native-stack'
import { GameStackParamList } from '../navigations/stack/GameStackNav'
import Icon from 'react-native-vector-icons/Ionicons';
import { NotificationStackParamList } from '../navigations/stack/NotificationStackNav'
import CreatePostModal from '../components/CreatePostModal'
import { Comment } from '../utils/TypeUtils'

type Props = NativeStackScreenProps<NotificationStackParamList, 'NotificationScreen'>;

const NotificationScreen = (props: Props) => {
  const [comments, setComments] = useState<Comment[]>([]);

  const context = useContext(AppContext);
const [showPostModal, setShowPostModal] = useState(false);

  const handlePostCreated = (postData: {
    text: string;
    image?: string;
    game: any;
  }) => {
    console.log('Nueva publicación creada:', postData);
  };
  
  const navigateToComment = async (comment: Comment) => {
    if (!comment) {
      return;
    }

    context.setCurrentComment(comment);
    props.navigation.navigate("CommentScreen");

  }
  return (
    <View className="flex-1 bg-[#0F1218] px-3 pt-6">
      <FlatList
        data={comments}
        keyExtractor={(item) => item.id.toString()}
        contentContainerStyle={{ paddingVertical: 16 }}
        className="mt-4"
        renderItem={({ item }) => (
          <TouchableOpacity className="flex-row items-center bg-[#1E222A] p-3 mb-3 rounded-lg"
            onPress={() => navigateToComment(item)}
          >
            <Text className="text-[#F6F7F7] text-base font-semibold flex-1">
              @{item.username}: {item.content}
            </Text>
          </TouchableOpacity>
        )}
        ListEmptyComponent={() => (
          <View className="items-center justify-center mt-12">
            <Icon name="eye-outline" size={78} color="#1E222A" />
          </View>
        )}
      />

      <CreatePostModal
        visible={showPostModal}
        onClose={() => setShowPostModal(false)}
        onPostCreated={handlePostCreated}
      />
      <PostButton onPress={() => setShowPostModal(true)} />
    </View>
  )
}

export default NotificationScreen

const styles = StyleSheet.create({})