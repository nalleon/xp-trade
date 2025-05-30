import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../../screens/LoginScreen';
import RegisterScreen from '../../screens/RegisterScreen';
import HomeScreen from '../../screens/HomeScreen';
import SearchScreen from '../../screens/SearchScreen';
import PostScreen from '../../screens/PostScreen';
import NotificationScreen from '../../screens/NotificationScreen';

type Props = {}


export type NotificationStackParamList = {
    NotificationScreen: undefined,
    PostScreen: undefined,
    CommentScreen: undefined,
};

const Stack = createNativeStackNavigator<NotificationStackParamList>();

const NotificationStackNav = (props: Props) => {
    return (
        <Stack.Navigator id={undefined}
            screenOptions={{
                headerShown: false,
            }}
        >
            <Stack.Screen name="NotificationScreen" component={NotificationScreen} />
            <Stack.Screen name="PostScreen" component={PostScreen} />
        </Stack.Navigator>
    )
}

export default NotificationStackNav