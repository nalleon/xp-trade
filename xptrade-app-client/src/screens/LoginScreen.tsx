import { Alert, Text, TouchableOpacity, View } from 'react-native'
import React, { useState } from 'react'
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';
import { TextInput } from 'react-native-gesture-handler';
import Icon from 'react-native-vector-icons/Ionicons';
import UseApi from '../hooks/UseApi';
import { SUCCESS } from '../utils/Utils';
import AuthErrorModal from '../components/auth.modals/AuthErrorModal';

type Props = NativeStackScreenProps<AuthStackParamList, 'LoginScreen'>;

const LoginScreen = ({ navigation }: Props) => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const [showErrorModal, setShowErrorModal] = useState(false);
  const { handleLogin } = UseApi();

  const login = async () => {
    const result = await handleLogin(username, password);
    if (result != SUCCESS) {
      setShowErrorModal(true);
    }
  };

  return (
    <View className="flex-1 bg-[#0F1218] items-center justify-center px-6">
      <Icon name="person-circle" size={100} color="#66B3B7" className="mb-4" />

      <Text className="text-3xl text-[#F6F7F7] font-bold mb-8">Iniciar sesión</Text>

      <TextInput
        className="w-full bg-[#1E222A] text-[#F6F7F7] px-4 py-3 rounded-lg mb-4 text-base"
        placeholder="Nombre de usuario"
        placeholderTextColor="#999"
        value={username}
        onChangeText={setUsername}
      />

      <TextInput
        className="w-full bg-[#1E222A] text-[#F6F7F7] px-4 py-3 rounded-lg mb-6 text-base"
        placeholder="Contraseña"
        placeholderTextColor="#999"
        secureTextEntry
        value={password}
        onChangeText={setPassword}
      />

      <TouchableOpacity
        onPress={login}
        className="w-full bg-[#556791] py-3 rounded-lg mb-4"
      >
        <Text className="text-center text-[#F6F7F7] font-semibold text-base">
          Iniciar sesión
        </Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={() => navigation.navigate('RegisterScreen')}>
        <Text className="text-[#999] text-sm">¿No tienes cuenta? <Text className="text-[#66B3B7]">Regístrate</Text></Text>
      </TouchableOpacity>

      <AuthErrorModal
        visible={showErrorModal}
        onClose={()=> setShowErrorModal(false)}
      />
        
    </View>
  );
};

export default LoginScreen;
