import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useEffect, useState } from 'react'
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';
import { styles } from './InitScreen';
import { TextInput } from 'react-native-gesture-handler';
import AsyncStorage from '@react-native-async-storage/async-storage';
import axios from 'axios';
import { URL_API as URL_API } from '../utils/Utils';
import Icon from 'react-native-vector-icons/Ionicons';
import { AppContext } from '../context/AppContext';
import UseApi from '../hooks/UseApi';

type Props = {}

type AuthProps = NativeStackScreenProps<AuthStackParamList, 'LoginScreen'>;

const LoginScreen = (props: AuthProps) => {
    const [username, setUsername] = useState<string>("")
    const [password, setPassword] = useState<string>("")

    const context = useContext(AppContext);
    

    const { handleLogin } = UseApi();

    const login = async (username : string, password : string) => {
        const result = handleLogin(username, password);
        if (result){
            props.navigation.navigate('RemoteHomeScreen');
        }
    }





return (
    <View style={styles.container}>
            <Icon name={'person-circle'} color={'#008080'} size={100} style={{marginTop:20}}/>
            <Text style={styles.title}>Login</Text>
            <TextInput
                style={styles.input}
                placeholder="Username"
                value={username}
                onChangeText={setUsername}
                />

            <TextInput
                style={styles.input}
                placeholder="Password"
                secureTextEntry
                value={password}
                onChangeText={setPassword}
            />
            
            <TouchableOpacity style={styles.button} onPress={() => login(username, password)}>
                <Text style={styles.buttonText}>Login</Text>
            </TouchableOpacity>

            <TouchableOpacity  onPress={() => props.navigation.navigate('RegisterScreen')}>
                <Text>Not registered yet? Press here</Text>
            </TouchableOpacity>
        <View style={{flex:2,justifyContent:'flex-end'}}>
            <TouchableOpacity style={styles.button} onPress={() => props.navigation.navigate('InitScreen')}>
            <Text style={styles.buttonText}>Go Back</Text>
            </TouchableOpacity>
        </View>

    </View>
    )
}

export default LoginScreen;
