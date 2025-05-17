import { View, Text, Modal, TouchableOpacity } from 'react-native';
import React from 'react';
import Icon from 'react-native-vector-icons/Ionicons';

type Props = {
    visible: boolean;
    onClose: () => void;
    onEdit: () => void;
    onDelete: () => void;
};

const PostOptionsModal = ({ visible, onClose, onEdit, onDelete }: Props) => {
    return (
        <Modal visible={visible} transparent animationType="fade">
            <View className="flex-1 bg-black/50 justify-center items-center px-6">
                <View className="bg-[#1E222A] w-full rounded-tl-xl rounded-br-xl p-5 space-y-2">
                    <View className="flex-row justify-between items-center mb-4">
                        <Text className="text-white text-lg font-semibold">Opciones</Text>
                        <TouchableOpacity onPress={onClose}>
                            <Icon name="close" size={22} color="#F6F7F7" />
                        </TouchableOpacity>
                    </View>

                    <TouchableOpacity
                        onPress={() => {
                            onEdit();
                            onClose();
                        }}
                        className="flex-row items-center space-x-3 p-3 rounded-bl-xl rounded-tr-xl bg-[#2C3038]"
                    >
                        <Icon name="create-outline" size={20} color="#F6F7F7" />
                        <Text className="text-[#F6F7F7] text-base">Editar</Text>
                    </TouchableOpacity>

                    <TouchableOpacity
                        onPress={() => {
                            onDelete();
                            onClose();
                        }}
                        className="flex-row items-center space-x-3 p-3 rounded-bl-xl rounded-tr-xl bg-[#442222]"
                    >
                        <Icon name="trash-outline" size={20} color="#F87171" />
                        <Text className="text-[#F87171] text-base">Eliminar</Text>
                    </TouchableOpacity>

                </View>
            </View>
        </Modal>
    );
};

export default PostOptionsModal;
