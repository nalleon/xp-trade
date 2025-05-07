import { Alert, Button, StyleSheet, Text, TextInput, TouchableOpacity, View } from 'react-native'
import React, { useEffect, useState } from 'react'
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';
import PostButton from '../components/PostButton';





type AuthProps = NativeStackScreenProps<AuthStackParamList, 'TabNav'>;

function HomeScreen(props: AuthProps) {

  return (
    <View className="flex-1 bg-[#0F1218] px-3 pt-6">
      <Text className="text-2xl font-bold text-[#F6F7F7] mb-4">Home</Text>
      <PostButton />
    </View>
  );
}

export default HomeScreen;