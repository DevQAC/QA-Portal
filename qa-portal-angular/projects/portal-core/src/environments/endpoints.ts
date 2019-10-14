
export interface Endpoints {
  GET_COHORT_BY_ID: string;
  GET_ALL_COURSES: string;
  GET_TRAINERS_AVAILABLE_FOR_COHORT: string;
  SAVE_COHORT: string;
  GET_COURSE_BY_ID: string;
  SAVE_COURSE: string;
  GET_ALL_TECHNOLOGY_CATEGORIES: string;
  GET_ALL_TECHNOLOGY_CATEGORY_BY_ID: string;
  SAVE_TECHNOLOGY_CATEGORY: string;
  CREATE_TECHNOLOGY_CATEGORY: string;
  CREATE_COHORT: string;
  GET_ALL_FORMS: string;
  GET_FORM_BY_ID: string;
  CREATE_FORM: string;
  SAVE_FORM: string;
}

export const endpoints: Endpoints = {
  GET_COHORT_BY_ID: 'cohort-api/cohort/:id',
  GET_ALL_COURSES: 'cohort-api/courses',
  GET_TRAINERS_AVAILABLE_FOR_COHORT: 'cohort-api/manage/users/trainers',
  SAVE_COHORT: 'cohort-api/manage/cohort',
  GET_COURSE_BY_ID: '/cohort-api/course/:id',
  SAVE_COURSE: 'cohort-api/manage/course',
  GET_ALL_TECHNOLOGY_CATEGORIES: 'cohort-api/technology/categories',
  GET_ALL_TECHNOLOGY_CATEGORY_BY_ID: 'cohort-api/technology/category/:id',
  SAVE_TECHNOLOGY_CATEGORY: 'cohort-api/manage/technology/category',
  CREATE_TECHNOLOGY_CATEGORY: 'cohort-api/manage/technology/category',
  CREATE_COHORT: 'cohort-api/manage/cohort',
  GET_ALL_FORMS: '/form-api/forms',
  GET_FORM_BY_ID:  '/form-api/form/:id',
  CREATE_FORM: '/form-api/manage/form',
  SAVE_FORM: '/form-api/manage/form'
};

export type EndpointRef = keyof Endpoints;
