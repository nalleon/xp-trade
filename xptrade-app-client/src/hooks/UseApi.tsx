import { StyleSheet, Text, View } from 'react-native'
import React, { useContext, useRef, useState } from 'react'
import { TextInput } from 'react-native-gesture-handler';
import AsyncStorage from '@react-native-async-storage/async-storage';
import axios from 'axios';
import { PULLING_INTERVAL, SUCCESS, URL_API as URL_API } from '../utils/Utils';
import Icon from 'react-native-vector-icons/Ionicons';
import { AppContext } from '../context/AppContext';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AuthStackParamList } from '../navigations/stack/AuthStackNav';
import { userInfo } from 'os';
import { XPTradeInputGame, XPTradeInputGameCollection } from '../utils/TypeUtils';

const UseApi = () => {

    const context = useContext(AppContext);
    const pullingInterval = useRef<NodeJS.Timeout | null>(null);

    /**
     * Function to login
     * @param username of the user
     * @param password of the user
     * @returns success if login is okay, null otherwise
     */
    const handleLogin = async (username: string, password: string): Promise<string | null> => {
        if (!username?.trim() || !password?.trim()) return null;

        try {
            const response = await axios.post(`${URL_API}/v1/auth/login`, {
                name: username,
                password: password
            }, {
                headers: { 'Content-Type': 'application/json' }
            });

            if (response?.data) {
                await AsyncStorage.multiSet([
                    ["token", response.data],
                    ["username", username]
                ]);

                context.setUsername(username);
                context.setToken(response.data)

                console.log(context.username);

                return "success";
            }
        } catch (error) {
            console.error("Error al iniciar sesión");
        }

        return null;
    };

    /**
     * Function to get an user name and pfp's name
     * @param username of the user
     * @returns success if petition is okay, null otherwise
     */
    const getUser = async (username: string): Promise<string | null> => {
        if (!username?.trim()) return null;

        try {
            const response = await axios.get(`${URL_API}/v2/users/username/${username}`, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + context.token
                }
            });

            if (response?.data) {
                await AsyncStorage.multiSet([
                    ["username", response.data.username],
                    ["pfp", response.data.profilePicture]
                ]);

                return "success";
            }
        } catch (error) {
            console.error("Error al iniciar sesión");
        }

        return null;
    };


    //TODO
    const getUserPfp = async (pfpName: string): Promise<string | null> => {
        if (!pfpName?.trim()) return null;


        try {
            const response = await axios.get(`${URL_API}/v2/users/images/${pfpName}`, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + context.token
                }
            });

            if (response?.data) {
                await AsyncStorage.multiSet([
                    ["username", response.data.username],
                    ["pfp", response.data.profilePicture]
                ]);


                return "success";
            }
        } catch (error) {
            console.error("Error al iniciar sesión");
        }

        return null;
    };

    /**
     * Function to register an user 
     * @param username of the user
     * @param email of the user
     * @param password of the user
     * @returns success if registration is okay, null otherwise
     */
    const handleRegister = async (username: string, email: string, password: string) => {
        if (!username?.trim() || !password?.trim() || !email?.trim()) return null;

        try {
            const response = await axios.post(`${URL_API}/v1/auth/register`, {
                name: username,
                email: email,
                password: password
            }, {
                headers: { 'Content-Type': 'application/json' }
            });

            if (response?.data) {
                return "success";
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while registering", error);
        }

    };

    /**
     * Function to add a game to the collection
     * @param username of the user
     * @param game game to add
     * @returns 
     */
    const handleAddToCollection = async (username: string, game: XPTradeInputGameCollection) => {
        if (!username?.trim() || !game) return null;

        console.log("PRUEBA", game);

        try {
            const response = await axios.post(`${URL_API}/v2/collections/${username}`, {
                game,
            },
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + context.token
                    },
                });

            if (response?.data) {
                return response?.data.data;
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while fetching collections", error);
        }

    };


    /**
     * Function to get an user favorite games
     * @param username of the user
     * @returns the favorites
     */
    const handleGetFavorites = async (username: string) => {
        if (!username?.trim()) return null;

        try {
            const response = await axios.get(`${URL_API}/v2/favorites/users/${username}`,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + context.token
                    },
                });

            if (response?.data) {
                return response?.data.data;
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while fetching favorites", error);
        }
    };

    /**
    * Function to check if an user has a game in favorites
    * @param username of the user
    * @param title of the game
    * @returns success if exists, null if not
    */
    const handleCheckIfExistsFavorites = async (username: string, title: string) => {
        if (!username?.trim() || !title?.trim()) return null;

        try {
            const response = await axios.get(`${URL_API}/v2/favorites/users/${username}/games/${title}`,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + context.token
                    },
                });

            if (response?.data) {
                return response?.data.data;
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while fetching favorites", error);
        }

    };

    /**
    * Function to check if an user has a game in favorites
    * @param username of the user
    * @param title of the game
    * @returns success if exists, null if not
    */
    const handleDeleteFromFavorites = async (username: string, title: string) => {
        if (!username?.trim() || !title?.trim()) return null;

        const result = await handleCheckIfExistsFavorites(username, title);
        if (result == null) {
            return null;
        }

        try {
            const response = await axios.delete(`${URL_API}/v2/favorites/${result?.id}`,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + context.token
                    },
                });

                console.log(response);
            if (response?.status === 204) {
                return SUCCESS;
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while fetching favorites", error);
        }

    };

    /**
     * Function to get an user games' collection
     * @param username of the user
     * @returns the favorites
     */
    const handleGetCollection = async (username: string) => {
        if (!username?.trim()) return null;

        try {
            const response = await axios.get(`${URL_API}/v2/collections/users/${username}`,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + context.token
                    },
                });

            if (response?.data) {
                return response?.data;
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while fetching collections", error);
        }

    };

    /**
     * Function to add a game to the favorites list
     * @param username of the user
     * @param inputXPTrade game to add
     * @returns 
     */
    const handleAddToFavorite = async (inputXPTrade: XPTradeInputGame) => {
        if (!inputXPTrade) return null;

        try {
            const response = await axios.post(`${URL_API}/v2/favorites`, inputXPTrade, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + context.token
                },
            });


            if (response?.data) {
                return SUCCESS;
            } else {
                return null;
            }

        } catch (error: any) {
            if (error.response) {
                console.error('Server responded with error:', error.response.data);
            } else {
                console.error('Error while creating favorites:', error.message);
            }
        }
    };

    /**
     * Function to create a post a
     * @param inputPostXPTrade json to add
     * @returns 
     */
    const handleCreatePost = async (inputPostXPTrade) => {
        if (!inputPostXPTrade) return null;

        try {
            const response = await axios.post(`${URL_API}/v2/posts`, inputPostXPTrade, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + context.token
                },
            });

            if (response?.data) {
                return SUCCESS;
            } else {
                return null;
            }

        } catch (error: any) {
            if (error.response) {
                console.error('Server responded with error:', error.response.data);
            } else {
                console.error('Error while creating favorites:', error.message);
            }
        }
    };

    /**
    * Function to delete a post
    * @param id of the post
    * @returns success if exists, null if not
    */
    const handleDeletePost = async (id: number) => {
        if (!id) {
            return null;
        }


        try {
            const response = await axios.delete(`${URL_API}/v2/posts/${id}`,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + context.token
                    },
                });

            if (response?.status === 204) {
                return SUCCESS;
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while deleting item", error);
        }
    };

    /**
    * Function to delete a commment
    * @param id of the comment
    * @returns success if exists, null if not
    */
    const handleDeleteComment = async (id: number) => {
        if (!id) {
            return null;
        }


        try {
            const response = await axios.delete(`${URL_API}/v2/comments/${id}`,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + context.token
                    },
                });

            if (response?.status === 204) {
                return SUCCESS;
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while deleting item", error);
        }
    };

    /**
    * Function to update a commment
    * @param id of the comment
    * @param CommentButton of the comment
    * @returns success if exists, null if not
    */
    const handleUpdateComment = async (id: number, content: any) => {
        if (!id || !content.trim()) {
            return null;
        }


        try {
            const response = await axios.put(`${URL_API}/v2/comments/${id}`,
                {
                    content: content
                }, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + context.token
                },
            });

            if (response?.status === 200) {
                return response.data?.data;
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while updating item", error);
        }
    };


    /**
    * Function to update a post
    * @param id of the post
    * @param content of the post
    * @returns success if exists, null if not
    */
    const handleUpdatePost = async (id: number, content: string) => {
        if (!id || !content?.trim()) {
            return null;
        }


        try {
            const response = await axios.put(`${URL_API}/v2/posts/${id}`,
                {
                    content: content
                }, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + context.token
                },
            });

            if (response?.status === 200) {
                return response.data?.data;
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while updating item", error);
        }
    };

    /**
     * Function to create a comment in a post
     * @param inputPostXPTrade json to add
     * @returns 
     */
    const handleCreateComment = async (inputPostXPTrade) => {
        if (!inputPostXPTrade) return null;

        try {
            const response = await axios.post(`${URL_API}/v2/comments`, inputPostXPTrade, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + context.token
                },
            });

            if (response?.data) {
                return SUCCESS;
            } else {
                return null;
            }

        } catch (error: any) {
            if (error.response) {
                console.error('Server responded with error:', error.response.data);
            } else {
                console.error('Error while creating favorites:', error.message);
            }
        }
    };

    /**
     * Function to get an user favorite games
     * @param username of the user
     * @returns the favorites
     */
    const handleGetPosts = async () => {
        try {
            const response = await axios.get(`${URL_API}/v2/posts/latest`,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + context.token
                    },
                });

            if (response?.data) {
                return response?.data?.data;
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while fetching posts", error);
        }
    };

    /**
     * Function to get an user favorite games
     * @param username of the user
     * @returns the favorites
     */
    const handleGetUserPosts = async (username: string) => {
        if (!username?.trim()) return null;

        try {
            const response = await axios.get(`${URL_API}/v2/posts/users/${username}`,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + context.token
                    },
                });

            if (response?.data.data) {
                return response?.data.data;
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while fetching favorites", error);
        }
    };

    /**
     * Function to get an user favorite games
     * @param username of the user
     * @returns the favorites
     */
    const handleGetPostsComments = async (id: any) => {
        try {
            const response = await axios.get(`${URL_API}/v2/comments/posts/${id}`,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + context.token
                    },
                });

            if (response?.data) {
                return response?.data?.data;
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while fetching posts", error);
        }
    };


    /**
     * Function to get a post
     * @param id of the post
     * @returns the post
     */
    const handleGetPostById = async (id: any) => {
        try {
            const response = await axios.get(`${URL_API}/v2/posts/${id}`,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + context.token
                    },
                });

            if (response?.data) {
                return response?.data?.data;
            } else {
                return null;
            }

        } catch (error) {
            console.error("Error while fetching posts", error);
        }
    };


    return {
        handleLogin, handleRegister, handleAddToCollection, handleGetCollection, handleGetFavorites, handleAddToFavorite,
        handleCheckIfExistsFavorites, handleDeleteFromFavorites, handleCreatePost, handleGetPosts, handleGetUserPosts,
        handleCreateComment, handleGetPostsComments, handleDeletePost, handleDeleteComment, handleUpdateComment, 
        handleUpdatePost, handleGetPostById
    }

}

export default UseApi

