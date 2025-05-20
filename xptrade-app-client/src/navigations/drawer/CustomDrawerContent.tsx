import { InteractionManager, StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext } from 'react'
import Icon from 'react-native-vector-icons/Ionicons';
import { DrawerContentComponentProps, DrawerContentScrollView, DrawerItemList } from '@react-navigation/drawer'
import { AppContext } from '../../context/AppContext'
import { CommonActions, useNavigation } from '@react-navigation/native';
import AsyncStorage from '@react-native-async-storage/async-storage';

type Props = {}

const CustomDrawerContent = (props: DrawerContentComponentProps) => {
  const context = useContext(AppContext);
  const navigation = useNavigation();
  async function handleLogout() {
    await AsyncStorage.clear();
      context.setToken(null);
  }

  return (
    <DrawerContentScrollView {...props}>
      <DrawerItemList {...props} />
      <TouchableOpacity onPress={handleLogout}
        className="flex-row items-center space-x-3 p-3 rounded-bl-xl rounded-tr-xl bg-[#442222]">
        <Icon name="log-out-outline" size={20} color="#F6F7F7" />
        <Text className="text-[#F6F7F7] text-base">Cerrar sesión</Text>
      </TouchableOpacity>
    </DrawerContentScrollView>
  )
}

export default CustomDrawerContent

const styles = StyleSheet.create({})