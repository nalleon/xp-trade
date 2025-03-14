import { FlatList, StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useEffect, useState } from 'react'
import Cell from '../model/Cell'
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import UseGameLogic from '../hooks/UseGameLogic';
import { GameLocalEntity } from '../data/entity/GameLocalEntity';
import { GameRepository } from '../data/Database';
import { AppContext } from '../context/AppContext';

type Props = {}

type AuthProps = NativeStackScreenProps<AuthStackParamList, 'PlayLocalScreen'>;

const PlayLocalScreen = (props: AuthProps) => {

  const { restart, createBoard, play, cells, setCells, restoreBoard } = UseGameLogic();

  const context = useContext(AppContext);

  const [id, setId] = useState<number>(-1);

  useEffect(() => {
    createBoard();
  }, [])

  useEffect(() => {  
    if(context.currentLocalGameId != -1){
      const getGame = async () => {
        const currentGame : GameLocalEntity = await GameRepository.findOneBy({id: context.currentLocalGameId});
        let aux : Cell[][]= JSON.parse(currentGame.board);
        
        console.log(aux);      
        restoreBoard(aux);
        setId(context.currentLocalGameId);
      }
      getGame();
    } 
  }, [id])
  

  
  const handleCreate = async () => {
    let newGame : GameLocalEntity = new GameLocalEntity();
    newGame.dateCreation = new Date();

    newGame.board = JSON.stringify(cells);
    let currentGame : GameLocalEntity = await GameRepository.save(newGame);
    
    context.setCurrentLocalGameId(-1);
    setId(-1);
    props.navigation.navigate('LocalHomeScreen');
  }


  const handleUpdate = async () => {
    let currentGame : GameLocalEntity = await GameRepository.findOneBy({id: context.currentLocalGameId});
    currentGame.board = JSON.stringify(cells);

    let updatedGame : GameLocalEntity = await GameRepository.save(currentGame);
    
    context.setCurrentLocalGameId(-1);
    setId(-1);
    props.navigation.navigate('LocalHomeScreen');
  }
  
  return (
    <View style={{flex:1}}>
      <View style={styles.container}>
        {
          cells.map((row, rowIndex) =>
            <View style={styles.row} key={rowIndex}>
              {row.map((cell, cellIndex) =>
                  <TouchableOpacity onPress={() => play(rowIndex, cellIndex)} style={styles.cell} key={rowIndex + "_" + cellIndex}>
                      <Text style={styles.text}>{cell.getValue()}</Text>
                  </TouchableOpacity>
              )}
            </View>
        )}
        </View>
        <View style={{justifyContent:'flex-end', alignItems: 'center', marginBottom: 70,  flex:1}}>
          <TouchableOpacity style={styles.button} onPress={() => restart()}>
            <Text style={styles.buttonText}>Restart</Text>
          </TouchableOpacity>
          { context.currentLocalGameId == -1 ? 
            <TouchableOpacity style={styles.button} onPress={() => handleCreate()}>
              <Text style={styles.buttonText}>Save for later (create)</Text>
            </TouchableOpacity>
            :
            <TouchableOpacity style={styles.button} onPress={() => handleUpdate()}>
              <Text style={styles.buttonText}>Save for later (update)</Text>
            </TouchableOpacity>
          }
        </View>
    </View>
  )
}

export default PlayLocalScreen

const styles = StyleSheet.create({
  container: {
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