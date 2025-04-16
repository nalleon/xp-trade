import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React from 'react'
import Icon from 'react-native-vector-icons/Ionicons';

type Props = {}

const PostButton = (props: Props) => {
  return (
    <TouchableOpacity 
        className="absolute bottom-6 right-6 bg-[#556791] rounded-full p-4"
        onPress={() => console.log("createPost")}
    >
    <Icon name="add" size={28} color="#F6F7F7" />
    </TouchableOpacity>
  )
}

export default PostButton

const styles = StyleSheet.create({})