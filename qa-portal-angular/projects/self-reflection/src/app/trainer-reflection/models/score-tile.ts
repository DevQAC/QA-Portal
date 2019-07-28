import { Tile } from './tile';
import { UserType } from './user-type.enum';
import { ReflectionQuestion } from './dto/reflection-question';

export class ScoreTile extends Tile<ReflectionQuestion> {
  userType: UserType;
}
