import React, { useEffect, useState } from 'react';
import { ActivityIndicator, View } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import AuthStackNav from './stack/AuthStackNav';
import DrawerNav from './drawer/DrawerNav';

const RootNavigation = () => {
  const [loading, setLoading] = useState(true);
  const [token, setToken] = useState<string | null>(null);

  useEffect(() => {
    const checkToken = async () => {
      const storedToken = await AsyncStorage.getItem('token');
      setToken(storedToken);
      setLoading(false);
    };

    checkToken();
  }, []);

  if (loading) {
    return (
      <View className="flex-1 justify-center items-center bg-[#0F1218]">
        <ActivityIndicator size="large" color="#ffffff" />
      </View>
    );
  }

  return token ? <DrawerNav key="drawer" /> : <AuthStackNav key="auth" />;
};

export default RootNavigation;
