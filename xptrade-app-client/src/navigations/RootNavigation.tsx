import React, { useContext, useEffect, useState } from 'react';
import { ActivityIndicator, View } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import AuthStackNav from './stack/AuthStackNav';
import DrawerNav from './drawer/DrawerNav';
import { AppContext } from '../context/AppContext';

const RootNavigation = () => {
  const context = useContext(AppContext);

  return context.token ? <DrawerNav key="drawer" /> : <AuthStackNav key="auth" />;
};


export default RootNavigation;
