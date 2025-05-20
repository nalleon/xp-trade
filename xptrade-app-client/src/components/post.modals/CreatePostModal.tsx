import { View, Text, Modal, TouchableOpacity, TextInput, Image, FlatList } from 'react-native';
import React, { useState } from 'react';
import Icon from 'react-native-vector-icons/Ionicons';
import { Result } from '../../utils/TypeUtils';
import UseRAWGApi from '../../hooks/UseRAWGApi';
import { Asset, launchImageLibrary } from 'react-native-image-picker';
import { REGIONS, SUCCESS } from '../../utils/Utils';
import AsyncStorage from '@react-native-async-storage/async-storage';
import UseApi from '../../hooks/UseApi';

type Props = {
    visible: boolean;
    onClose: () => void;
};

const CreatePostModal = ({ visible, onClose }: Props) => {
    const [text, setText] = useState<string>('');
    const [search, setSearch] = useState('');
    const [games, setGames] = useState<Result[]>([]);
    const [selectedGame, setSelectedGame] = useState<Result | null>(null);
    const [image, setImage] = useState<Asset>();
    const CHARACTER_LIMIT = 250;

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
                    : [];

        const publishers =
            gameDetailed.publishers?.length > 0
                ? gameDetailed.publishers.map((p) => ({ name: p.name }))
                : gameDetailed.developers?.length > 0
                    ? gameDetailed.developers.map((d) => ({ name: d.name }))
                    : [];

        const genres =
            gameDetailed.genres?.length > 0
                ? gameDetailed.genres.map((g) => ({ name: g.name }))
                : gameDetailed.tags?.length > 0
                    ? gameDetailed.tags.map((tag) => ({ name: tag.name }))
                    : [];

        const inputXPTrade = {
            game: {
                title: gameDetailed.name,
                coverArt: gameDetailed.background_image,
                slug: gameDetailed.slug,
                developerInputDTOSet: developers,
                genreInputDTOSet: genres,
                platformInputDTOSet: gameDetailed.platforms?.length > 0
                    ? gameDetailed.platforms.map((p) => ({
                        name: p.platform.name,
                    }))
                    : [],
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
            picture: ""
        };


        const result = await handleCreatePost(inputXPTrade);

        if (result == SUCCESS) {
            setText('');
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
                            disabled={text.trim().length === 0 || !selectedGame || text.length > CHARACTER_LIMIT}
                            onPress={handlePost}
                            className={`px-3 py-1 rounded-full ${text.trim().length === 0 || !selectedGame   || text.length > CHARACTER_LIMIT ? 'bg-[#444]' : 'bg-[#9D8D6A]'}`}
                        >
                            <Text className={`text-sm font-semibold ${text.trim().length === 0 || !selectedGame  || text.length > CHARACTER_LIMIT ? 'text-[#0F1218]' : 'text-[#F6F7F7]'}`}>
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
                                                source={require('../../resources/xp-trade.png')}
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
                                    source={require('../../resources/xp-trade.png')}
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

                    <View className="flex-row justify-end mb-3">
                        <Text className={`text-xs ${text.length > CHARACTER_LIMIT ? 'text-red-400' : 'text-gray-400'}`}>
                            {CHARACTER_LIMIT - text.length}
                        </Text>
                    </View>

                </View>
            </View>
        </Modal>
    );
};

export default CreatePostModal;
