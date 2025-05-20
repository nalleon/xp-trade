import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext } from 'react'
import { createDrawerNavigator } from '@react-navigation/drawer';
import AuthStackNav from '../stack/AuthStackNav';
import SettingsStackNav from '../stack/SettingsStackNav';
import ProfileStackNav from '../stack/ProfileStackNav';
import HomeScreen from '../../screens/HomeScreen';
import TabNav from '../tab/TabNav';
import LogoutScreen from '../../screens/LogoutScreen';
import Icon from 'react-native-vector-icons/Ionicons'; // Para los iconos
import { AppContext } from '../../context/AppContext';
import CustomDrawerContent from './CustomDrawerContent';

type Props = {}

const Drawer = createDrawerNavigator();

const DrawerNav = (props: Props) => {

  return (
    <Drawer.Navigator 
    id={undefined}
      screenOptions={{
        drawerStyle: {
          backgroundColor: '#0F1218', 
          width: 250,
        },
        headerStyle: {
          backgroundColor: '#1E222A', 
          height: 40
        },
        headerTintColor: '#F6F7F7',
        headerTitleStyle: {
          fontWeight: 'bold',
        },
        drawerActiveBackgroundColor: '#556791', 
        drawerActiveTintColor: '#F6F7F7', 
        drawerInactiveTintColor: '#B0B0B0',
        drawerLabelStyle: {
          fontSize: 16,
          fontWeight: '600',
        },
      }}
    >
      <Drawer.Screen 
        name="TabNavDrawer" 
        component={TabNav} 
        options={{
          title: 'Home', 
          drawerIcon: ({ color }) => (
            <Icon name="home" size={20} color={color} />
          ),
          
        }} 
      />
      <Drawer.Screen 
        name="ProfileStackNav" 
        component={ProfileStackNav} 
        options={{
          title: 'Perfil', 
          drawerIcon: ({ color }) => (
            <Icon name="person-circle" size={20} color={color} />
          ),
        }} 
      />
    </Drawer.Navigator>
  )
}

export default DrawerNav
