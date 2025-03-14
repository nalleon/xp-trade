import {
    Entity,
    PrimaryGeneratedColumn,
    Column,
    ManyToMany,
    JoinTable,
    BaseEntity,
    OneToMany,
} from "typeorm"



@Entity()
export class GameLocalEntity extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({type:'text'}) board: string;

  @Column('date') dateCreation: Date;

}