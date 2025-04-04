import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../../screens/LoginScreen';
import RegisterScreen from '../../screens/RegisterScreen';
import InitScreen from '../../screens/InitScreen';

type Props = {}


export type AuthStackParamList = {
    InitScreen: undefined,
    LoginScreen: undefined,
    RegisterScreen: undefined
};

const Stack = createNativeStackNavigator<AuthStackParamList>();

const AuthStackNav = (props: Props) => {
    return (
        <Stack.Navigator id={undefined}
            screenOptions={{
                headerShown: false,
            }}
        >
            <Stack.Screen name="LoginScreen" component={LoginScreen} />
            <Stack.Screen name="RegisterScreen" component={RegisterScreen} />
            <Stack.Screen name="InitScreen" component={InitScreen   } />

        </Stack.Navigator>
    )
}

export default AuthStackNav

const styles = StyleSheet.create({})