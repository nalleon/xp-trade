import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createDrawerNavigator } from '@react-navigation/drawer';
import AuthStackNav from '../stack/AuthStackNav';
import SettingsStackNav from '../stack/SettingsStackNav';
import ProfileStackNav from '../stack/ProfileStackNav';

type Props = {}

type DrawerScreens = {
    ProfileStackNav: undefined, 
    SettingsStackNav: undefined,
    AuthStackNav: undefined,
}

const Drawer = createDrawerNavigator();

  
const DrawerNav = (props: Props) => {
  return (
    <Drawer.Navigator id={undefined}>
      <Drawer.Screen name="ProfileStackNav" component={ProfileStackNav} options={{ title: 'Mi perfil' }} />
      <Drawer.Screen name="SettingsStackNav" component={SettingsStackNav} options={{ title: 'Ajustes' }}/>
      <Drawer.Screen name="AuthStackNav" component={AuthStackNav} options={{ title: 'Cerrar sesión' }}/>
    </Drawer.Navigator>
  )
}

export default DrawerNav

const styles = StyleSheet.create({})