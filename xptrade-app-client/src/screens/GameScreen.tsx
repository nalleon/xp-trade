import { Image, ScrollView, TouchableOpacity } from 'react-native';
import { Text, View } from 'react-native';
import React, { useContext } from 'react';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { GameStackParamList } from '../navigations/stack/GameStackNav';
import { AppContext } from '../context/AppContext';
import Icon from 'react-native-vector-icons/Ionicons';

type Props = NativeStackScreenProps<GameStackParamList, 'GameScreen'>;

const GameScreen = (props: Props) => {
  const { currentGame } = useContext(AppContext);

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
              onPress={() => console.log("A")}
              className="p-2"
            >
              <Icon name="add-circle-outline" size={22} color="#F6F7F7" />
            </TouchableOpacity>

            <TouchableOpacity
              onPress={() => console.log("A2")}
              className="p-2"
            >
              <Icon name="heart-outline" size={22} color="#F6F7F7" />
            </TouchableOpacity>
          </View>
        </View>

        <View className="h-px bg-[#2C3038] mb-6" />

        <View className="mb-4">
          <Text className="text-[#F6F7F7] font-semibold mb-1">Plataformas:</Text>
          <Text className="text-[#ccc]">
            {currentGame.platforms.map(p => p.platform.name).join(', ')}
          </Text>
        </View>
        {currentGame.genres?.length > 0 && 
        <View className="mb-4">
          <Text className="text-[#F6F7F7] font-semibold mb-1">Géneros:</Text>
          <Text className="text-[#ccc]">
            {currentGame.genres.map(g => g.name).join(', ')}
          </Text>
        </View>
        }

        {currentGame.tags?.length > 0 && (
          <View className="mb-6">
            <Text className="text-[#F6F7F7] font-semibold mb-2">Tags:</Text>
            <View className="flex-row flex-wrap justify-between gap-2">
              {currentGame.tags.slice(0, 10).map((tag) => (
                <View
                  key={tag.id}
                  className="bg-[#1E222A] px-3 py-1 rounded-full mb-2 flex-grow"
                >
                  <Text className="text-[#66B3B7] text-xs capitalize text-center">{tag.name}</Text>
                </View>
              ))}
            </View>
          </View>
        )}


        {currentGame.short_screenshots?.length > 0 && (
          <View className="mb-8">
            <Text className="text-[#F6F7F7] font-semibold mb-2">Otras imágenes:</Text>
            <ScrollView horizontal showsHorizontalScrollIndicator={false}>
              {currentGame.short_screenshots.map((screenshot) => (
                <Image
                  key={screenshot.id}
                  source={{ uri: screenshot.image }}
                  className="w-40 h-24 mr-3 rounded-lg"
                  resizeMode="cover"
                />
              ))}
            </ScrollView>
          </View>
        )}

      </View>
    </ScrollView>
  );
};

export default GameScreen;
