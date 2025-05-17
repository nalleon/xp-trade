import { View, Text, Modal, TouchableOpacity, TextInput, Image, FlatList } from 'react-native';
import React, { useEffect, useState } from 'react';
import Icon from 'react-native-vector-icons/Ionicons';
import { Result } from '../../utils/TypeUtils';
import UseRAWGApi from '../../hooks/UseRAWGApi';
import { Asset, launchImageLibrary } from 'react-native-image-picker';
import { REGIONS, SUCCESS } from '../../utils/Utils';
import AsyncStorage from '@react-native-async-storage/async-storage';
import UseApi from '../../hooks/UseApi';

type Props = {
    visible: boolean;
    username: string,
    onClose: () => void;
};

const AuthOKModal = ({ visible, username, onClose }: Props) => {
    return (
        <Modal visible={visible} animationType="slide" transparent>
            <View className="flex-1 bg-black/50 justify-center items-center px-4">
                <View className="bg-[#1E222A] w-full max-h-[90%] rounded-xl p-4">
                    <View className="flex-row justify-between items-center">
                        <TouchableOpacity onPress={onClose}>
                            <Icon name="close" size={22} color="#F6F7F7" />
                        </TouchableOpacity>
                    </View>
                    <Text className="text-white rounded p-3 h-24">
                        Tu cuenta <Text className="text-[#9D8D6A] font-bold">{username}</Text> se ha creado con éxito. Verifíquela con el correo que le ha sido enviado a la dirección proporcionda para poder acceder.
                    </Text>
                </View>
            </View>
        </Modal>
    );
};

export default AuthOKModal;
