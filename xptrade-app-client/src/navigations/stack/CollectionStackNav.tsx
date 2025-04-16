import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../../screens/LoginScreen';
import RegisterScreen from '../../screens/RegisterScreen';
import HomeScreen from '../../screens/HomeScreen';
import CollectionScreen from '../../screens/CollectionScreen';
import GameScreen from '../../screens/GameScreen';

type Props = {}


export type GameStackParamList = {
    CollectionScreen: undefined,
    GameScreen: undefined,
};

const Stack = createNativeStackNavigator<GameStackParamList>();

const CollectionStackNav = (props: Props) => {
    return (
        <Stack.Navigator id={undefined}
            screenOptions={{
                headerShown: false,
            }}
        >
            <Stack.Screen name="CollectionScreen" component={CollectionScreen} />
            <Stack.Screen name="GameScreen" component={GameScreen} />
        </Stack.Navigator>
    )
}

export default CollectionStackNav