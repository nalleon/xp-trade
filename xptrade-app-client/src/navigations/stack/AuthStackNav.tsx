import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../../screens/LoginScreen';
import RegisterScreen from '../../screens/RegisterScreen';
import TabNav from '../tab/TabNav';
import DrawerNav from '../drawer/DrawerNav';

type Props = {}


export type AuthStackParamList = {
    TabNav: undefined,
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
            <Stack.Screen name="TabNav" component={DrawerNav} />

        </Stack.Navigator>
    )
}

export default AuthStackNav

const styles = StyleSheet.create({})