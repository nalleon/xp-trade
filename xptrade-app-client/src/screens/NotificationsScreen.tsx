import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import PostButton from '../components/PostButton'

type Props = {}

const NotificationsScreen = (props: Props) => {
  return (
    <View className="flex-1 bg-[#0F1218] px-3 pt-6">
      <Text className="text-2xl font-bold text-[#F6F7F7] mb-4">Home</Text>
      <PostButton/>
    </View>
  )
}

export default NotificationsScreen

const styles = StyleSheet.create({})