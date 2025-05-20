import React, { useEffect, useState, useContext } from 'react';
import { View, Text, FlatList, Image, ActivityIndicator, TouchableOpacity } from 'react-native';
import { styled } from 'nativewind';
import { AppContext } from '../context/AppContext';
import UseApi from '../hooks/UseApi';
import { ScrollView } from 'react-native-gesture-handler';
import UseRAWGApi from '../hooks/UseRAWGApi';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { GameStackParamList } from '../navigations/stack/CollectionStackNav';

type Props = NativeStackScreenProps<GameStackParamList, 'CollectionScreen'>;

const CollectionScreen = (props: Props) => {
  const { username } = useContext(AppContext);
  const [collection, setCollection] = useState([]);
  const [loading, setLoading] = useState(true);

  const { handleGetCollection } = UseApi();

  const { handleGameDetailsFetch } = UseRAWGApi();

  const context = useContext(AppContext);

  useEffect(() => {
    const fetchCollection = async () => {
      try {
        const data = await handleGetCollection(username);
        setCollection(data.gameCollectionList);
      } catch (error) {
        console.error('Failed to fetch collection:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchCollection();
  }, [username]);

  if (loading) {
    return (
      <View className="flex-1 justify-center items-center">
        <ActivityIndicator size="large" color="#000" />
      </View>
    );
  }

  const navigateToGame = async (item) => {
    if (!item) return;

    context.setCurrentGame(item.game);

    const details = await handleGameDetailsFetch(item.game.slug);
    context.setCurrentGameDetailed(details);

    if (details) {
      props.navigation.navigate("GameScreen");
    }
  }

  return (
    <View className='flex-1 p-2 bg-[#1E222A]'>
      <ScrollView
        horizontal
        pagingEnabled
        decelerationRate="fast"
        snapToInterval={144}
        snapToAlignment="start"
        showsHorizontalScrollIndicator={false}
        scrollEventThrottle={16}
        contentContainerStyle={{ paddingHorizontal: 0 }}
        className="space-x-4"
      >
        {
          collection && collection.length > 0 ?
          collection.map((item, index) => (
            <TouchableOpacity
              key={index}
              className="w-32 h-48 bg-[#1E222A] rounded-tr-xl rounded-bl-xl overflow-hidden border border-[#9D8D64]"
              onPress={() => navigateToGame(item)}
            >
              <Image
                source={{ uri: item.game.coverArt }}
                className="w-32 h-28"
                resizeMode="cover"
              />
              <View className="flex-1 items-center justify-center p-2">
                <Text className="text-[#F6F7F7] font-semibold text-center text-sm" numberOfLines={2}>
                  {item.game.title}
                </Text>
                <Text className="text-[#F6F7F7] text-sm">
                  {item.region.name}
                </Text>
                <Text className="text-[#F6F7F7] text-sm">
                  {item.platform.name}
                </Text>
              </View>
            </TouchableOpacity>
          ))
          :
          <View className="flex-1 justify-center items-center">
            <Text className="text-[#F6F7F7] text-lg font-semibold mb-4 text-center">
              No hay juegos en tu colección
            </Text>
          </View>
        }
      </ScrollView>
    </View>
  );
};

export default CollectionScreen;