import { FlatList, Image, Text, TouchableOpacity, View } from 'react-native';
import React, { useState, useEffect, useContext } from 'react';
import PostButton from '../components/post.modals/PostButton';
import CreatePostModal from '../components/post.modals/CreatePostModal';
import UseApi from '../hooks/UseApi';
import { Touchable } from 'react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { HomeStackParamList } from '../navigations/stack/HomeStackNav';
import { AppContext } from '../context/AppContext';
import { PostXPTrade } from '../utils/TypeUtils';
import { RefreshControl } from 'react-native-gesture-handler';
import { GameDetails } from '../utils/GameDetailsType';
import UseRAWGApi from '../hooks/UseRAWGApi';

type Props = NativeStackScreenProps<HomeStackParamList, 'HomeScreen'>;

function HomeScreen({ navigation }: Props) {
  const [posts, setPosts] = useState([]);
  const [showPostModal, setShowPostModal] = useState(false);
  const [refreshing, setRefreshing] = useState(false);
  const [refresh, setRefresh] = useState(false);
  const maxCharactersForFiveLines = 100;

  const { handleGetPosts } = UseApi();
  const { handleGameDetailsFetch } = UseRAWGApi();
  const context = useContext(AppContext);

  useEffect(() => {
      getPosts();
  }, [refresh]);



  const getPosts = async () => {
    const result = await handleGetPosts();

    if (result != null) {

      setPosts(result);
    }
  }


  const formatDate = (isoString) => {
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


  const navigateToPostDetails = (item: PostXPTrade) => {
    context.setCurrentPost(item);
    navigation.navigate('PostScreen')
  }

  const navigateToGameDetails = async (game) => {
    context.setCurrentGameDetailed(await handleGameDetailsFetch(game.slug));
    navigation.navigate('GameScreenHome');
  }

  const handleRefresh = async () => {
    setRefreshing(true);
    setRefresh(!refresh);

    setTimeout(() => {
      setRefreshing(false);
    }, 2000);
  }

  return (
    <View className="flex-1 bg-[#0F1218] pt-5">
      <FlatList
        data={posts}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => {
        

          return (
            <TouchableOpacity onPress={() => navigateToPostDetails(item)}>


              <View className="bg-[#1E222A] rounded-xl p-4 mb-4 mx-4">
                <View className="flex-row items-center mb-2">
                  <Image
                    source={
                      item.user.profilePicture
                        ? { uri: item.user.profilePicture }
                        : require('../resources/xp-trade.png')
                    }
                    className="w-10 h-10 rounded-full mr-3"
                  />
                  <View>
                    <TouchableOpacity>
                      <Text className="text-[#F6F7F7] font-semibold">@{item.user.username}</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => navigateToGameDetails(item.game)}>
                      <Text className="text-xs text-[#8899A6]">{item.game.title}</Text>
                    </TouchableOpacity>
                  </View>
                </View>

                <View className="h-px bg-[#2C3038] mb-6" />

                <Text
                  className="text-[#F6F7F7] mb-2"
                  numberOfLines={item.content.length >= maxCharactersForFiveLines ? 5 : 0}
                >
                  {item.content}
                </Text>

                {item.content.length/2 >= maxCharactersForFiveLines && (
                  <TouchableOpacity onPress={() => navigateToPostDetails(item)}>
                    <Text className="text-xs text-[#66B3B7]">Ver más</Text>
                  </TouchableOpacity>
                )}

                {item.picture && (
                  <Image
                    source={{ uri: item.picture }}
                    className="w-full h-48 rounded-md mb-2"
                    resizeMode="cover"
                  />
                )}

                <View className="flex-row items-end mt-2">
                  <Text className="text-xs text-[#8899A6] ml-auto">{formatDate(item.creationDate)}</Text>
                </View>
              </View>
            </TouchableOpacity>
          );
        }}
        contentContainerStyle={{ paddingBottom: 80 }}
        refreshControl={
          <RefreshControl
            refreshing={refreshing}
            onRefresh={handleRefresh}
          />
        }
      />

      <CreatePostModal
        visible={showPostModal}
        onClose={() => {setShowPostModal(false); setRefresh(!refresh);}}
      />
      <PostButton onPress={() => setShowPostModal(true)} />
    </View>
  );
}

export default HomeScreen;
