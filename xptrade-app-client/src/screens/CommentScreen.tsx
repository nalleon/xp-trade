import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { NotificationStackParamList } from '../navigations/stack/NotificationStackNav';

type Props = NativeStackScreenProps<NotificationStackParamList, 'CommentScreen'>;

const CommentScreen = (props: Props) => {
  return (
    <View>
      <Text>CommentScreen</Text>
    </View>
  )
}

export default CommentScreen

const styles = StyleSheet.create({})