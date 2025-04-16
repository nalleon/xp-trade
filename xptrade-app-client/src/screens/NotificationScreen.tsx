import { Image, StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useState } from 'react'
import PostButton from '../components/PostButton'
import { FlatList } from 'react-native-gesture-handler'
import { AppContext } from '../context/AppContext'
import { NativeStackScreenProps } from '@react-navigation/native-stack'
import { GameStackParamList } from '../navigations/stack/GameStackNav'
import Icon from 'react-native-vector-icons/Ionicons';

type Props = NativeStackScreenProps<GameStackParamList, 'SearchScreen'>;

const NotificationScreen = (props: Props) => {
    const [posts, setPosts] = useState<Result[]>([]);
    
    const context = useContext(AppContext);
    
    const navigateToPost = async (post : Result) => {
      if (!post){
       return;
      }  
  
      context.setCurrentGame(post);
      props.navigation.navigate("GameScreen");
  
    }
  return (
    <View className="flex-1 bg-[#0F1218] px-3 pt-6">
      <Text className="text-2xl font-bold text-[#F6F7F7] mb-4">NotificationScreen</Text>
      
            <FlatList
              data={posts}
              keyExtractor={(item) => item.id.toString()}
              contentContainerStyle={{ paddingVertical: 16 }}
              className="mt-4"
              renderItem={({ item }) => (
                <TouchableOpacity className="flex-row items-center bg-[#1E222A] p-3 mb-3 rounded-lg"
                  onPress={() => navigateToPost(item)}
                >
                  {
                    item.background_image  &&
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
                  <Icon name="eye-outline" size={78} color="#1E222A" />
              </View>
              )}
            />
      <PostButton/>
    </View>
  )
}

export default NotificationScreen

const styles = StyleSheet.create({})