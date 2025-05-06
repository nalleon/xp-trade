import { Image, ScrollView, TouchableOpacity } from 'react-native';
import { Text, View } from 'react-native';
import React, { useContext, useEffect, useState } from 'react';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { GameStackParamList } from '../navigations/stack/GameStackNav';
import { AppContext } from '../context/AppContext';
import Icon from 'react-native-vector-icons/Ionicons';
import { ImageZoom } from '@likashefqet/react-native-image-zoom';
import ScreenshotGallery from '../components/ScreenshotGallery';
import UseApi from '../hooks/UseApi';
import { Result, XPTradeInputGame } from '../utils/TypeUtils';
import { GameDetails } from '../utils/GameDetailsType';
import UseRAWGApi from '../hooks/UseRAWGApi';

type Props = NativeStackScreenProps<GameStackParamList, 'GameScreen'>;

const GameScreen = (props: Props) => {
  const [showPlatforms, setShowPlatforms] = useState(false);
  const [showGenres, setShowGenres] = useState(false);

  const [showPublishers, setShowPublishers] = useState(false);
  const [showDevelopers, setShowDevelopers] = useState(false);
  
  const { currentGame, currentGameDetailed, username } = useContext(AppContext);

  const { handleAddToCollection } = UseApi();

  const addToCollection = async (game: GameDetails) => {
    if(!game){
      return;
    }

    const aux: XPTradeInputGame = {
      game: {
        title: game.name,
        coverArt: game.background_image,
        developerInputDTOSet: [], 
        genreInputDTOSet: game.genres.map((genre) => ({
          name: genre.name,
        })),
        platformInputDTOSet: game.platforms.map((p) => ({
          name: p.platform.name,
        })),
        publisherInputDTOSet: [],
        regionInputDTOSet: [],
      },
      user: {
        username,
      },
    };
  }

  const addToFavorite = async (game: Result) => {

  }
  return (
    <ScrollView className="flex-1 bg-[#0F1218]">
      {
        currentGame.background_image &&
        <Image
          source={{ uri: currentGame.background_image }}
          className="w-full h-60"
          resizeMode="cover"
        />
      }

      {
        !currentGame.background_image && currentGame.short_screenshots && currentGame.short_screenshots.length > 0 &&
         <Image
           source={{ uri: currentGame.short_screenshots[0].image }}
           className="w-full h-60"
           resizeMode="cover"
         />
      }

      {
        !currentGame.background_image && (!currentGame.short_screenshots || currentGame.short_screenshots.length === 0) && (
          <Image
            source={require('../resources/xp-trade.png')}
            className="w-full h-60"
            resizeMode="cover"
          />
        )
      }

      <View className="p-4">
        <Text className="text-[#F6F7F7] text-2xl font-bold mb-2">
          {currentGame.name}
        </Text>

        <View className="flex-row justify-between items-center mb-4">
          <View className="flex-row items-center space-x-6">
              <View className="flex-row items-center space-x-1">
                <Icon name="star" size={16} color="#9D8D6A" />
                <Text className="text-[#F6F7F7]">{currentGame.rating.toFixed(1)} / 5</Text>
              </View>
              <View className="flex-row items-center space-x-1">
                <Icon name="calendar-outline" size={16} color="#999" />
                <Text className="text-[#999]">{currentGame.released}</Text>
              </View>
          </View>

          <View className="flex-row space-x-4">
            <TouchableOpacity
              onPress={() => addToCollection(currentGameDetailed)}
              className="p-2"
            >
              <Icon name="add-circle-outline" size={22} color="#F6F7F7" />
            </TouchableOpacity>

            <TouchableOpacity
              onPress={() => addToFavorite(currentGame)}
              className="p-2"
            >
              <Icon name="heart-outline" size={22} color="#F6F7F7" />
            </TouchableOpacity>
          </View>
        </View>

        <View className="h-px bg-[#2C3038] mb-6" />


<View className="flex-row justify-between gap-4 mb-4">

  <View className="flex-1">
    <TouchableOpacity 
      onPress={() => setShowDevelopers(prev => !prev)} 
      className="flex-row items-center mb-1"
    >
      <Text className="text-[#F6F7F7] font-semibold mr-2">Desarrolladora(s)</Text>
      <Icon 
        name={showDevelopers ? 'caret-up-outline' : 'caret-down-outline'} 
        size={16} 
        color="#F6F7F7" 
      />
    </TouchableOpacity>
    {showDevelopers && (
      <View className="ml-2">
        {currentGameDetailed.developers.map((dev) => (
          <Text key={dev.id} className="text-[#ccc] mb-1">
            {dev.name}
          </Text>
        ))}
      </View>
    )}
  </View>
    <View className="flex-1">
    <TouchableOpacity 
      onPress={() => setShowPublishers(prev => !prev)} 
      className="flex-row items-center mb-1"
    >
      <Text className="text-[#F6F7F7] font-semibold mr-2">Publisher(s)</Text>
      <Icon 
        name={showPublishers ? 'caret-up-outline' : 'caret-down-outline'} 
        size={16} 
        color="#F6F7F7" 
      />
    </TouchableOpacity>
    {showPublishers && (
      <View className="ml-2">
        {currentGameDetailed.publishers.map((pub) => (
          <Text key={pub.id} className="text-[#ccc] mb-1">
            {pub.name}
          </Text>
        ))}
      </View>
    )}
  </View>

</View>

{currentGame.platforms?.length > 0 && 
        <View className="mb-4">
          <Text className="text-[#F6F7F7] font-semibold mb-1">Plataformas:</Text>
          <Text className="text-[#ccc]">
            {currentGame.platforms.map(p => p.platform.name).join(', ')}
          </Text>
        </View>
        }

{(currentGame.genres?.length > 0 || currentGame.tags?.length > 0) && (
  <View className="mb-6">
<View className="flex-row items-center justify-center my-6">
  <View className="flex-1 h-px bg-[#3A3F4A]" />
  <Text className="mx-3 text-[#999] text-xs uppercase">ETIQUETAS</Text>
  <View className="flex-1 h-px bg-[#3A3F4A]" />
</View>

    <View className="flex-row flex-wrap justify-between gap-2">
      {currentGame.genres.map((genre) => (
        <View
          key={`genre-${genre.id}`}
          className="bg-[#1E222A] px-3 py-1 rounded-full mb-2 flex-grow"
        >
          <Text className="text-[#9D8D6A] text-xs text-center">{genre.name}</Text>
        </View>
      ))}

      {currentGame.tags
        .filter((tag) => tag.language !== 'rus')
        .filter((tag) => !currentGame.genres.some((genre) => genre.name.toLowerCase() === tag.name.toLowerCase()))
        .slice(0, 10)
        .map((tag) => (
          <View
            key={`tag-${tag.id}`}
            className="bg-[#1E222A] px-3 py-1 rounded-full mb-2 flex-grow"
          >
            <Text className="text-[#66B3B7] text-xs capitalize text-center">{tag.name}</Text>
          </View>
        ))}
    </View>
  </View>
)}



        {currentGame.short_screenshots?.length > 0 && (
            <ScreenshotGallery screenshots={currentGame.short_screenshots}/>
        )}

      </View>
    </ScrollView>
  );
};

export default GameScreen;
