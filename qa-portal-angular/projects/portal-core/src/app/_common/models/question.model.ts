import { ControlTypes } from '../types/control.types';

export class QuestionModel {
    /**
     * Unique ID for this control
     */
    id: number;

    /**
     * The question being asked. This is typically displayed along with an appropriate control
     */
    body: string;

    /**
     * Determines the visibility of an additional comment field
     */
    hasComment = false;

    /**
     * Additional user comment
     */
    commentLabel: string;

    /**
     * The type of control to use for this question.
     */
    selectionType: ControlTypes;


    selectionOptionsList = [];
}
