
import 'react-native-gesture-handler';
import { createDrawerNavigator } from '@react-navigation/drawer';
import React, { useContext, useEffect, useState } from 'react';
import type { PropsWithChildren } from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';



import { NavigationContainer } from '@react-navigation/native';

import "reflect-metadata";
import { dataSource } from './src/data/Database';

import axios from 'axios';
import AuthStackNav from './src/navigations/stack/AuthStackNav';
import { GestureHandlerRootView } from 'react-native-gesture-handler';
import AppContextProvider, { AppContext } from './src/context/AppContext';
import RootNavigation from './src/navigations/RootNavigation';









function App(): React.JSX.Element {



  return (
    <>
      <AppContextProvider>
        <GestureHandlerRootView style={{ flex: 1 }}>
          <AppInner />
        </GestureHandlerRootView>
      </AppContextProvider>
    </>

  );

}



function AppInner() {
  const context = useContext(AppContext)

  return (
    <NavigationContainer key={context.token ?? 'no-token'}>
      <RootNavigation />
    </NavigationContainer>
  )
}





export default App;
