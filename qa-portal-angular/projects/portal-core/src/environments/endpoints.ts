
export interface Endpoints {
  GET_COHORT_BY_ID: string;
  GET_ALL_COURSES: string;
}

export const endpoints: Endpoints = {
  GET_COHORT_BY_ID: 'cohort-api/cohort/:id',
  GET_ALL_COURSES: 'cohort-api/courses'
};

export type EndpointRef = keyof Endpoints;
