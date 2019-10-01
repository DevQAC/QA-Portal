
export interface Endpoints {
  GET_COHORT_BY_ID: string;
  GET_ALL_COURSES: string;
  GET_TRAINERS_AVAILABLE_FOR_COHORT: string;
}

export const endpoints: Endpoints = {
  GET_COHORT_BY_ID: 'cohort-api/cohort/:id',
  GET_ALL_COURSES: 'cohort-api/courses',
  GET_TRAINERS_AVAILABLE_FOR_COHORT: 'cohort-api/manage/users/trainers'
};

export type EndpointRef = keyof Endpoints;
