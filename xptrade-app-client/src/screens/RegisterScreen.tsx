import { Alert, Text, TouchableOpacity, View } from 'react-native'
import React, { useState } from 'react'
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';
import { TextInput } from 'react-native-gesture-handler';
import Icon from 'react-native-vector-icons/Ionicons';
import UseApi from '../hooks/UseApi';
import AuthErrorModal from '../components/auth.modals/AuthErrorModal';
import AuthOKModal from '../components/auth.modals/AuthOKModal';

type Props = NativeStackScreenProps<AuthStackParamList, 'RegisterScreen'>;

const RegisterScreen = ({ navigation }: Props) => {
  const [username, setUsername] = useState<string>("")
  const [email, setEmail] = useState<string>("")
  const [password, setPassword] = useState<string>("")

  const [showErrorModal, setShowErrorModal] = useState(false);
  const [showOKModal, setShowOKModal] = useState(false);

  const { handleRegister } = UseApi();

  const register = async () => {
    const result = await handleRegister(username, email, password);
    if (result) {
      setShowOKModal(true);
    } else {
      setShowErrorModal(true);
    }
  };

  return (
    <View className="flex-1 bg-[#0F1218] items-center justify-center px-6">
      <Icon name="person-add" size={100} color="#66B3B7" className="mb-4" />

      <Text className="text-3xl text-[#F6F7F7] font-bold mb-8">Crear cuenta</Text>

      <TextInput
        className="w-full bg-[#1E222A] text-[#F6F7F7] px-4 py-3 rounded-lg mb-4 text-base"
        placeholder="Nombre de usuario"
        placeholderTextColor="#999"
        value={username}
        onChangeText={setUsername}
      />

      <TextInput
        className="w-full bg-[#1E222A] text-[#F6F7F7] px-4 py-3 rounded-lg mb-4 text-base"
        placeholder="Correo electrónico"
        placeholderTextColor="#999"
        value={email}
        onChangeText={setEmail}
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
        onPress={register}
        className="w-full bg-[#556791] py-3 rounded-lg mb-4"
      >
        <Text className="text-center text-[#F6F7F7] font-semibold text-base">
          Registrarse
        </Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={() => navigation.navigate('LoginScreen')}>
        <Text className="text-[#999] text-sm">
          ¿Ya tienes una cuenta? <Text className="text-[#66B3B7]">Inicia sesión</Text>
        </Text>
      </TouchableOpacity>
      <AuthErrorModal
        visible={showErrorModal}
        onClose={() => setShowErrorModal(false)}
      />
      <AuthOKModal
        visible={showOKModal}
        onClose={() => {
          setShowOKModal(false);
          navigation.replace('LoginScreen');

        }}
        username={username}
      />
    </View>
  );
};

export default RegisterScreen;
