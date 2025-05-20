import { StyleSheet, Text, View } from 'react-native'
import React, { useContext } from 'react'
import { AppContext } from '../context/AppContext'
import AuthStackNav from './stack/AuthStackNav'
import DrawerNav from './drawer/DrawerNav'

type Props = {}

const RootNavigation = (props: Props) => {
  const context = useContext(AppContext);
  return (
    <>
      {context.token ? <DrawerNav key="drawer" /> : <AuthStackNav key="auth" />}
    </>
  )
}

export default RootNavigation

const styles = StyleSheet.create({})