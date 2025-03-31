import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createDrawerNavigator } from '@react-navigation/drawer';
import AuthStackNav from '../stack/AuthStackNav';

type Props = {}

type DrawerScreens = {
    ProfileScreen: undefined, 
    DrawerStackNav: undefined,
    AuthStackNav: undefined,
}

const Drawer = createDrawerNavigator();

  
const DrawerNav = (props: Props) => {
  return (
    <Drawer.Navigator id={undefined}>
      <Drawer.Screen name="ProfileScreen" component={AuthStackNav} options={{ title: 'Mi perfil' }} />
      <Drawer.Screen name="DrawerStackNav" component={AuthStackNav} options={{ title: 'Ajustes' }}/>
      <Drawer.Screen name="AuthStackNav" component={AuthStackNav} options={{ title: 'Cerrar sesión' }}/>
    </Drawer.Navigator>
  )
}

export default DrawerNav

const styles = StyleSheet.create({})