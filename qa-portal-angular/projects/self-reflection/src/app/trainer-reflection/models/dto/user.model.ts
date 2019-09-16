import { BaseModel } from './base.model';

export class UserModel extends BaseModel {
  id?: number;
  firstName = '';
  lastName = '';
  userName = '';
  role: string;
  reviewerId: number;
  cohortId: number;
}
