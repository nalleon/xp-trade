import { useEffect, useContext } from 'react';
import { View, Text } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import Icon from 'react-native-vector-icons/Ionicons';
import { AppContext } from '../context/AppContext';
import React from 'react';
import { createNativeStackNavigator, NativeStackScreenProps } from '@react-navigation/native-stack';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';

type Props = NativeStackScreenProps<AuthStackParamList, 'LogoutScreen'>;

const LogoutScreen = ({ navigation }: Props) => {
  const context = useContext(AppContext);

  useEffect(() => {
    const logout = async () => {
      await AsyncStorage.removeItem('token');
      context.setToken(null);
    };

    logout();

  }, []);

  
  return (
    <View
      className="flex-1 justify-center items-center bg-[#0F1218] space-y-4"
    >
      <View className="flex-row items-center space-x-3 p-3 rounded-bl-xl rounded-tr-xl bg-[#442222]">
        <Icon name="log-out-outline" size={24} color="#F6F7F7" />
        <Text className="text-[#F6F7F7] text-lg font-semibold">Cerrando sesión...</Text>
      </View>
    </View>
  );
};

export default LogoutScreen;
