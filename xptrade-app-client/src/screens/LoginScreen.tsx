import { Alert, StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useContext, useEffect, useState } from 'react'
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';
import { styles } from './HomeScreen';
import { TextInput } from 'react-native-gesture-handler';
import AsyncStorage from '@react-native-async-storage/async-storage';
import axios from 'axios';
import { SUCCESS, URL_API as URL_API } from '../utils/Utils';
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
        const result = await handleLogin(username, password);
        if (result === SUCCESS){
            props.navigation.replace('TabNav');
        } else {
            Alert.alert("Error", "Usuario o contraseña incorrectos");
        }
    }





return (
    <View style={styles.container}>
            <Icon name={'person-circle'} color={'#008080'} size={100} style={{marginTop:20}}/>
            <Text style={styles.title}>Login</Text>
            <TextInput
                style={styles.input}
                placeholder="Nombre de usuario"
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
                <Text style={styles.buttonText}>Iniciar sesión</Text>
            </TouchableOpacity>

            <TouchableOpacity  onPress={() => props.navigation.navigate('RegisterScreen')}>
                <Text>¿No tienes cuenta? Registrate</Text>
            </TouchableOpacity>

    </View>
    )
}

export default LoginScreen;
