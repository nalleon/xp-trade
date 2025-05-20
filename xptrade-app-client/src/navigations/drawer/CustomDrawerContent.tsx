import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext } from 'react'
import Icon from 'react-native-vector-icons/Ionicons';
import { DrawerContentComponentProps, DrawerContentScrollView, DrawerItemList } from '@react-navigation/drawer'
import { AppContext } from '../../context/AppContext'
import { CommonActions, useNavigation } from '@react-navigation/native';

type Props = {}

const CustomDrawerContent = (props: DrawerContentComponentProps) => {
    const context = useContext(AppContext);
  function handleLogout() {
    setTimeout(() => {
        context.setToken("");
    }, 100);
  }

  return (
    <DrawerContentScrollView {...props}>
        <DrawerItemList {...props}/>
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