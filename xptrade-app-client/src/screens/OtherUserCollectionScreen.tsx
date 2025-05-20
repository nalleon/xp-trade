import React, { useState, useContext, useCallback } from 'react';
import { View, Text, Image, ActivityIndicator, TouchableOpacity, FlatList } from 'react-native';
import { useFocusEffect } from '@react-navigation/native';
import { AppContext } from '../context/AppContext';
import UseApi from '../hooks/UseApi';
import UseRAWGApi from '../hooks/UseRAWGApi';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { GameStackParamList } from '../navigations/stack/CollectionStackNav';
import { HomeStackParamList } from '../navigations/stack/HomeStackNav';
import { BackHandler } from 'react-native';

type Props = NativeStackScreenProps<HomeStackParamList, 'OtherUserCollectionScreen'>;

const OtherUserCollectionScreen = (props: Props) => {
  const { otherUsername, setCurrentGame, setCurrentGameDetailed } = useContext(AppContext);
  const [collection, setCollection] = useState([]);
  const [loading, setLoading] = useState(true);

  const { handleGetCollection } = UseApi();
  const { handleGameDetailsFetch } = UseRAWGApi();

  useFocusEffect(
    useCallback(() => {
      let isActive = true;

      const fetchCollection = async () => {
        setLoading(true);
        try {
          const data = await handleGetCollection(otherUsername);
          if (isActive) {
            setCollection(data.gameCollectionList);
          }
        } catch (error) {
          console.error('Failed to fetch collection:', error);
        } finally {
          if (isActive) setLoading(false);
        }
      };

      fetchCollection();

      return () => {
        isActive = false;
        setCollection([]);
        setLoading(true);
      };

    }, [otherUsername])
  );

  useFocusEffect(
    useCallback(() => {
      const onBackPress = () => {
        props.navigation.navigate("HomeScreen");    
        return true;
      };
      BackHandler.addEventListener('hardwareBackPress', onBackPress);

      return () => BackHandler.removeEventListener('hardwareBackPress', onBackPress);
    }, [props.navigation])
  );


  const navigateToGame = async (item) => {
    if (!item) return;

    setCurrentGame(item.game);

    const details = await handleGameDetailsFetch(item.game.slug);
    setCurrentGameDetailed(details);

    if (details) {
      props.navigation.navigate('GameScreenHome');
    }
  };

  const renderItem = ({ item }: { item: any }) => (
    <TouchableOpacity
      className="w-[31%] m-[1.16%] bg-[#1E222A] rounded-tr-xl rounded-bl-xl overflow-hidden border border-[#9D8D64]"
      onPress={() => navigateToGame(item)}
    >
      <Image
        source={{ uri: item.game.coverArt }}
        className="w-full h-28"
        resizeMode="cover"
      />
      <View className="items-center justify-center p-2">
        <Text className="text-[#F6F7F7] font-semibold text-center text-sm" numberOfLines={2}>
          {item.game.title}
        </Text>
        <Text className="text-[#F6F7F7] text-sm">{item.region.name}</Text>
        <Text className="text-[#F6F7F7] text-sm">{item.platform.name}</Text>
      </View>
    </TouchableOpacity>
  );

  if (loading) {
    return (
      <View className="flex-1 justify-center items-center">
        <ActivityIndicator size="large" color="#000" />
      </View>
    );
  }

  if (!collection || collection.length === 0) {
    return (
      <View className="flex-1 justify-center items-center bg-[#1E222A] px-4">
        <Text className="text-[#F6F7F7] text-lg font-semibold text-center">
          No hay juegos en tu colección
        </Text>
      </View>
    );
  }

  return (
    <View className="flex-1 bg-[#0F1218] p-2">
      <FlatList
        data={collection}
        renderItem={renderItem}
        keyExtractor={(item, index) => `${item.game.id}-${index}`}
        numColumns={3}
        showsVerticalScrollIndicator={false}
      />
    </View>
  );
};

export default OtherUserCollectionScreen;
