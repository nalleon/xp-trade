import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../../screens/LoginScreen';
import RegisterScreen from '../../screens/RegisterScreen';
import HomeScreen from '../../screens/HomeScreen';
import SettingsScreen from '../../screens/SettingsScreen';

type Props = {}


export type SettingsStackParamList = {
    InitScreen: undefined,
    LoginScreen: undefined,
    RegisterScreen: undefined
};

const Stack = createNativeStackNavigator<SettingsStackParamList>();

const SettingsStackNav = (props: Props) => {
    return (
        <Stack.Navigator id={undefined}
            screenOptions={{
                headerShown: false,
            }}
        >
            <Stack.Screen name="InitScreen" component={SettingsScreen} />
            <Stack.Screen name="LoginScreen" component={LoginScreen} />
            <Stack.Screen name="RegisterScreen" component={RegisterScreen} />
        </Stack.Navigator>
    )
}

export default SettingsStackNav