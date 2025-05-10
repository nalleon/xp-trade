import { FlatList, Image, Text, View } from 'react-native';
import React, { useState, useEffect } from 'react';
import PostButton from '../components/PostButton';
import CreatePostModal from '../components/CreatePostModal';
import UseApi from '../hooks/UseApi';

const dummyPosts = [
  {
    id: '1',
    user: { username: 'root', profilePicture: null },
    content: 'Este juego es una locura 🔥',
    game: {
      title: 'Umamusume: Pretty Derby – Party Dash',
      coverArt: 'https://media.rawg.io/media/screenshots/12d/12d04f00a2eb84ac1523533c5adcf631.jpg',
    },
    picture: null,
  }
];

function HomeScreen() {
  const [posts, setPosts] = useState([]);
  const [showPostModal, setShowPostModal] = useState(false);

  const { handleGetPosts } = UseApi();

  useEffect(() => {
    getPosts();
  }, []);



  const getPosts = async () => {
    const result = await handleGetPosts();

    if (result != null)
      setPosts(result);
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

  return (
    <View className="flex-1 bg-[#0F1218] pt-10">
      <FlatList
        data={posts}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
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
                <Text className="text-[#F6F7F7] font-semibold">@{item.user.username}</Text>
                <Text className="text-xs text-[#8899A6]">{item.game.title}</Text>
              </View>
            </View>

            <View className="h-px bg-[#2C3038] mb-6" />


            <Text className="text-[#F6F7F7] mb-2">{item.content}</Text>

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
        )}
        contentContainerStyle={{ paddingBottom: 80 }}
      />

      <CreatePostModal
        visible={showPostModal}
        onClose={() => setShowPostModal(false)}
      />
      <PostButton onPress={() => setShowPostModal(true)} />
    </View>
  );
}

export default HomeScreen;
