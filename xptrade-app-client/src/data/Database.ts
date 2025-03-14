
import {DataSource} from 'typeorm';

import { GameLocalEntity } from './entity/GameLocalEntity';



export const dataSource = new DataSource({
  database: 'tictactoe5.db',
  entities: [
    GameLocalEntity
  ],
  location: 'default',
  //logging: [],
  logging: ['error', 'query', 'schema'],
  synchronize: true,
  type: 'react-native',
});


export const GameRepository = dataSource.getRepository(GameLocalEntity);









