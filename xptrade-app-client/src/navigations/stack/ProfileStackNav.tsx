import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../../screens/LoginScreen';
import RegisterScreen from '../../screens/RegisterScreen';
import HomeScreen from '../../screens/HomeScreen';
import ProfileScreen from '../../screens/ProfileScreen';
import { Collection } from 'typeorm';
import CollectionScreen from '../../screens/CollectionScreen';
import FavoriteScreen from '../../screens/FavoriteScreen';

type Props = {}


export type ProfileStackParamList = {
    HomeScreen: undefined,
    LoginScreen: undefined,
    RegisterScreen: undefined
};

const Stack = createNativeStackNavigator<ProfileStackParamList>();

const ProfileStackNav = (props: Props) => {
    return (
        <Stack.Navigator id={undefined}
            screenOptions={{
                headerShown: false,
            }}
        >
            <Stack.Screen name="HomeScreen" component={ProfileScreen} />
            <Stack.Screen name="RegisterScreen" component={FavoriteScreen} />
            <Stack.Screen name="LoginScreen" component={CollectionScreen} />
        </Stack.Navigator>
    )
}

export default ProfileStackNav