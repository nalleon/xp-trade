import { Image, StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useEffect, useState } from 'react'
import { FlatList, TextInput } from 'react-native-gesture-handler';
import Icon from 'react-native-vector-icons/Ionicons';
import UseRAWGApi from '../hooks/UseRAWGApi';
import { AppContext } from '../context/AppContext';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { GameStackParamList } from '../navigations/stack/GameStackNav';
import PostButton from '../components/PostButton';
import { Result } from '../utils/TypeUtils';
import CreatePostModal from '../components/CreatePostModal';

type Props = NativeStackScreenProps<GameStackParamList, 'SearchScreen'>;

const SearchScreen = (props: Props) => {
  const [search, setSearch] = useState<string>("")
  const [games, setGames] = useState<Result[]>([])

  useEffect(() => {

  }, [])

  const [showPostModal, setShowPostModal] = useState(false);

  const handlePostCreated = (postData: {
    text: string;
    image?: string;
    game: any;
  }) => {
    console.log('Nueva publicación creada:', postData);
  };
  
  const context = useContext(AppContext);

  const { handleFetch, handleGameDetailsFetch } = UseRAWGApi();

  const getGames = async (search: string) => {
    const result: Result[] | null = await handleFetch(search);

    if (result != null) {
      const filteredByTagGames = result.filter(
        (game) =>
          !game.tags.some(
            (tag) => tag.name.toLowerCase() === 'fangame' || tag.slug.toLowerCase().includes('fangame') ||
              tag.name.toLocaleLowerCase() === 'randomizer' || tag.slug.toLocaleLowerCase() === 'randomizer' ||
              tag.name.toLocaleLowerCase() === 'doujin' || tag.slug.toLocaleLowerCase() === 'doujin' ||
              tag.name.toLocaleLowerCase() === 'doujin-game' || tag.slug.toLocaleLowerCase() === 'doujin-game'
          )
      );

      setGames(filteredByTagGames);
    }
  }

  const navigateToGame = async (game: Result) => {
    if (!game) {
      return;
    }

    context.setCurrentGame(game);
    context.setCurrentGameDetailed(await handleGameDetailsFetch(game.slug));

    props.navigation.navigate("GameScreen");

  }

  return (
    <View className="flex-1 bg-[#0F1218] pt-10 px-4">
      <View className="flex-row items-center bg-[#1E222A] rounded-lg px-3 py-2 self-center w-full max-w-md">
        <TextInput
          className="flex-1 text-[#F6F7F7] text-base mr-2"
          placeholder="Nombre del juego"
          placeholderTextColor="#999"
          value={search}
          onChangeText={setSearch}
        />
        <TouchableOpacity onPress={() => getGames(search)}>
          <Icon name='search-circle' size={38} color={'#556791'} />
        </TouchableOpacity>
      </View>

      <FlatList
        data={games}
        keyExtractor={(item) => item.id.toString()}
        contentContainerStyle={{ paddingVertical: 16 }}
        className="mt-4"
        renderItem={({ item }) => (
          <TouchableOpacity className="flex-row items-center bg-[#1E222A] p-3 mb-3 rounded-lg"
            onPress={() => navigateToGame(item)}
          >
            {
              item.background_image &&
              <Image
                source={{ uri: item.background_image }}
                className="w-14 h-14 rounded-md mr-4"
                resizeMode="cover"
              />
            }

            {
              !item.background_image && item.short_screenshots && item.short_screenshots.length > 0 &&
              <Image
                source={{ uri: item.short_screenshots[0].image }}
                className="w-14 h-14 rounded-md mr-4"
                resizeMode="cover"
              />
            }

            {
              !item.background_image && (!item.short_screenshots || item.short_screenshots.length === 0) && (
                <Image
                  source={require('../resources/xp-trade.png')}
                  className="w-14 h-14 rounded-md mr-4"
                  resizeMode="cover"
                />
              )
            }

            <Text className="text-[#F6F7F7] text-base font-semibold flex-1">
              {item.name}
            </Text>
          </TouchableOpacity>
        )}
        ListEmptyComponent={() => (
          <View className="items-center justify-center mt-12">
            <Icon name="game-controller-outline" size={78} color="#1E222A" />
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

export default SearchScreen