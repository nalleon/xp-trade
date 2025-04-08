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

export type GameOutput = {
  player1: string;
  player2: string;
  board: string[][];
  finished: boolean;
  turnPlayer: string;
};

export type GameOutputList = {
  id:number,
  player1: string;
  player2: string;
  board: string[][];
  finished: boolean;
  turnPlayer: string;
};

export type GamePlay = {
  playername: string;
  posX: number;
  posY: number;
};




const UseApi = () => {
    const context = useContext(AppContext);
    
    const createPost = async (content: string, game: string, picture: string): Promise<string | null> => {
      if (!content?.trim() || !game?.trim() ) return null;
  
      try {
          const response = await axios.post(`${URL_API}/v2/posts`, {
              game: game,
              content: content,
              picture: picture
          }, {
              headers: { 'Content-Type': 'application/json' }
          });
  
          if (response?.data) {
              return "success";
          }
      } catch (error) {
          console.error("Error al iniciar sesión"); 
      }
  
      return null;
  };

    
    return {
        createPost
    }

}

export default UseApi

