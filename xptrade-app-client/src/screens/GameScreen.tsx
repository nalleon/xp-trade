import { Alert, Image, ScrollView, TouchableOpacity } from 'react-native';
import { Text, View } from 'react-native';
import React, { useContext, useEffect, useState } from 'react';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { GameStackParamList } from '../navigations/stack/GameStackNav';
import { AppContext } from '../context/AppContext';
import Icon from 'react-native-vector-icons/Ionicons';
import { ImageZoom } from '@likashefqet/react-native-image-zoom';
import ScreenshotGallery from '../components/ScreenshotGallery';
import UseApi from '../hooks/UseApi';
import { Result, Screenshot, XPTradeInputGame } from '../utils/TypeUtils';
import { GameDetails } from '../utils/GameDetailsType';
import UseRAWGApi from '../hooks/UseRAWGApi';
import PlatformModal from '../components/PlatformModal';
import RegionModal from '../components/RegionModal';
import { REGIONS, SUCCESS } from '../utils/Utils';
import AsyncStorage from '@react-native-async-storage/async-storage';
import AddToFavoritesModal from '../components/favorite.modals/AddToFavoritesModal';
import DeleteFromFavoritesModal from '../components/favorite.modals/DeleteFromFavoritesModal';
import { platform } from 'os';

type Props = NativeStackScreenProps<GameStackParamList, 'GameScreen'>;

const GameScreen = (props: Props) => {
  const [showPublishers, setShowPublishers] = useState(false);
  const [showDevelopers, setShowDevelopers] = useState(false);
  const [showPlatformModal, setShowPlatformModal] = useState(false);
  const [showRegionModal, setShowRegionModal] = useState(false);
  const [showAddToFavoritesModal, setShowAddToFavoritesModal] = useState(false);
  const [showDeleteFromFavoritesModal, setShowDeleteFromFavoritesModal] = useState(false);

  const [selectedPlatform, setSelectedPlatform] = useState<string>();
  const [selectedRegion, setSelectedRegion] = useState<string>();
  const [isScrollEnabled, setIsScrollEnabled] = useState(true);
  const [isFavorite, setIsFavorite] = useState(false);
  const [screenshots, setScreenshots] = useState<Screenshot[]>([]);
  const { currentGame, currentGameDetailed, username } = useContext(AppContext);

  const { handleAddToCollection, handleAddToFavorite, handleCheckIfExistsFavorites, handleDeleteFromFavorites } = UseApi();

  /**
   * UseEffect 
   */


  useEffect(() => {
    checkIfIsFavorites();
  }, [isFavorite]);



  /**
     * Function to check if a game is in favorites
     */
  const checkIfIsFavorites = async () => {
    const usernameXP = await AsyncStorage.getItem('username');
    const title = currentGameDetailed.name;

    const result = await handleCheckIfExistsFavorites(usernameXP, title);

    if (result != null) {
      setIsFavorite(true);
    }
  }

  /**
   * Function to delete a game from favorites
   */
  const deleteFromFavorites = async () => {
    const usernameXP = await AsyncStorage.getItem('username');
    const title = currentGameDetailed.name;

    const result = await handleDeleteFromFavorites(usernameXP, title);

    if (result != null) {
      setIsFavorite(!isFavorite);
      setShowDeleteFromFavoritesModal(true);
    }
  }

  const addToCollection = async (game: GameDetails) => {
    if (!game) {
      return;
    }

    const developers =
      game.developers?.length > 0
        ? game.developers.map((d) => ({ name: d.name }))
        : game.publishers?.length > 0
          ? game.publishers.map((p) => ({ name: p.name }))
          : [];

    const publishers =
      game.publishers?.length > 0
        ? game.publishers.map((p) => ({ name: p.name }))
        : game.developers?.length > 0
          ? game.developers.map((d) => ({ name: d.name }))
          : [];

    const genres =
      game.genres?.length > 0
        ? game.genres.map((g) => ({ name: g.name }))
        : [];

    const tags =
      game.tags?.length > 0
        ? game.tags.map((tag) => ({ name: tag.name }))
        : [];



    const inputXPTrade = {
      game: {
        title: game.name,
        coverArt: game.background_image,
        slug: game.slug,
        rating: game.rating,
        released: game.released,
        tagInputDTOSet: tags,
        developerInputDTOSet: developers,
        genreInputDTOSet: genres,
        platformInputDTOSet: game.platforms?.length > 0
          ? game.platforms.map((p) => ({
            name: p.platform.name,
          }))
          : [],
        publisherInputDTOSet: publishers,
        regionInputDTOSet: REGIONS.map((region) => ({
          name: region,
        })),
      },
      region: {
        name: selectedRegion,
      },
      platform: {
        name: selectedPlatform,
      }
    };

    await handleAddToCollection(username, inputXPTrade);

    setIsScrollEnabled(true);
  }

  const addToFavorite = async (game: GameDetails) => {
    if (!game) return;

    const usernameXP = await AsyncStorage.getItem('username');

    const developers =
      game.developers?.length > 0
        ? game.developers.map((d) => ({ name: d.name }))
        : game.publishers?.length > 0
          ? game.publishers.map((p) => ({ name: p.name }))
          : [];

    const publishers =
      game.publishers?.length > 0
        ? game.publishers.map((p) => ({ name: p.name }))
        : game.developers?.length > 0
          ? game.developers.map((d) => ({ name: d.name }))
          : [];

    const genres =
      game.genres?.length > 0
        ? game.genres.map((g) => ({ name: g.name }))
        : [];

    const tags =
      game.tags?.length > 0
        ? game.tags.map((tag) => ({ name: tag.name }))
        : [];

    const inputXPTrade = {
      game: {
        title: game.name,
        coverArt: game.background_image,
        slug: game.slug,
        rating: game.rating,
        released: game.released,
        tagInputDTOSet: tags,
        developerInputDTOSet: developers,
        genreInputDTOSet: genres,
        platformInputDTOSet: game.platforms?.length > 0
          ? game.platforms.map((p) => ({
            name: p.platform.name,
          }))
          : [],
        publisherInputDTOSet: publishers,
      },
      user: {
        username: usernameXP,
        profilePicture: '',
      },
    };


    const result = await handleAddToFavorite(inputXPTrade);

    if (result == SUCCESS) {
      setIsFavorite(true);
      setShowAddToFavoritesModal(true);
    }

    setIsScrollEnabled(true);
  };

  /**
   * Function to block the scroll of the screen
   * @param isModalOpen true if any of the modals is open, false otherwise
   */
  const toggleScroll = (isModalOpen: boolean) => {
    setIsScrollEnabled(!isModalOpen);
  };

  /**
   * Function to handle the press on add to collection
   */
  const handlePressAddToCollection = () => {
    setShowPlatformModal(true);
    toggleScroll(true);
  };

  /**
   * Function to handle finishing se lecting platforms
   */
  const handlePlatformSelectionDone = () => {
    setShowPlatformModal(false);
    toggleScroll(true);
    setTimeout(() => setShowRegionModal(true), 200);
  };

  /**
   *  Function to handle finishing selecting regions
   */
  const handleRegionSelectionDone = () => {
    setShowRegionModal(false);
    setTimeout(() => {
      addToCollection(currentGameDetailed);
      setSelectedPlatform('');
      setSelectedRegion('');
    }, 200);
  };


  return (
    <ScrollView className="flex-1 bg-[#0F1218]" scrollEnabled={isScrollEnabled}
      contentContainerStyle={{ flexGrow: 1 }}
    >
      {
        currentGameDetailed?.background_image &&
        <Image
          source={{ uri: currentGameDetailed?.background_image }}
          className="w-full h-60"
          resizeMode="cover"
        />
      }

      {
        !currentGameDetailed?.background_image && screenshots && screenshots.length > 0 &&
        <Image
          source={{ uri: screenshots[0].image }}
          className="w-full h-60"
          resizeMode="cover"
        />
      }

      {
        !currentGameDetailed?.background_image && (!screenshots || screenshots.length === 0) && (
          <Image
            source={require('../resources/xp-trade.png')}
            className="w-full h-60"
            resizeMode="cover"
          />
        )
      }

      <View className="p-4">
        <Text className="text-[#F6F7F7] text-2xl font-bold mb-2">
          {currentGameDetailed.name}
        </Text>

        <View className="flex-row justify-between items-center mb-4">
          <View className="flex-row items-center space-x-6">
            <View className="flex-row items-center space-x-1">
              <Icon name="star" size={16} color="#9D8D6A" />
              <Text className="text-[#F6F7F7]">{currentGameDetailed.rating.toFixed(1)} / 5</Text>
            </View>
            <View className="flex-row items-center space-x-1">
              <Icon name="calendar-outline" size={16} color="#999" />
              <Text className="text-[#999]">{currentGameDetailed.released}</Text>
            </View>
          </View>

          <View className="flex-row space-x-4">
            <TouchableOpacity
              onPress={handlePressAddToCollection}
              className="p-2"
            >
              <Icon name="add-circle-outline" size={22} color="#F6F7F7" />
            </TouchableOpacity>

            {
              isFavorite == false ?
                <TouchableOpacity
                  onPress={() => addToFavorite(currentGameDetailed)}
                  className="p-2"
                >
                  <Icon name="heart-outline" size={22} color="#F6F7F7" />
                </TouchableOpacity>

                :
                <TouchableOpacity
                  onPress={() => deleteFromFavorites()}
                  className="p-2"
                >
                  <Icon name="heart" size={22} color="#cd776c" />
                </TouchableOpacity>
            }
          </View>
        </View>

        <View className="h-px bg-[#2C3038] mb-6" />


        <View className="flex-row justify-between gap-4 mb-4">

          <View className="flex-1">
            <TouchableOpacity
              onPress={() => setShowDevelopers(prev => !prev)}
              className="flex-row items-center mb-1"
            >
              <Text className="text-[#F6F7F7] font-semibold mr-2">Desarrolladora(s)</Text>
              <Icon
                name={showDevelopers ? 'caret-up-outline' : 'caret-down-outline'}
                size={16}
                color="#F6F7F7"
              />
            </TouchableOpacity>
            {showDevelopers && (
              <View className="ml-2">
                {currentGameDetailed?.developers.map((dev) => (
                  <Text key={dev.id} className="text-[#ccc] mb-1">
                    {dev.name}
                  </Text>
                ))}
              </View>
            )}
          </View>
          <View className="flex-1">
            <TouchableOpacity
              onPress={() => setShowPublishers(prev => !prev)}
              className="flex-row items-center mb-1"
            >
              <Text className="text-[#F6F7F7] font-semibold mr-2">Distribuidora(s)</Text>
              <Icon
                name={showPublishers ? 'caret-up-outline' : 'caret-down-outline'}
                size={16}
                color="#F6F7F7"
              />
            </TouchableOpacity>
            {showPublishers && (
              <View className="ml-2">
                {currentGameDetailed?.publishers.map((pub) => (
                  <Text key={pub.id} className="text-[#ccc] mb-1">
                    {pub.name}
                  </Text>
                ))}
              </View>
            )}
          </View>
        </View>

        {currentGameDetailed.platforms?.length > 0 &&
          <View className="mb-1">
            <Text className="text-[#F6F7F7] font-semibold mb-1">Plataformas:</Text>
            <Text className="text-[#ccc]">
              {currentGameDetailed.platforms.map(p => p.platform.name).join(', ')}
            </Text>
          </View>
        }

        {(currentGameDetailed.genres?.length > 0 || currentGameDetailed?.tags?.length > 0 || currentGameDetailed?.genres?.length > 0) && (
          <View className="mb-6">
            <View className="flex-row items-center justify-center my-6">
              <View className="flex-1 h-px bg-[#3A3F4A]" />
              <Text className="mx-3 text-[#999] text-xs uppercase">ETIQUETAS</Text>
              <View className="flex-1 h-px bg-[#3A3F4A]" />
            </View>


            <View className="flex-row flex-wrap justify-between gap-2">
              {(currentGameDetailed.genres.length > 0 ? currentGameDetailed?.genres : currentGameDetailed.genres)?.map((genre) => (
                <View
                  key={`genre-${genre.id}`}
                  className="bg-[#1E222A] px-3 py-1 rounded-full mb-2 flex-grow"
                >
                  <Text
                    className="text-[#9D8D6A] text-xs text-center"
                    numberOfLines={1}
                    ellipsizeMode="tail"
                  >
                    {genre.name}
                  </Text>
                </View>
              ))}

              {currentGameDetailed?.tags
                .filter((tag) => tag.language !== 'rus')
                .filter((tag) => {
                  const genres = currentGameDetailed?.genres?.length > 0 ? currentGameDetailed.genres : currentGameDetailed.genres;
                  return !genres.some((genre) => genre.name.toLowerCase() === tag.name.toLowerCase());
                })
                .slice(0, 10)
                .map((tag) => (
                  <View
                    key={`tag-${tag.id}`}
                    className="bg-[#1E222A] px-3 py-1 rounded-full mb-2 flex-grow"
                  >
                    <Text
                      className="text-[#66B3B7] text-xs capitalize text-center"
                      numberOfLines={1}
                      ellipsizeMode="tail"
                    >
                      {tag.name}
                    </Text>
                  </View>
                ))}
            </View>
          </View>
        )}


        <ScreenshotGallery id={currentGameDetailed.id} />


      </View>
      <PlatformModal
        showModal={showPlatformModal}
        platforms={currentGameDetailed.platforms}
        selectedPlatform={selectedPlatform}
        setSelectedPlatform={setSelectedPlatform}
        handlePlatformSelectionDone={() =>
          handlePlatformSelectionDone()
        }
      />
      <RegionModal
        showModal={showRegionModal}
        selectedRegion={selectedRegion}
        setSelectedRegion={setSelectedRegion}
        handleRegionSelectionDone={() =>
          handleRegionSelectionDone()
        }
      />
      <AddToFavoritesModal
        visible={showAddToFavoritesModal}
        game={currentGameDetailed}
        onClose={() => setShowAddToFavoritesModal(false)}
      />
      <DeleteFromFavoritesModal
        visible={showDeleteFromFavoritesModal}
        game={currentGameDetailed}
        onClose={() => setShowDeleteFromFavoritesModal(false)}
      />

    </ScrollView>
  );
};

export default GameScreen;
