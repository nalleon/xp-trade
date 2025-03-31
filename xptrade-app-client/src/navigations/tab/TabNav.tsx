import { StyleSheet, Text, useWindowDimensions, View } from 'react-native'
import React from 'react'
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Icon from 'react-native-vector-icons/Ionicons';
import LoginScreen from '../../screens/LoginScreen';

type Props = {}
const Tab = createBottomTabNavigator();

const TabNav = (props: Props) => {
  const { width, height} = useWindowDimensions();
  const isHorizontal = width > height;
  return (
      <Tab.Navigator id={undefined}
          screenOptions={{
              headerShown:false,
              tabBarShowLabel: false,
              tabBarPosition: isHorizontal ? 'left' : 'bottom',
              tabBarVariant: isHorizontal ? 'material' : 'uikit',
              tabBarLabelPosition: 'below-icon',
          }}
          >   


          <Tab.Screen name='Perfil' component={LoginScreen}
              options={ {tabBarIcon: ({focused}) => 
                  <Icon name={(focused) ? 'person-add' : 'person-add-outline'} size={30}/>
              }
          }/>

          <Tab.Screen name='Colección' component={LoginScreen}
              options={ {tabBarIcon: ({focused}) => 
                  <Icon name={(focused) ? 'bookmarks' : 'bookmarks-outline'} size={30}/>
              }
          }/>

          <Tab.Screen name='Notificaciones' component={LoginScreen}
              options={ {tabBarIcon: ({focused}) => 
                  <Icon name={(focused) ? 'notifications' : 'notifications-outline'} size={30}/>
              }
          }/>
              
      </Tab.Navigator>
  )
}

export default TabNav

