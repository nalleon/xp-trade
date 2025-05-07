import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React from 'react'
import Icon from 'react-native-vector-icons/Ionicons';

type Props = {
  onPress: () => void;
};

const PostButton = ({ onPress }: Props) => {
  return (
    <TouchableOpacity
      className="absolute bottom-6 right-6 bg-[#556791] rounded-full p-4"
      onPress={onPress}
    >
      <Icon name="add" size={28} color="#F6F7F7" />
    </TouchableOpacity>
  );
};

export default PostButton;
