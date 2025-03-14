import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useEffect, useRef, useState } from 'react'
import { FlatList } from 'react-native-gesture-handler';
import axios from 'axios';
import { PULLING_INTERVAL, URL_API } from '../utils/Utils';
import { AppContext } from '../context/AppContext';
import { GameOutput, GameOutputList } from '../hooks/UseApi';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';

type Props = {}

type AuthProps = NativeStackScreenProps<AuthStackParamList, 'SpectListScreen'>;

const SpectListScreen = (props: AuthProps) => {
    const [games, setGames] = useState<GameOutputList[]>({} as GameOutputList[]);
    const context = useContext(AppContext);
  

    useEffect(() => {
        console.log(`${URL_API}/v2/games`);
        const fetchData = async () => {
            try {
              const response = await axios.get(`${URL_API}/v2/games`, {
                headers: {
                  "Content-Type": "application/json",
                  Authorization: `Bearer ${context.token}`,
                },
              });
          
            let status = response.data.status;
            if (status == 200){
              console.log("OK")
              setGames([...response.data.data]);
            }
      
            } catch (error) {
              console.log("Error al obtener datos:", error);
            }
        }

        fetchData();
      
    }, [])
  
    const handleGoBack = async () => {
      props.navigation.navigate("RemoteHomeScreen");
    }
  

    const handleOnPress =  (id:number) => {
        context.setSpectingGameId(id);
      props.navigation.navigate("SpectScreen");
    }
    return (
      <View style={{flex:1}}>        
        <View style={styles.container}>
          { games && 
            <FlatList
            data={games}
            renderItem={({item, index}) => (
                <TouchableOpacity style={styles.listElement} key={index} onPress={() => handleOnPress(item.id)}>
                    <Text>{item.player1} VS {item.player2} -- status: {item.finished ? 'finished' : 'on going'}</Text>
                </TouchableOpacity>
            )
            }
          />
          }
        </View>
  
          <View style={{justifyContent:'flex-end', alignItems: 'center', marginBottom: 70,  flex:1}}>
              <TouchableOpacity style={styles.button} onPress={() => handleGoBack()}>
                <Text style={styles.buttonText}>Go back</Text>
              </TouchableOpacity>
            
          </View>
      </View>
    )
  }
  
  export default SpectListScreen
  
  const styles = StyleSheet.create({
    container: {
      marginTop: 150,
      flex: 3,
      justifyContent: "center",
      alignItems: "center",
      width: "100%",
      paddingHorizontal: 20
    },
  
    listElement: {
      width: "100%", 
      paddingVertical: 10, 
      paddingHorizontal: 15, 
      borderBottomWidth: 1, 
      borderBottomColor: "#008080",
      alignItems: 'center'
    },
  
    text: {
      fontSize: 16, 
      textAlign: 'center'
    },
  
    button: {
      width: "80%",
      backgroundColor: "#008080",
      padding: 15,
      borderRadius: 8,
      alignItems: "center",
      margin: 10,
    },
  
    buttonText: {
      color: "#fff",
      fontSize: 16,
      fontWeight: "bold",
    },
});
  