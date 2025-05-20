import { StyleSheet, useWindowDimensions, View } from 'react-native'
import React from 'react'
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Icon from 'react-native-vector-icons/Ionicons';
import HomeScreen from '../../screens/HomeScreen';
import ProfileScreen from '../../screens/ProfileScreen';
import NotificationScreen from '../../screens/NotificationScreen';
import CollectionScreen from '../../screens/CollectionScreen';
import SearchScreen from '../../screens/SearchScreen';
import GameStackNav, { GameStackParamList } from '../stack/GameStackNav';
import NotificationStackNav from '../stack/NotificationStackNav';
import CollectionStackNav from '../stack/CollectionStackNav';
import HomeStackNav, { HomeStackParamList } from '../stack/HomeStackNav';
import { NavigatorScreenParams } from '@react-navigation/native';
import ProfileStackNav, { ProfileStackParamList } from '../stack/ProfileStackNav';

type Props = {}
const Tab = createBottomTabNavigator();


const TabNav = (props: Props) => {
  const { width, height } = useWindowDimensions();
  const isHorizontal = width > height;

  return (
    <Tab.Navigator
    id={undefined}
    detachInactiveScreens={true}
      screenOptions={{
        headerShown: false,
        tabBarShowLabel: false,
        tabBarPosition: isHorizontal ? 'left' : 'bottom',
        tabBarVariant: isHorizontal ? 'material' : 'uikit',
        tabBarLabelPosition: 'below-icon',
        tabBarStyle: {
          backgroundColor: '#1E222A', 
          borderTopColor: '#9D8D6A',
          height: 50,
        },
        tabBarIconStyle: {
          marginBottom: 0, 
          marginTop: 4,
        },
        tabBarActiveBackgroundColor: '#556791', 
        tabBarInactiveBackgroundColor: '#0F1218', 
        tabBarActiveTintColor: '#F6F7F7', 
        tabBarInactiveTintColor: '#B0B0B0',
      }}
    >
      <Tab.Screen
        name="Home"
        component={HomeStackNav}
        options={{
        
          tabBarIcon: ({ focused, color }) => (
            <Icon
              name={focused ? 'home' : 'home-outline'}
              size={24}
              color={color}
            />
          ),
        }}
      />

      <Tab.Screen
        name="Búsqueda"
        component={GameStackNav}
        options={{
          tabBarIcon: ({ focused, color }) => (
            <Icon
              name={focused ? 'search' : 'search-outline'}
              size={24}
              color={color}
            />
          ),
        }}
      />

      <Tab.Screen
        name="Colección"
        component={CollectionStackNav}
        options={{
          tabBarIcon: ({ focused, color }) => (
            <Icon
              name={focused ? 'game-controller' : 'game-controller-outline'}
              size={24}
              color={color}
            />
          ),
        }}
      />

      <Tab.Screen
        name="Profile"
        component={ProfileStackNav}
        options={{
          tabBarIcon: ({ focused, color }) => (
            <Icon
              name={focused ? 'person' : 'person-outline'}
              size={24}
              color={color}
            />
          ),
        }}
      />
    </Tab.Navigator>
  );
};

export default TabNav;
