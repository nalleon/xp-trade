import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../../screens/LoginScreen';
import RegisterScreen from '../../screens/RegisterScreen';
import HomeScreen from '../../screens/HomeScreen';
import SearchScreen from '../../screens/SearchScreen';
import GameScreen from '../../screens/GameScreen';

type Props = {}


export type GameStackParamList = {
    SearchScreen: undefined,
    GameScreen: undefined,
    RegisterScreen: undefined
};

const Stack = createNativeStackNavigator<GameStackParamList>();

const GameStackNav = (props: Props) => {
    return (
        <Stack.Navigator id={undefined}
            screenOptions={{
                headerShown: false,
            }}
        >
            <Stack.Screen name="SearchScreen" component={SearchScreen} />
            <Stack.Screen name="GameScreen" component={GameScreen} />
        </Stack.Navigator>
    )
}

export default GameStackNav