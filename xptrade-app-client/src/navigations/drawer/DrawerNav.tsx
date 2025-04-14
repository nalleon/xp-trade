import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createDrawerNavigator } from '@react-navigation/drawer';
import AuthStackNav from '../stack/AuthStackNav';
import SettingsStackNav from '../stack/SettingsStackNav';
import ProfileStackNav from '../stack/ProfileStackNav';
import HomeScreen from '../../screens/HomeScreen';
import TabNav from '../tab/TabNav';
import LogoutScreen from '../../screens/LogoutScreen';

type Props = {}

type DrawerScreens = {
    ProfileStackNav: undefined, 
    SettingsStackNav: undefined,
    AuthStackNav: undefined,
    HomeScreen: undefined
}

const Drawer = createDrawerNavigator();

  
const DrawerNav = (props: Props) => {
  return (
    <Drawer.Navigator id={undefined}
      screenOptions={{
        headerStyle:{
          height: 40,
        }
      }}
    >
      <Drawer.Screen name="TabNav" component={TabNav} options={{ title: 'Main' }} />
      <Drawer.Screen name="ProfileStackNav" component={ProfileStackNav} options={{ title: 'Mi perfil' }} />
      <Drawer.Screen name="SettingsStackNav" component={SettingsStackNav} options={{ title: 'Ajustes' }}/>
      <Drawer.Screen name="AuthStackNav" component={LogoutScreen} options={{ title: 'Cerrar sesión' }}/>
    </Drawer.Navigator>
  )
}

export default DrawerNav

const styles = StyleSheet.create({})