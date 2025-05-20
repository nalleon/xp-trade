import { StyleSheet, Text, View } from 'react-native'
import React, { useContext, useEffect, useState } from 'react'
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { ProfileStackParamList } from '../navigations/stack/ProfileStackNav';
import UseApi from '../hooks/UseApi';
import { AppContext } from '../context/AppContext';

type Props = NativeStackScreenProps<ProfileStackParamList, 'CollectionScreenProfile'>;

const CollectionScreen = (props: Props) => {
  const [games, setGames] = useState([]);

  const context = useContext(AppContext);

  const { handleGetCollection } = UseApi();

  useEffect(() => {
    const getCollection = async () => {
      const collection = await handleGetCollection(context.username);
      setGames(collection);
    }
    getCollection();
  }, [])
  
  return (
    <View className="flex-1 bg-[#0F1218] px-3 pt-6">
      <Text className="text-2xl font-bold text-[#F6F7F7] mb-4">CollectionScreen</Text>

    </View>
  )
}

export default CollectionScreen

const styles = StyleSheet.create({})