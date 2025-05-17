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
    post: any,
    onClose: () => void;
};

const UpdatePostModal = ({ visible, post, onClose }: Props) => {
    const [text, setText] = useState<string>(post.content);
    const { handleUpdatePost } = UseApi();
    
    useEffect(() => {
        setText(post?.content ?? '');
    }, [post]);


    const handlePost = async () => {
        if (!text || text.trim().length == 0) return;

        const result = await handleUpdatePost(post?.id, text);

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
                            onPress={handlePost}
                            className={`px-3 py-1 rounded-full ${text.trim().length === 0 ? 'bg-[#444]' : 'bg-[#9D8D6A]'}`}
                        >
                            <Text className={`text-sm font-semibold ${text.trim().length === 0 ? 'text-[#0F1218]' : 'text-[#F6F7F7]'}`}>
                                Editar
                            </Text>
                        </TouchableOpacity>
                    </View>


                    {post?.game && (
                        <View className="flex-row items-center p-1 rounded bg-[#2C3038] mb-2">
                            {post?.game.coverArt && (
                                <Image
                                    source={{ uri: post.game.coverArt }}
                                    className="w-5 h-5 rounded-md mr-4"
                                    resizeMode="cover"
                                />
                            )}

                            <Text className="text-[#F6F7F7] overflow-hidden flex-1" numberOfLines={1}>
                                {post?.game?.title}
                            </Text>
                        </View>
                    )}

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

export default UpdatePostModal;
