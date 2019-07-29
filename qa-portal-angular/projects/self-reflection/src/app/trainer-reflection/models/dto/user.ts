import { Base } from './base';

export class User extends Base {
  id?: number;
  firstName = '';
  lastName = '';
  userName = '';
  role: string;
  reviewerId: number;
  cohortId: number;
}
