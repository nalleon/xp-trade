import { Image, StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useEffect, useState } from 'react'
import { FlatList, TextInput } from 'react-native-gesture-handler';
import Icon from 'react-native-vector-icons/Ionicons';
import UseRAWGApi from '../hooks/UseRAWGApi';
import { AppContext } from '../context/AppContext';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { GameStackParamList } from '../navigations/stack/GameStackNav';

type Props = NativeStackScreenProps<GameStackParamList, 'SearchScreen'>;

const SearchScreen = (props: Props) => {
  const [search, setSearch] = useState<string>("")
  const [games, setGames] = useState<Result[]>([])

  useEffect(() => {
   
  }, [])

  const context = useContext(AppContext);
  
  const { handleFetch } = UseRAWGApi();

  const getGames = async (search : string) => {
      const result : Result[] | null = await handleFetch(search);

      if(result!=null){
         setGames(result);
      }

  }

  const navigateToGame = async (game : Result) => {
    if (!game){
     return;
    }  

    context.setCurrentGame(game);
    console.log(game);
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
              item.background_image  ?
              <Image
                source={{ uri: item.background_image }}
                className="w-14 h-14 rounded-md mr-4"
                resizeMode="cover"
              />  
              :
              <Icon
                name='image'
                className="w-14 h-14 rounded-md mr-4"
                size={48}
                color="#556791"
              />
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

      <TouchableOpacity 
        className="absolute bottom-6 right-6 bg-[#556791] rounded-full p-4"
        onPress={() => console.log("createPost")}
      >
        <Icon name="add" size={28} color="#F6F7F7" />
      </TouchableOpacity>
    </View>
  )
}

export default SearchScreen