import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { NotificationStackParamList } from '../navigations/stack/NotificationStackNav';

type Props = NativeStackScreenProps<NotificationStackParamList, 'PostScreen'>;


const PostScreen = (props: Props) => {
  return (
    <View>
      <Text>PostScreen</Text>
    </View>
  )
}

export default PostScreen

const styles = StyleSheet.create({})