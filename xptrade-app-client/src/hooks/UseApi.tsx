import { StyleSheet, Text, View } from 'react-native'
import React, { useContext, useRef, useState } from 'react'
import { TextInput } from 'react-native-gesture-handler';
import AsyncStorage from '@react-native-async-storage/async-storage';
import axios from 'axios';
import { PULLING_INTERVAL, URL_API as URL_API } from '../utils/Utils';
import Icon from 'react-native-vector-icons/Ionicons';
import { AppContext } from '../context/AppContext';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';

const UseApi = () => {

    const context = useContext(AppContext);
    const pullingInterval = useRef<NodeJS.Timeout | null>(null); 
    
    const handleLogin = async (username: string, password: string): Promise<string | null> => {
      if (!username?.trim() || !password?.trim()) return null;
  
      try {
          const response = await axios.post(`${URL_API}/v1/auth/login`, {
              name: username,
              password: password 
          }, {
              headers: { 'Content-Type': 'application/json' }
          });
  
          if (response?.data) {
              await AsyncStorage.multiSet([
                  ["token", response.data],
                  ["username", username]
              ]);
  
              // context.setUsername(username);
              // context.setToken(response.data);
              return "success";
          }
      } catch (error) {
          console.error("Error al iniciar sesión"); 
      }
  
      return null;
  };

    const handleRegister = async (username : string, email : string, password : string) => {
      if (!username?.trim() || !password?.trim() || !email?.trim()) return null;
  
      try {
          const response = await axios.post(`${URL_API}/v1/auth/register`, {
              name: username,
              email: email,
              password: password 
          }, {
              headers: { 'Content-Type': 'application/json' }
          });
  
          if (response?.data) {
              return "success";
          } else {
            return null;
          }

      } catch (error) {
        console.error("Error while registering", error);
      }
      
    };

    
    return {
        handleLogin, handleRegister
    }

}

export default UseApi

