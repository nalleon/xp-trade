import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useState } from 'react'
import { TextInput } from 'react-native-gesture-handler';
import Icon from 'react-native-vector-icons/Ionicons';

type Props = {}

const SearchScreen = (props: Props) => {
  const [search, setSearch] = useState<string>("")
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
        <TouchableOpacity>
          <Icon name='search-circle' size={38} color={'#556791'} />
        </TouchableOpacity>
      </View>

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