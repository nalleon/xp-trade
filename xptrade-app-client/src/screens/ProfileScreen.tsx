import { Dimensions, Image, StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useEffect, useRef, useState } from 'react'
import { ScrollView, TextInput } from 'react-native-gesture-handler';
import Icon from 'react-native-vector-icons/Ionicons';
import { AppContext } from '../context/AppContext';

import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { ProfileStackParamList } from '../navigations/stack/ProfileStackNav';
import { Result } from '../utils/TypeUtils';
import CreatePostModal from '../components/post.modals/CreatePostModal';
import PostButton from '../components/post.modals/PostButton';
import UseApi from '../hooks/UseApi';
import UseRAWGApi from '../hooks/UseRAWGApi';

type Props = NativeStackScreenProps<ProfileStackParamList, 'ProfileScreen'>;

const ProfileScreen = (props: Props) => {
  const [favorites, setFavorites] = useState<any[]>([]);
  const [collection, setCollection] = useState<any[]>([]);
  const [post, setPosts] = useState<any>();
  const [showLeftArrow, setShowLeftArrow] = useState(false);
  const [showRightArrow, setShowRightArrow] = useState(true);

  const scrollRef = useRef<ScrollView>(null);

  const context = useContext(AppContext);

  const { handleGetFavorites, handleGetUserLatestPost, handleGetCollection } = UseApi();
  const { handleGameDetailsFetch } = UseRAWGApi();

  const maxCharactersForTwoLines = 100;

  useEffect(() => {
    fetchUserProfileDetails();
  }, [])

  const fetchUserProfileDetails = async () => {
    setFavorites(await handleGetFavorites(context.username));
    setCollection(await handleGetCollection(context.username));
    setPosts(await handleGetUserLatestPost(context.username));
  }

  const handleScroll = (event) => {
    const { contentOffset, layoutMeasurement, contentSize } = event.nativeEvent;

    const scrollX = contentOffset.x;
    const totalWidth = contentSize.width;
    const visibleWidth = layoutMeasurement.width;

    setShowLeftArrow(scrollX > 10);
    setShowRightArrow(scrollX + visibleWidth < totalWidth - 10);
  };




  const navigateToPost = async (post: any) => {
    if (!post) {
      return;
    }

    context.setCurrentPost(post);
    props.navigation.navigate("PostScreenProfile");

  }

  const navigateCollection = async (collection: Result) => {
    if (!collection) {
      return;
    }

    context.setCurrentGame(collection);
    props.navigation.navigate("CollectionScreenProfile");

  }


  const navigateToGame = async (item) => {
    if (!item) return;

    context.setCurrentGame(item.game);

    const details = await handleGameDetailsFetch(item.game.slug);
    context.setCurrentGameDetailed(details);

    if (details) {
      props.navigation.navigate("GameScreenProfile");
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

  return (
    <ScrollView className="flex-1 bg-[#0F1218] pt-10 px-4"
      contentContainerStyle={{ alignItems: 'center' }}
    >

      <View className="items-center mb-6">
        <View className="w-24 h-24 rounded-full bg-[#1E222A] mb-2" />
        <Text className="text-[#F6F7F7] text-lg font-semibold">{context.username}</Text>
      </View>
      <View className="w-full mb-6">
        {favorites && favorites.length > 0 ? (
          <View className="w-full mb-6 items-center relative">

            {showLeftArrow && (
              <TouchableOpacity
                className="absolute left-0 z-10 h-full justify-center pl-2"
                onPress={() => scrollRef.current?.scrollTo({ x: 0, animated: true })}
              >
                <Icon name="chevron-back" size={24} color="#9D8D64" />
              </TouchableOpacity>
            )}

            <View style={{ width: 272 }}>
              <ScrollView
                ref={scrollRef}
                horizontal
                pagingEnabled
                decelerationRate="fast"
                snapToInterval={144}
                snapToAlignment="start"
                showsHorizontalScrollIndicator={false}
                onScroll={handleScroll}
                scrollEventThrottle={16}
                contentContainerStyle={{ paddingHorizontal: 0 }}
                className="space-x-4"
              >
                {favorites.map((fav, index) => (
                  <TouchableOpacity
                    key={index}
                    className="w-32 h-48 bg-[#1E222A] rounded-tr-xl rounded-bl-xl overflow-hidden border border-[#9D8D64]"
                    onPress={() => navigateToGame(fav)}
                  >
                    <Image
                      source={{ uri: fav.game.coverArt }}
                      className="w-32 h-28"
                      resizeMode="cover"
                    />
                    <View className="flex-1 items-center justify-center p-2">
                      <Text className="text-[#F6F7F7] font-semibold text-center text-sm" numberOfLines={2}>
                        {fav.game.title}
                      </Text>
                      <Icon name="star" size={20} color="#9D8D64" style={{ marginTop: 4 }} />
                    </View>
                  </TouchableOpacity>
                ))}
              </ScrollView>
            </View>
            {(showRightArrow && favorites.length > 2) && (
              <TouchableOpacity
                className="absolute right-0 z-10 h-full justify-center pr-2"
                onPress={() => scrollRef.current?.scrollToEnd({ animated: true })}
              >
                <Icon name="chevron-forward" size={24} color="#9D8D64" />
              </TouchableOpacity>
            )}
          </View>

        ) : (
          <Text className="text-[#8899A6] text-sm text-center mt-2">
            No hay nada de momento
          </Text>
        )}
      </View>


      <View className="w-full mb-6 items-center relative">
        <Text className="text-[#F6F7F7] text-base font-bold mb-2">Última publicación</Text>

        {post ? (
          <TouchableOpacity key={post.id} onPress={() => navigateToPost(post)}>
            <View className="bg-[#1E222A] rounded-xl p-4 w-64 h-40 border border-[#9D8D64]">
              <View className="flex-row items-center mb-2">
                <Text className="text-xs text-[#8899A6]">{post.game.title}</Text>
              </View>

              <View className="h-px bg-[#2C3038] mb-6" />

              <Text
                className="text-[#F6F7F7] mb-2"
                numberOfLines={
                  post.content && post.content.length > maxCharactersForTwoLines ? 2 : undefined
                }
              >
                {post.content}
              </Text>

              {post.content && post.content.length >= maxCharactersForTwoLines && (
                <Text className="text-xs text-[#66B3B7]">Ver más</Text>
              )}

              <View className="flex-row items-end mt-2">
                <Text className="text-xs text-[#8899A6] ml-auto">
                  {formatDate(post.creationDate)}
                </Text>
              </View>
            </View>
          </TouchableOpacity>
        ) : (
          <Text className="text-[#8899A6] text-sm text-center mt-2">
            No hay posts
          </Text>
        )}
      </View>




      <TouchableOpacity className="bg-[#66B3B7] px-6 py-2 rounded-lg" onPress={() => navigateCollection}>
        <Text className="text-[#F6F7F7] text-base font-semibold">COLECCION</Text>
      </TouchableOpacity>

    </ScrollView>

  )
}

export default ProfileScreen

const styles = StyleSheet.create({})