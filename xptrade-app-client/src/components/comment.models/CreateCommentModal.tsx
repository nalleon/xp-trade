import { View, Text, Modal, TouchableOpacity, TextInput, Image, FlatList } from 'react-native';
import React, { useContext, useState } from 'react';
import Icon from 'react-native-vector-icons/Ionicons';
import { Result } from '../../utils/TypeUtils';
import UseRAWGApi from '../../hooks/UseRAWGApi';
import { Asset, launchImageLibrary } from 'react-native-image-picker';
import { REGIONS, SUCCESS } from '../../utils/Utils';
import AsyncStorage from '@react-native-async-storage/async-storage';
import UseApi from '../../hooks/UseApi';
import { AppContext } from '../../context/AppContext';

type Props = {
    visible: boolean;
    onClose: () => void;
};

const CreateCommentModal = ({ visible, onClose }: Props) => {
    const [text, setText] = useState<string>('');
    const context = useContext(AppContext);
    const { handleCreateComment } = UseApi();


    const handleComment = async () => {
        if (!text || text.trim().length == 0) return;

        const usernameXP = await AsyncStorage.getItem('username');

        const inputXPTrade = {
            post:{
                ...context.currentPost
            },
            user: {
                username: usernameXP,
                profilePicture: null
            },
            content: text
        };


        const result = await handleCreateComment(inputXPTrade);

        if (result == SUCCESS) {
            setText('');
        }

        onClose();
    };

    return (
        <Modal visible={visible} animationType="slide" transparent>
            <View className="flex-1 bg-black/50 justify-center items-center px-4">
                <View className="bg-[#1E222A] w-full max-h-[90%] rounded-xl p-4">
                    <View className="flex-row justify-between items-center mb-4">
                        <TouchableOpacity onPress={onClose}>
                            <Icon name="close" size={22} color="#F6F7F7" />
                        </TouchableOpacity>
                        <TouchableOpacity
                            disabled={text.trim().length === 0}
                            onPress={handleComment}
                            className={`px-3 py-1 rounded-full ${text.trim().length === 0 ? 'bg-[#444]' : 'bg-[#9D8D6A]'}`}
                        >
                            <Text className={`text-sm font-semibold ${text.trim().length === 0 ? 'text-[#0F1218]' : 'text-[#F6F7F7]'}`}>
                                Publicar
                            </Text>
                        </TouchableOpacity>

                    </View>

                    <TextInput
                        placeholder="¿Qué estás pensando?"
                        placeholderTextColor="#aaa"
                        value={text}
                        onChangeText={setText}
                        multiline
                        className="bg-[#2C3038] text-white rounded p-3 h-24 mb-3"
                    />
 
                </View>
            </View>
        </Modal>
    );
};

export default CreateCommentModal;
