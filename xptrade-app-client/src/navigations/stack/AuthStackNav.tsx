import { StyleSheet, Text, View } from 'react-native'
import React, { useContext } from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../../screens/LoginScreen';
import RegisterScreen from '../../screens/RegisterScreen';
import TabNav from '../tab/TabNav';
import DrawerNav from '../drawer/DrawerNav';
import { AppContext } from '../../context/AppContext';
import LogoutScreen from '../../screens/LogoutScreen';

type Props = {}


export type AuthStackParamList = {
    LoginScreen: undefined,
    RegisterScreen: undefined,
    LogoutScreen: undefined
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
            <Stack.Screen name="LogoutScreen" component={LogoutScreen} />
        </Stack.Navigator>
    )
}

export default AuthStackNav

const styles = StyleSheet.create({})