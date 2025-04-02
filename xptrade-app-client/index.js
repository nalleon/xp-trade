/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';

console.log('***********************************************************************');
console.log('App name:', appName);
console.log('***********************************************************************');
AppRegistry.registerComponent(appName, () => App);
