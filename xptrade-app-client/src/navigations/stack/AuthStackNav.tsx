import { StyleSheet, Text, View } from 'react-native'
import React, { useContext } from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../../screens/LoginScreen';
import RegisterScreen from '../../screens/RegisterScreen';
import TabNav from '../tab/TabNav';
import DrawerNav from '../drawer/DrawerNav';
import { AppContext } from '../../context/AppContext';

type Props = {}


export type AuthStackParamList = {
    LoginScreen: undefined,
    RegisterScreen: undefined
};

const Stack = createNativeStackNavigator<AuthStackParamList>();

const AuthStackNav = (props: Props) => {
    const context = useContext(AppContext);

    return (
        <Stack.Navigator id={undefined}
            screenOptions={{
                headerShown: false,
            }}
        >
            <Stack.Screen name="LoginScreen" component={LoginScreen} />
            <Stack.Screen name="RegisterScreen" component={RegisterScreen} />
        
        </Stack.Navigator>
    )
}

export default AuthStackNav

const styles = StyleSheet.create({})