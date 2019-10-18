
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
  GET_ALL_APPLICATIONS: string;
  CREATE_APPLICATION: string;
  GET_APPLICATION_BY_ID: string;
  SAVE_APPLICATION: string;
  GET_ALL_PORTAL_PROJECTS: string;
  CREATE_PORTAL_PROJECT: string;
  GET_PORTAL_PROJECT_BY_ID: string;
  SAVE_PORTAL_PROJECT: string;
  GET_ALL_LOCATIONS: string;
  GET_LOCATION_BY_ID: string;
  SAVE_LOCATION: string;
  CREATE_LOCATION: string;
  GET_ALL_ROLES: string;
  GET_ALL_ROLE_NAMES: string;
  GET_ROLE_BY_ID: string;
  SAVE_ROLE: string;
  CREATE_ROLE: string;
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
  SAVE_FORM: '/form-api/manage/form',
  GET_ALL_APPLICATIONS: '/portal-application-api/manage/portal/applications',
  CREATE_APPLICATION: '/portal-application-api/manage/portal/application',
  GET_APPLICATION_BY_ID: '/portal-application-api/manage/portal/application/:id',
  SAVE_APPLICATION: '/portal-application-api/manage/portal/application',
  GET_ALL_PORTAL_PROJECTS: '/portal-application-api/portal/projects',
  CREATE_PORTAL_PROJECT: '/portal-application-api/manage/portal/project',
  GET_PORTAL_PROJECT_BY_ID: '/portal-application-api/portal/project/:id',
  SAVE_PORTAL_PROJECT: '/portal-application-api/manage/portal/project',
  GET_ALL_LOCATIONS: '/cohort-api/locations',
  GET_LOCATION_BY_ID: '/cohort-api/location/:id',
  SAVE_LOCATION: '/cohort-api/manage/location',
  CREATE_LOCATION: '/cohort-api/manage/location',
  GET_ALL_ROLES: '/portal-application-api/manage/roles',
  GET_ALL_ROLE_NAMES: '/portal-application-api/manage/roles/names',
  GET_ROLE_BY_ID: '/portal-application-api/role/:id',
  SAVE_ROLE: '/portal-application-api/manage/role',
  CREATE_ROLE: '/portal-application-api/manage/role'
};

export type EndpointRef = keyof Endpoints;
