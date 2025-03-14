import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../../screens/LoginScreen';
import RegisterScreen from '../../screens/RegisterScreen';
import InitScreen from '../../screens/InitScreen';
import LocalHomeScreen from '../../screens/LocalHomeScreen';
import PlayLocalScreen from '../../screens/PlayLocalScreen';
import PlayRemoteScreen from '../../screens/PlayRemoteScreen';
import RemoteHomeScreen from '../../screens/RemoteHomeScreen';
import SpectScreen from '../../screens/SpectScreen';
import SpectListScreen from '../../screens/SpectListScreen';

type Props = {}


export type AuthStackParamList = {
    InitScreen: undefined,
    LoginScreen: undefined,
    RegisterScreen: undefined,
    LocalHomeScreen: undefined,
    PlayLocalScreen: undefined,
    RemoteHomeScreen: undefined,
    PlayRemoteScreen: undefined,
    SpectListScreen: undefined,
    SpectScreen : undefined
};

const Stack = createNativeStackNavigator<AuthStackParamList>();

const AuthStackNav = (props: Props) => {
    return (
        <Stack.Navigator id={undefined}
        screenOptions={{
            headerShown: false,
        }}
        
        >
            <Stack.Screen name="InitScreen" component={InitScreen} />
            <Stack.Screen name="LoginScreen" component={LoginScreen} />
            <Stack.Screen name="RegisterScreen" component={RegisterScreen} />    
            <Stack.Screen name="LocalHomeScreen" component={LocalHomeScreen} />  
            <Stack.Screen name="PlayLocalScreen" component={PlayLocalScreen} />
            <Stack.Screen name="RemoteHomeScreen" component={RemoteHomeScreen} />  
            <Stack.Screen name="PlayRemoteScreen" component={PlayRemoteScreen} />  
            <Stack.Screen name="SpectListScreen" component={SpectListScreen} />  
            <Stack.Screen name="SpectScreen" component={SpectScreen} />  
        </Stack.Navigator>
    )
}

export default AuthStackNav

const styles = StyleSheet.create({})