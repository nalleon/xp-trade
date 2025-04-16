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
            return response.data.results;
          }
        } catch (error) {
            console.error("Error fetching data from the api"); 
        }

      return null;
  };

  //https://api.rawg.io/api/games/2727/movies?key=1a094ebbcbef4440a46abee88f222abb

    const handleGetTrailer = async (search: string) => {
      if (!search?.trim()) return null;
  
      try {
          const response = await axios.get(`https://api.rawg.io/api/games/${search}/movies?key=1a094ebbcbef4440a46abee88f222abb`, {
              headers: { 'Content-Type': 'application/json' }
          });

          if (response?.data) {
            return response.data.results;
          }
        } catch (error) {
            console.error("Error fetching data from the api"); 
        }

      return null;
    };
  
    return {
        handleFetch, handleGetTrailer
    }

}

export default UseRAWGApi

