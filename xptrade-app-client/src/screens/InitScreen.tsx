import { Alert, Button, StyleSheet, Text, TextInput, TouchableOpacity, View } from 'react-native'
import React, { useEffect, useState } from 'react'
import { NativeStackScreenProps } from '@react-navigation/native-stack';

import { GameRepository } from '../data/Database';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';




type AuthProps = NativeStackScreenProps<AuthStackParamList, 'InitScreen'>;

function InitScreen(props:AuthProps) {

    return (
      <View style={styles.container}>
        <TouchableOpacity style={styles.button} onPress={() => props.navigation.navigate('LoginScreen')}>
          <Text style={styles.buttonText}>Play online</Text>
        </TouchableOpacity>


        <TouchableOpacity style={styles.button} onPress={() => props.navigation.navigate('LocalHomeScreen')}>
          <Text style={styles.buttonText}>Play Local</Text>
        </TouchableOpacity>
    
      </View>
    );
}


export const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#f4f4f4",
    padding: 20,
  },
  title: {
    fontSize: 24,
    fontWeight: "bold",
    marginBottom: 20,
    color: "#333",
  },
  
  input: {
    width: "100%",
    height: 50,
    backgroundColor: "#fff",
    borderRadius: 8,
    paddingHorizontal: 15,
    marginBottom: 15,
    borderWidth: 1,
    borderColor: "#008080",
  },

  button: {
    width: "100%",
    backgroundColor: "#008080",
    padding: 15,
    borderRadius: 8,
    alignItems: "center",
    margin:10
  },
  buttonText: {
    color: "#fff",
    fontSize: 16,
    fontWeight: "bold",
  },
  flatListElement: {
    padding: 15,
    marginBottom: 10,
    backgroundColor: "#fff",
    borderRadius: 50,
    borderWidth: 1,
    borderColor: "#008080",
    flexDirection: 'row',
    alignItems: 'center',
    elevation: 3, 
  },

  flatListElementText: {
    fontSize: 17,
    flex: 1,
    marginLeft: 25,
    color: '#333',
  },
});

export default InitScreen;