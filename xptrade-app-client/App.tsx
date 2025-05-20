
import 'react-native-gesture-handler';
import { createDrawerNavigator } from '@react-navigation/drawer';
import React, { useEffect, useState } from 'react';
import type {PropsWithChildren} from 'react';
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
import AppContextProvider from './src/context/AppContext';
import RootNavigation from './src/navigations/RootNavigation';









function App(): React.JSX.Element {

 

  return (
    <>
    <GestureHandlerRootView>
      <NavigationContainer >
        <AppContextProvider>
          <RootNavigation />
        </AppContextProvider>
      </NavigationContainer>
    </GestureHandlerRootView>
  </>

  );
  
}
  









export default App;
