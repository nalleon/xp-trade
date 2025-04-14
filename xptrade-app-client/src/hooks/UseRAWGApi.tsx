import { StyleSheet, Text, View } from 'react-native'
import React, { useContext, useRef, useState } from 'react'
import { TextInput } from 'react-native-gesture-handler';
import AsyncStorage from '@react-native-async-storage/async-storage';
import axios from 'axios';
import { PULLING_INTERVAL, RAWG_API, URL_API as URL_API } from '../utils/Utils';
import Icon from 'react-native-vector-icons/Ionicons';
import { AppContext } from '../context/AppContext';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';

const UseRAWGApi = () => {

    const handleFetch = async (search: string) => {
      if (!search?.trim()) return null;
  
      try {
          const response = await axios.get(`${RAWG_API}${search}`, {
              headers: { 'Content-Type': 'application/json' }
          });

          if (response?.data) {
            console.log("RESULTS: " + response.data.results);
            return response.data.results;
          }
        } catch (error) {
            console.error("Error fetching data from the api"); 
        }

      return null;
  };
    
    return {
        handleFetch
    }

}

export default UseRAWGApi

