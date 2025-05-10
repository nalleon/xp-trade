import { View, Text, Modal, TouchableOpacity, TextInput, Image, FlatList } from 'react-native';
import React, { useState } from 'react';
import Icon from 'react-native-vector-icons/Ionicons';
import { Result } from '../utils/TypeUtils';
import UseRAWGApi from '../hooks/UseRAWGApi';
import { Asset, launchImageLibrary } from 'react-native-image-picker';
import { REGIONS, SUCCESS } from '../utils/Utils';
import AsyncStorage from '@react-native-async-storage/async-storage';
import UseApi from '../hooks/UseApi';

type Props = {
    visible: boolean;
    onClose: () => void;
};

const CreatePostModal = ({ visible, onClose }: Props) => {
    const [text, setText] = useState<string>('');
    const [imageUri, setImageUri] = useState<string | null>(null);
    const [search, setSearch] = useState('');
    const [games, setGames] = useState<Result[]>([]);
    const [selectedGame, setSelectedGame] = useState<Result | null>(null);
    const [image, setImage] = useState<Asset>();

    const { handleFetch, handleGameDetailsFetch } = UseRAWGApi();
    const { handleCreatePost } = UseApi();


    const pickImage = async () => {
        launchImageLibrary({ mediaType: "photo" }, (response) => {
            if (response.didCancel) {
                console.log("Selección cancelada");
            } else if (response.errorCode) {
                console.log("Error:", response.errorMessage);
            } else {
                const asset = response.assets[0];
                setImage(asset);
            }
        });
    };


    const searchGames = async () => {
        const result: Result[] | null = await handleFetch(search);
        if (result) {
            const filtered = result.filter(
                (gameDetailed) =>
                    !gameDetailed.tags.some(tag =>
                        ['fangame', 'randomizer', 'doujin', 'doujin-gameDetailed'].includes(tag.name.toLowerCase()) ||
                        ['fangame', 'randomizer', 'doujin', 'doujin-gameDetailed'].includes(tag.slug.toLowerCase())
                    )
            );
            setGames(filtered);
        }
    };

    const handlePost = async () => {
        if (!selectedGame || !text || text.trim().length == 0) return;

        const gameDetailed = await handleGameDetailsFetch(selectedGame.slug);
        const usernameXP = await AsyncStorage.getItem('username');

        const developers =
            gameDetailed.developers?.length > 0
                ? gameDetailed.developers.map((d) => ({ name: d.name }))
                : gameDetailed.publishers?.length > 0
                    ? gameDetailed.publishers.map((p) => ({ name: p.name }))
                    : [{ name: "TBA" }];

        const publishers =
            gameDetailed.publishers?.length > 0
                ? gameDetailed.publishers.map((p) => ({ name: p.name }))
                : gameDetailed.developers?.length > 0
                    ? gameDetailed.developers.map((d) => ({ name: d.name }))
                    : [{ name: "TBA" }];

        const genres =
            gameDetailed.genres?.length > 0
                ? gameDetailed.genres.map((g) => ({ name: g.name }))
                : gameDetailed.tags?.length > 0
                    ? gameDetailed.tags.map((tag) => ({ name: tag.name }))
                    : [{ name: "TBA" }];

        const inputXPTrade = {
            game: {
                title: gameDetailed.name,
                coverArt: gameDetailed.background_image,
                developerInputDTOSet: developers,
                genreInputDTOSet: genres,
                platformInputDTOSet: gameDetailed.platforms?.length > 0
                    ? gameDetailed.platforms.map((p) => ({
                        name: p.platform.name,
                    }))
                    : [{ name: "TBA" }],
                publisherInputDTOSet: publishers,
                regionInputDTOSet: REGIONS.map((region) => ({
                    name: region,
                })),
            },
            user: {
                username: usernameXP,
                profilePicture: null
            },
            content: text,
            picture: imageUri
        };


        const result = await handleCreatePost(inputXPTrade);

        if (result == SUCCESS) {
            setText('');
            setImageUri(null);
            setSelectedGame(null);
            setSearch('');
            setGames([]);
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
                            disabled={text.trim().length === 0 || !selectedGame}
                            onPress={handlePost}
                            className={`px-3 py-1 rounded-full ${text.trim().length === 0 || !selectedGame ? 'bg-[#444]' : 'bg-[#9D8D6A]'}`}
                        >
                            <Text className={`text-sm font-semibold ${text.trim().length === 0 || !selectedGame ? 'text-[#0F1218]' : 'text-[#F6F7F7]'}`}>
                                Publicar
                            </Text>
                        </TouchableOpacity>

                    </View>


                    {!selectedGame && (
                        <>
                            <View className="flex-row items-center bg-[#2C3038] rounded-tl-xl rounded-br-xl px-3  mb-3">
                                <TextInput
                                    className="flex-1 text-[#F6F7F7] text-xs placeholder-[#888] border-none"
                                    placeholder="Buscar juego"
                                    placeholderTextColor="#888"
                                    value={search}
                                    onChangeText={setSearch}
                                />
                                <TouchableOpacity onPress={searchGames} className='mr-2'>
                                    <Icon name="search" size={18} color="#B0B0B0" />
                                </TouchableOpacity>
                            </View>

                            <FlatList
                                data={games}
                                keyExtractor={(item) => item.id.toString()}
                                className="mb-3"
                                style={{ maxHeight: 150 }}
                                renderItem={({ item }) => (
                                    <TouchableOpacity
                                        className="flex-row items-center p-1 rounded bg-[#2C3038] mb-2"
                                        onPress={() => {
                                            setSelectedGame(item);
                                            setGames([]);
                                        }}
                                    >
                                        {item.background_image && (
                                            <Image
                                                source={{ uri: item.background_image }}
                                                className="w-5 h-5 rounded-md mr-4"
                                                resizeMode="cover"
                                            />
                                        )}

                                        {!item.background_image && item.short_screenshots && item.short_screenshots.length > 0 && (
                                            <Image
                                                source={{ uri: item.short_screenshots[0].image }}
                                                className="w-5 h-5 rounded-md mr-4"
                                                resizeMode="cover"
                                            />
                                        )}

                                        {!item.background_image && (!item.short_screenshots || item.short_screenshots.length === 0) && (
                                            <Image
                                                source={require('../resources/xp-trade.png')}
                                                className="w-5 h-5 rounded-md mr-4"
                                                resizeMode="cover"
                                            />
                                        )}

                                        <Text className="text-[#F6F7F7] overflow-hidden" numberOfLines={1}>
                                            {item.name}
                                        </Text>
                                    </TouchableOpacity>
                                )}
                            />

                        </>
                    )}

                    {selectedGame && (
                        <View className="flex-row items-center p-1 rounded bg-[#2C3038] mb-2">
                            {selectedGame.background_image && (
                                <Image
                                    source={{ uri: selectedGame.background_image }}
                                    className="w-5 h-5 rounded-md mr-4"
                                    resizeMode="cover"
                                />
                            )}

                            {!selectedGame.background_image && selectedGame.short_screenshots && selectedGame.short_screenshots.length > 0 && (
                                <Image
                                    source={{ uri: selectedGame.short_screenshots[0].image }}
                                    className="w-5 h-5 rounded-md mr-4"
                                    resizeMode="cover"
                                />
                            )}

                            {!selectedGame.background_image && (!selectedGame.short_screenshots || selectedGame.short_screenshots.length === 0) && (
                                <Image
                                    source={require('../resources/xp-trade.png')}
                                    className="w-5 h-5 rounded-md mr-4"
                                    resizeMode="cover"
                                />
                            )}

                            <Text className="text-[#F6F7F7] overflow-hidden flex-1" numberOfLines={1}>
                                {selectedGame.name}
                            </Text>

                            <TouchableOpacity onPress={() => setSelectedGame(null)}>
                                <Icon name="close-circle" size={22} color="#F77" />
                            </TouchableOpacity>
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
                    {image?.uri && (
                        <View className="relative mb-2 w-full items-end">
                            <Image
                                source={{ uri: image.uri }}
                                className="w-full h-40 rounded"
                                resizeMode="cover"
                            />
                            <TouchableOpacity
                                onPress={() => setImage(undefined)}
                                className="absolute top-1 right-1 bg-black/80 rounded-full p-1"
                            >
                                <Icon name="close" size={16} color="#fff" />
                            </TouchableOpacity>
                        </View>
                    )}

                    <TouchableOpacity onPress={pickImage} className="flex-row items-center space-x-2 mt-2">
                        <Icon name="image-outline" size={24} color="#556791" />
                        <Text className="text-[#556791] font-medium text-sm">Añadir imagen</Text>
                    </TouchableOpacity>


                </View>
            </View>
        </Modal>
    );
};

export default CreatePostModal;
