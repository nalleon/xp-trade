import { StyleSheet, Text, useWindowDimensions, View } from 'react-native'
import React from 'react'
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Icon from 'react-native-vector-icons/Ionicons';
import HomeScreen from '../../screens/HomeScreen';
import ProfileScreen from '../../screens/ProfileScreen';
import NotificationsScreen from '../../screens/NotificationsScreen';
import CollectionScreen from '../../screens/CollectionScreen';
import SearchScreen from '../../screens/SearchScreen';
import GameStackNav from '../stack/GameStackNav';

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

        <Tab.Screen name='Home' component={HomeScreen}
              options={ {tabBarIcon: ({focused}) => 
                  <Icon name={(focused) ? 'home' : 'home-outline'} size={30}/>
              }
          }/>

        <Tab.Screen name='Búsqueda' component={GameStackNav}
              options={ {tabBarIcon: ({focused}) => 
                  <Icon name={(focused) ? 'search' : 'search-outline'} size={30}/>
              }
          }/>


          <Tab.Screen name='Colección' component={CollectionScreen}
              options={ {tabBarIcon: ({focused}) => 
                <Icon name={(focused) ? 'game-controller' : 'game-controller-outline'} size={30}/>
            }
        }/>

          <Tab.Screen name='Notificaciones' component={NotificationsScreen}
              options={ {tabBarIcon: ({focused}) => 
                <Icon name={(focused) ? 'notifications' : 'notifications-outline'} size={30}/>
              }
            }/>
              
        <Tab.Screen name='Perfil' component={ProfileScreen}
            options={ {tabBarIcon: ({focused}) => 
                <Icon name={(focused) ? 'person' : 'person-outline'} size={30}/>
            }
        }/>
      </Tab.Navigator>
  )
}

export default TabNav

