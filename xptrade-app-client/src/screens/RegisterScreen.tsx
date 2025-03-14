import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useEffect, useState } from 'react'
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';
import { styles } from './InitScreen';
import { TextInput } from 'react-native-gesture-handler';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { URL_API } from '../utils/Utils';
import axios from 'axios';
import Icon from 'react-native-vector-icons/Ionicons';
import { AppContext } from '../context/AppContext';
import UseApi from '../hooks/UseApi';

type Props = {}

type AuthProps = NativeStackScreenProps<AuthStackParamList, 'RegisterScreen'>;


const RegisterScreen = (props: AuthProps) => {
  const [username, setUsername] = useState<string>("")
  const [email, setEmail] = useState<string>("")
  const [password, setPassword] = useState<string>("")
  
  const { handleRegister } = UseApi();



  const register = async (username : string, password : string, email : string) => {
    const result = handleRegister(username, email, password);
    if (result){
        props.navigation.navigate('LoginScreen');
    }
  };



return (
  <View style={styles.container}>
      <Icon name={'person-circle'} color={'#008080'} size={100} style={{marginTop:20}}/>

      <Text style={styles.title}>Register</Text>
      
      <TextInput
          style={styles.input}
          placeholder="Username"
          value={username}
          onChangeText={setUsername}
          />

      <TextInput
          style={styles.input}
          placeholder="Email"
          value={email}
          onChangeText={setEmail}
      />
      
      <TextInput
          style={styles.input}
          placeholder="Password"
          secureTextEntry
          value={password}
          onChangeText={setPassword}
      />
      
      <TouchableOpacity style={styles.button} onPress={() => register(username, password, email)}>
          <Text style={styles.buttonText}>Register</Text>
      </TouchableOpacity>

      <TouchableOpacity  onPress={() => props.navigation.navigate('LoginScreen')}>
          <Text>Already have an account? Press here</Text>
      </TouchableOpacity>
      
      <View style={{flex:2,justifyContent:'flex-end'}}>
            <TouchableOpacity style={styles.button} onPress={() => props.navigation.navigate('InitScreen')}>
            <Text style={styles.buttonText}>Go Back</Text>
            </TouchableOpacity>
        </View>
    </View>
  )
}
export default RegisterScreen

