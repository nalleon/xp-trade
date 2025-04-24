import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useState } from 'react'
import { ScrollView, TextInput } from 'react-native-gesture-handler';
import Icon from 'react-native-vector-icons/Ionicons';
import { AppContext } from '../context/AppContext';

import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { ProfileStackParamList } from '../navigations/stack/ProfileStackNav';

type Props = NativeStackScreenProps<ProfileStackParamList, 'ProfileScreen'>;

const ProfileScreen = (props: Props) => {
  const [search, setSearch] = useState<string>("")

  const context = useContext(AppContext);

  const navigateToPost = async (post : Result) => {
    if (!post){
     return;
    }  

    context.setCurrentGame(post);
    props.navigation.navigate("ProfileScreen");

  }

  const navigateCollection = async (collection : Result) => {
    if (!collection){
     return;
    }  

    context.setCurrentGame(collection);
    props.navigation.navigate("CollectionScreen");

  }

  const navigateFavorite = async (favorite : Result) => {
    if (!favorite){
     return;
    }  

    context.setCurrentGame(favorite);
    props.navigation.navigate("FavoriteScreen");

  }

  const navigateToGame = async (game : Result) => {
    if (!game){
     return;
    }  

    context.setCurrentGame(game);
    props.navigation.navigate("GameScreen");
  }

  return (
    <View className="flex-1 bg-[#0F1218] pt-10 px-4 items-center">

      <View className="items-center mb-6">
        <View className="w-24 h-24 rounded-full bg-[#1E222A] mb-2" />
        <Text className="text-[#F6F7F7] text-lg font-semibold">gatoviotas</Text>
        <Text className="text-[#999] text-sm">derecha donde tu izquierda yo</Text>
      </View>
    
      <View className="w-full mb-6">
        <Text className="text-[#F6F7F7] text-base font-bold mb-2 text-center">
          FAVORITE GAMES
        </Text>
        <ScrollView
          horizontal
          showsHorizontalScrollIndicator={false}
          className="flex-row space-x-4 px-2"
        >
          <View className="w-40 h-20 bg-[#1E222A] rounded-md justify-center items-center relative">
            <TouchableOpacity onPress={() => navigateToGame}>
              <Icon name="image-outline" size={30} color="#556791" />
            </TouchableOpacity>
            <Icon
              name="star"
              size={18}
              color="#9D8D64"
              className="absolute bottom-5 right-1"
            />
          </View>
        </ScrollView>
      </View>

    
      <View className="w-full mb-6 items-center">
        <Text className="text-[#F6F7F7] text-base font-bold mb-2">POSTS</Text>
        <View className="w-75 h-40 bg-[#1E222A] rounded-lg flex-row items-center justify-center">
          <Icon name="chevron-back" size={30} color="#556791" />
          <View className="w-72 h-32 bg-[#0F1218] mx-2 justify-center items-center">
            <Text className='text-[#F6F7F7]'>EXAMPLE</Text>
          </View>
          <Icon name="chevron-forward" size={30} color="#556791" />
        </View>
      </View>
    
      <TouchableOpacity className="bg-[#66B3B7] px-6 py-2 rounded-lg" onPress={() => navigateCollection}>
        <Text className="text-[#F6F7F7] text-base font-semibold">SHOW COLLECTION</Text>
      </TouchableOpacity>
    </View>
  
  )
}

export default ProfileScreen

const styles = StyleSheet.create({})