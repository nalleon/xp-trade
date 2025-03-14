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
    const pullingInterval = useRef<NodeJS.Timeout | null>(null); 
    
    const handleLogin = async (username : string, password : string) => {
        if(!username || username.trim() === "" || !password || password.trim() === ""){
            return false;
        }
        
        console.log(`${URL_API}/v1/auth/login`);
        try {
            const response = await axios.post(`${URL_API}/v1/auth/login`, {
                    name: username,
                    password: password 
                },
                {
                headers:{
                    'Content-Type': 'application/json'
                }   
                }
            );

            console.log("Respuesta del servidor: ", response.data);
        
            
            if (response.data) {
                try {
                await AsyncStorage.setItem("token", response.data);
                await AsyncStorage.setItem("username", username);
                context.setUsername(username);
                context.setToken(response.data);
                return true;
                } catch(error){
                    console.error("Error al guardar el token: "+  error);
                } 
                
            }
            } catch (error) {
                console.error("Error al iniciar sesión", error);
        }

        return false;
    };

    const handleRegister = async (username : string, email : string, password : string) => {
        if(!username || username.trim() === "" || !password || password.trim() === "" || !email || email.trim() === ""){
            return false;
        }
        
        console.log(`${URL_API}/v1/auth/register`);
        try {
            const response = await axios.post(`${URL_API}/v1/auth/register`, {
                    name: username,
                    email: email,
                    password: password 
                },
                {
                headers:{
                    'Content-Type': 'application/json'
                }   
                }
            );

            
            if (response.data) {
                return true;
            }

        } catch (error) {
            console.error("Error while register", error);
        }

        return false;
    };

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
                await pullStuffHome(gameId);
                return true;
              } catch(error){
                  console.error("Error al guardar el token: "+  error);
                  return false;

              } 
            
          }
          } catch (error) {
              console.error("Error al iniciar sesión", error);
              return false;
          }
        
      }

      const fetchDataHome = async (gameId:number) => {
        if(context.onlineGameId != -1){
            return false;;
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
            return true;
        }
        }
    
      } catch (error) {
        console.log("Error al obtener datos:", error);
      }
      return false;

    }
    
    const pullStuffHome = async (gameId: number) => {
        pullingInterval.current = setInterval(() => {
            fetchDataHome(gameId);
        }, PULLING_INTERVAL);

        return () => {
            if (pullingInterval.current) {
            clearInterval(pullingInterval.current);
            }
        }
    }
    
    const fetchData = async (gameId:number) => {
        if(gameId == -1){
          return;
        }
  
        try {
          const response = await axios.get(`${URL_API}/v2/games/${gameId}`, {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${context.token}`,
            },
          });
      
        let status = response.data.status;
        if (status == 200){
          console.log("OK")
          setGame({...response.data.data});
        }
  
        if(response.data.data.finished == true && context.onlineGameId != -1){
          console.log(response?.data?.data?.finished)
          context.setOnlineGameId(-1);
          stopPulling();
          return true;
        }
  
        } catch (error) {
          console.log("Error al obtener datos:", error);
        }
    }
  
    const pullStuffGame = async (gameId: number) => {
      stopPulling();
      pullingInterval.current = setInterval(() => {
        fetchData(gameId);
      }, PULLING_INTERVAL);
  
    }
  
    const stopPulling = () => {
      if (pullingInterval.current) {
        clearInterval(pullingInterval.current);
        pullingInterval.current = null;
      }
    };
  
    const pressAbandon = async () => {
      if(game.finished){
        context.setOnlineGameId(-1);
        return true;
      }
  
      try {
        console.log(`${URL_API}/v2/games/abandonment/${context.onlineGameId}`);
        const response = 
        await axios.post(`${URL_API}/v2/games/abandonment/${context.onlineGameId}`, {
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
        context.setOnlineGameId(-1);
        return true;
        
        } catch (error) {
            console.error("Error: ", error);
            return false;
        }
    }
  
    const play = async (posX : number, posY : number) => {

      try {
        console.log(`${URL_API}/v2/games/bet/{id}?id=${context.onlineGameId}`);
        const response = 
            await axios.post(`${URL_API}/v2/games/bet/{id}?id=${context.onlineGameId}`, {
                    playername: context.username,
                    posX: posX,
                    posY: posY
                },
                {
                headers:{
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + context.token
                }   
                }
            );
  
            if (response.data){
                return true;
            }
            
        } catch (error) {
            console.error("Error 2", error);
        }
      
    }

    const [game, setGame] = useState<GameOutput>({} as GameOutput);

    
    return {
        handleLogin, handleRegister, fetchDataHome, pullStuffHome, handleGame, 
        game, setGame, play, pressAbandon, pullStuffGame, stopPulling
    }

}

export default UseApi

