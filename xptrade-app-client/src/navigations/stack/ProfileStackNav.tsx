import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import CollectionScreen from '../../screens/CollectionScreen';
import FavoriteScreen from '../../screens/FavoriteScreen';
import ProfileScreen from '../../screens/ProfileScreen'
import GameScreen from '../../screens/GameScreen';

type Props = {}


export type ProfileStackParamList = {
    ProfileScreen: undefined,
    CollectionScreen: undefined,
    FavoriteScreen: undefined,
    GameScreen: undefined
};

const Stack = createNativeStackNavigator<ProfileStackParamList>();

const ProfileStackNav = (props: Props) => {
    return (
        <Stack.Navigator id={undefined}
            screenOptions={{
                headerShown: false,
            }}
        >
            <Stack.Screen name="ProfileScreen" component={ProfileScreen} />
            <Stack.Screen name="FavoriteScreen" component={FavoriteScreen} />
            <Stack.Screen name="CollectionScreen" component={CollectionScreen} />
            <Stack.Screen name="GameScreen" component={GameScreen} />
        </Stack.Navigator>
    )
}

export default ProfileStackNav