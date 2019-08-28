import { ControlTypes } from '../types/control.types';

export interface IQuestion {
    /**
     * Unique ID for this control
     * @type {number}
     * @memberof IGenericQuestion
     */
    id: number;

    /**
     * The question being asked. This is typically displayed along with an appropriate control
     * @type {string}
     * @memberof IGenericQuestion
     */
    body: string;

    /**
     * Determines the visibility of an additional comment field
     * @type {boolean}
     * @memberof IGenericQuestion
     */
    hasComment: boolean;

    /**
     * Additional user comment
     * @type {string}
     * @memberof IGenericQuestion
     */
    comment: string;

    /**
     * The type of control to use for this question.
     *
     * @type {ControlTypes}
     * @memberof IGenericQuestion
     */
    selectionType: ControlTypes;
}
