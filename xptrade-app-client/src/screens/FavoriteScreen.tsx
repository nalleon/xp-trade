import { StyleSheet, Text, View } from 'react-native'
import React from 'react'

import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { ProfileStackParamList } from '../navigations/stack/ProfileStackNav';

type Props = NativeStackScreenProps<ProfileStackParamList, 'FavoriteScreen'>;


const FavoriteScreen = (props: Props) => {
  return (
    <View>
      <Text>FavoriteScreen</Text>
    </View>
  )
}

export default FavoriteScreen

const styles = StyleSheet.create({})