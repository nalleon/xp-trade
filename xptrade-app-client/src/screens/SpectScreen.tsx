import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useEffect, useRef, useState } from 'react'
import { FlatList } from 'react-native-gesture-handler';
import { PULLING_INTERVAL, URL_API } from '../utils/Utils';
import { AppContext } from '../context/AppContext';
import { GameOutput } from '../hooks/UseApi';
import axios from 'axios';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';
import { NativeStackScreenProps } from '@react-navigation/native-stack';

type Props = {}

type AuthProps = NativeStackScreenProps<AuthStackParamList, 'SpectScreen'>;


const SpectScreen = (props: AuthProps) => {
  const [game, setGame] = useState<GameOutput>({} as GameOutput);
  const context = useContext(AppContext);
  const pullingInterval = useRef<NodeJS.Timeout | null>(null); 

  useEffect(() => {
    if(context.spectingGameId != -1){
      pullStuff(context.spectingGameId);
    }else{
      stopPulling();
    }
  }, [context.spectingGameId])

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


      } catch (error) {
        console.log("Error al obtener datos:", error);
      }
  }

  const pullStuff = async (gameId: number) => {
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

  const handleGoBack = async () => {
    context.setSpectingGameId(-1);
    props.navigation.navigate("RemoteHomeScreen");
  }


  return (
    <View style={{flex:1}}>
      <View style={styles.container}>
        {game.turnPlayer && 
          <Text style={styles.turnText}>Current turn is for: {game.turnPlayer}</Text>
        }
        { game && 
          <FlatList
          data={game?.board}
          renderItem={({item: row}) => (
            <View style={styles.row}>
            <FlatList
              data={row}
              numColumns={3}
              renderItem={({item: column}) => (
                  <TouchableOpacity style={styles.cell}>
                    <Text style={styles.text}>{column == '_' ? ' ' : column}</Text>
                  </TouchableOpacity>
                )
              }
            />
            </View>
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

export default SpectScreen

const styles = StyleSheet.create({
  container: {
    marginTop: 150,
    flex: 3,
    justifyContent: "center",
    alignItems: "center"
  },

  cell: {
    flex: 1,
    borderWidth: 1,
    width: 50,
    height: 100,
    borderColor: "#008080",
    justifyContent: "center",
    alignItems: "center",
  },

  row: {
    display: "flex",
    flexDirection: "row",
    height: 100,
    width: 300
  },

  text: {
    fontSize: 50,
    textAlign: "center",
    fontFamily: 'courier'
  },

  turnText:{
    fontSize: 16,
    textAlign: "center",
    fontFamily: 'courier',
    fontWeight: 'bold',
    marginBottom: 10  
  },
    button: {
    width: "80%",
    backgroundColor: "#008080",
    padding: 15,
    borderRadius: 8,
    alignItems: "center",
    margin:10
  },
  buttonText: {
    color: "#fff",
    fontSize: 16,
    fontWeight: "bold",
  },
})