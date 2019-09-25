// TODO  - TO BE REMOVED
export const GET_FORM_TYPE = 'form-api/question/category/formtype/';
export const SEND_EVAL_RESPONSE = 'feedback-api/feedback';
// TODO - END OF TO BE REMOVED

// EVALUATION FORM URLS

export const GET_TRAINER_EVALUATION_SUMMARY = 'feedback-api/evaluation/course/';

export const GET_COHORT_COURSES_FOR_TRAINER = '/feedback-api/evaluation/trainer';

export const GET_TRAINEE_EVALUATION_SUMMARY_URL = 'feedback-api/evaluation/trainee/summary';

export const GET_EVALUATION_FORMS_FOR_TRAINEE_URL = 'feedback-api/evaluation/trainee';

// Add cohort course id to the end of the url
export const GET_EVALUATION_FOR_TRAINEE_AND_COURSE_URL = 'feedback-api/evaluation/trainee/course/';

// Add the Cohort Course Evaluation id to the end of the url
export const GET_EVALUATION_FORM_FOR_ID_URL = 'feedback-api/evaluation/';

// Add the Cohort Course Id to the end of the url
export const GET_EVALUATION_FORMS_FOR_COHORT_COURSE_URL = 'feedback-api/evaluation/course/';

// Invoked with an Http POST request
export const CREATE_EVALUATION_FORM_URL = 'feedback-api/evaluation';

// Invoked with an Http PUT request
export const UPDATE_EVALUATION_FORM_URL = 'feedback-api/evaluation'


// FEEDBACK FORM URLS

// Invoked with an Http POST request
export const CREATE_FEEDBACK_FORM_URL = 'feedback-api/feedback';

// Invoked with a and Http PUT request
export const UPDATE_FEEDBACK_FORM_URL = 'feedback-api/feedback';

export const GET_FEEDBACK_HISTORY_FOR_TRAINER_URL = 'feedback-api/feedback/trainer';

// Add the Cohort course Id to the end of the url
export const GET_FEEDBACK_FOR_COURSE_URL = 'feedback-api/feedback/course/';

// Add the Cohort Course Feedback Id to the end of the url
export const GET_FEEDBACK_FOR_ID_URL = 'feedback-api/feedback/';
