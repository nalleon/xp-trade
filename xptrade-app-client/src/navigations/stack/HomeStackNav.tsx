import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../../screens/LoginScreen';
import RegisterScreen from '../../screens/RegisterScreen';
import HomeScreen from '../../screens/HomeScreen';
import SearchScreen from '../../screens/SearchScreen';
import PostScreen from '../../screens/PostScreen';
import CommentScreen from '../../screens/CommentScreen';
import NotificationScreen from '../../screens/NotificationScreen';
import GameScreen from '../../screens/GameScreen';
import ProfileScreen from '../../screens/ProfileScreen';
import OtherUserProfileScreen from '../../screens/OtherUserProfileScreen';
import OtherUserCollectionScreen from '../../screens/OtherUserCollectionScreen';

type Props = {}


export type HomeStackParamList = {
    HomeScreen: undefined,
    PostScreen: undefined,
    CommentScreen: undefined,
    OtherUserProfileScreen: undefined,
    OtherUserCollectionScreen: undefined,
    GameScreenHome: undefined,
    
};

const Stack = createNativeStackNavigator<HomeStackParamList>();

const HomeStackNav = (props: Props) => {
    return (
        <Stack.Navigator id={undefined}
            screenOptions={{
                headerShown: false,
            }}
        >
            <Stack.Screen name="HomeScreen" component={HomeScreen} />
            <Stack.Screen name="PostScreen" component={PostScreen} />
            <Stack.Screen name="OtherUserProfileScreen" component={OtherUserProfileScreen} />
            <Stack.Screen name="OtherUserCollectionScreen" component={OtherUserCollectionScreen} />
            <Stack.Screen name="GameScreenHome" component={GameScreen} />
        </Stack.Navigator>
    )
}

export default HomeStackNav