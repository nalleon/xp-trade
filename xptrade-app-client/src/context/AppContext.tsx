import { StyleSheet, Text, View } from 'react-native'
import React, { createContext, useEffect, useState } from 'react'
import AsyncStorage from '@react-native-async-storage/async-storage';
import { Comment, PostXPTrade, Result } from '../utils/TypeUtils';
import { GameDetails } from '../utils/GameDetailsType';


type Props = {
    children: React.ReactNode;
}
type AppContextType ={
    username: string,
    setUsername: (nombreUsuario: string) => void,
    token: string,
    setToken: (token: string) => void,
    currentGame: Result,
    setCurrentGame: (game: Result) => void,
    currentGameDetailed: GameDetails | null,
    setCurrentGameDetailed: (game: GameDetails | null) => void,
    currentComment: any,
    setCurrentComment: (comment: any) => void,
    currentPost: any,
    setCurrentPost: (post: any) => void,
}

export const AppContext = createContext<AppContextType>({} as AppContextType );
const AppContextProvider = (props: Props) => {
    const [username, setUsername] = useState<string>("");
    const [token, setToken] = useState<string>("");
    const [currentGame, setCurrentGame] = useState<Result>({} as Result);
    const [currentGameDetailed, setCurrentGameDetailed] = useState<GameDetails | null>(null);

    const [currentComment, setCurrentComment] = useState<any>({} as any);
    const [currentPost, setCurrentPost] = useState<any>({} as any);

    const contextValues: AppContextType  = {
        username,
        setUsername,
        token,
        setToken,
        currentGame,
        setCurrentGame,
        currentGameDetailed,
        setCurrentGameDetailed,
        currentComment,
        setCurrentComment,
        currentPost,
        setCurrentPost
    }
    
        return (
            <AppContext.Provider value={contextValues}>
                {props.children}
            </AppContext.Provider>
        )
    }
export default AppContextProvider

const styles = StyleSheet.create({})