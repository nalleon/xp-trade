import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useEffect, useRef, useState } from 'react'
import { styles } from './InitScreen';
import { FlatList } from 'react-native-gesture-handler';
import Icon from 'react-native-vector-icons/Ionicons';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';
import { GameLocalEntity } from '../data/entity/GameLocalEntity';
import { AppContext } from '../context/AppContext';
import { PULLING_INTERVAL as PULLING_INTERVAL, URL_API } from '../utils/Utils';
import axios from 'axios';

type Props = {}

type AuthProps = NativeStackScreenProps<AuthStackParamList, 'RemoteHomeScreen'>;

const RemoteHomeScreen = (props: AuthProps) => {
  const pullingInterval = useRef<NodeJS.Timeout | null>(null); 

  const context = useContext(AppContext);

  const handleGame = async () => {
    try {
      const response = await axios.post(`${URL_API}/v2/games`, {
              name: context.username,
          },
          {
          headers:{
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + context.token
            }   
          }
      );

      console.log("Respuesta del servidor: ", response.data.message);

     
      
      if (response.data) {
          try {
            const gameId: number = parseInt(response.data.message.slice(-2).trim());
            console.log("ID de la partida:", gameId);
            await pullStuff(gameId);
          } catch(error){
              console.error("Error al guardar el token: "+  error);
          } 
        
      }
      } catch (error) {
          console.error("Error al iniciar sesión", error);
      }
    
  }

  const fetchData = async (gameId:number) => {
    if(context.onlineGameId != -1){
      return;
    }
    try {
      const response = await axios.get(`${URL_API}/v2/games/opponent/${gameId}`, {
        params: { name: context.username },
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${context.token}`,
        },
      });
  
    let status = response.data.status;
    if (status == 200){
      if (pullingInterval.current && response.data.data.finished == false) {
        context.setOnlineGameId(gameId);
        props.navigation.navigate("PlayRemoteScreen");
      }
    }

  } catch (error) {
    console.log("Error al obtener datos:", error);
  }
}

  const pullStuff = async (gameId: number) => {
    pullingInterval.current = setInterval(() => {
      fetchData(gameId);
    }, PULLING_INTERVAL);

    return () => {
      if (pullingInterval.current) {
        clearInterval(pullingInterval.current);
      }
    }
  }

  const handleGoBack = () => {
    if (context) {
      context.setToken("");
      context.setUsername("");
      context.setOnlineGameId(-1);
      props.navigation.navigate('InitScreen');
    }
  }
  return (
    <View style={styles.container}>
      <Icon name={'game-controller'} color={'#008080'} size={100} style={{marginTop:20, borderColor: '#008080', borderWidth:3, borderRadius: 100, padding:5}}/>
      <Text style={styles.title}> GAMES </Text>

      <TouchableOpacity style={styles.button} onPress={() => handleGame()}>
          <Text style={styles.buttonText}>Create or join a game</Text>
      </TouchableOpacity>

      <TouchableOpacity style={styles.button} onPress={() => props.navigation.navigate('SpectListScreen')}>
          <Text style={styles.buttonText}>Look at other games</Text>
      </TouchableOpacity>

      <View style={{justifyContent:'flex-end', flex:2}}>
        <TouchableOpacity style={styles.button} onPress={() => handleGoBack()}>
          <Text style={styles.buttonText}>Go Back</Text>
        </TouchableOpacity>
      </View>
    </View>
  )
}
export default RemoteHomeScreen