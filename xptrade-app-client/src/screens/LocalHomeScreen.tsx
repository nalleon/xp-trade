import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useEffect, useState } from 'react'
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';
import { styles } from './InitScreen';
import Icon from 'react-native-vector-icons/Ionicons';
import { FlatList } from 'react-native-gesture-handler';
import { GameRepository } from '../data/Database';
import { GameLocalEntity } from '../data/entity/GameLocalEntity';
import { AppContext } from '../context/AppContext';

type Props = {}

type AuthProps = NativeStackScreenProps<AuthStackParamList, 'LocalHomeScreen'>;

const LocalHomeScreen = (props: AuthProps) => {
  const [data, setData] = useState<GameLocalEntity[]>([]);
  
  const context = useContext(AppContext);

  useEffect(() => {
    fetchData();
  }, [])

  const fetchData = async () => {
    const list = await GameRepository.find();
    setData([...list]);
  }

  const handleContinueGame = (id: number) => {
      context.setCurrentLocalGameId(id);
      props.navigation.navigate('PlayLocalScreen');
  }


  const handleGame = async () => {
    context.setIsFinished(false);
    props.navigation.navigate('PlayLocalScreen');
  }

  return (
    <View style={styles.container}>
      <Icon name={'game-controller'} color={'#008080'} size={100} style={{marginTop:20, borderColor: '#008080', borderWidth:3, borderRadius: 100, padding:5}}/>
      <Text style={styles.title}> GAMES</Text>


      <FlatList
        data={data}
        renderItem={({ item }) => (
            <TouchableOpacity onPress={() => handleContinueGame(item?.id)}>
                <Text style={styles.flatListElementText}>{item?.dateCreation?.toString()} - {item?.id} </Text>
            </TouchableOpacity>
        )}
        keyExtractor={(item, index) => item?.dateCreation + "_" + item.id + "_" + index}
        style={{marginTop:20}}
      />

      <TouchableOpacity style={styles.button} onPress={() => handleGame()}>
          <Text style={styles.buttonText}>Create a new game</Text>
      </TouchableOpacity>

      <View style={{justifyContent:'flex-end', flex:2}}>
        <TouchableOpacity style={styles.button} onPress={() => props.navigation.navigate('InitScreen')}>
          <Text style={styles.buttonText}>Go Back</Text>
        </TouchableOpacity>
      </View>
    </View>
  )
}

export default LocalHomeScreen

