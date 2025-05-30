import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import CollectionScreen from '../../screens/CollectionScreen';
import ProfileScreen from '../../screens/ProfileScreen'
import GameScreen from '../../screens/GameScreen';
import PostScreen from '../../screens/PostScreen';

type Props = {}


export type ProfileStackParamList = {
    ProfileScreen: undefined,
    CollectionScreenProfile: { from?: string };
    PostScreenProfile: { from?: string };
    GameScreenProfile: { from?: string };
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
            <Stack.Screen name="PostScreenProfile" component={PostScreen} />
            <Stack.Screen name="CollectionScreenProfile" component={CollectionScreen} />
            <Stack.Screen name="GameScreenProfile" component={GameScreen} />
        </Stack.Navigator>
    )
}

export default ProfileStackNav