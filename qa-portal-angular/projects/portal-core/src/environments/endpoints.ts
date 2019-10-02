
export interface Endpoints {
  GET_COHORT_BY_ID: string;
  GET_ALL_COURSES: string;
  GET_TRAINERS_AVAILABLE_FOR_COHORT: string;
  SAVE_COHORT: string;
  GET_COURSE_BY_ID: string;
  SAVE_COURSE: string;
  GET_ALL_TECHNOLOGY_CATEGORIES: string;
}

export const endpoints: Endpoints = {
  GET_COHORT_BY_ID: 'cohort-api/cohort/:id',
  GET_ALL_COURSES: 'cohort-api/courses',
  GET_TRAINERS_AVAILABLE_FOR_COHORT: 'cohort-api/manage/users/trainers',
  SAVE_COHORT: 'cohort-api/manage/cohort',
  GET_COURSE_BY_ID: '/cohort-api/course/:id',
  SAVE_COURSE: 'cohort-api/manage/course',
  GET_ALL_TECHNOLOGY_CATEGORIES: 'cohort-api/technology/categories'
};

export type EndpointRef = keyof Endpoints;
