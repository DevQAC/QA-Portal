
export interface IGenericQuestion<ResponseType> {
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
     * User input value for the question
     * @type {ResponseType}
     * @memberof IGenericQuestion
     */
    response: ResponseType;

    /**
     * Additional user comment
     * @type {string}
     * @memberof IGenericQuestion
     */
    comment: string;
}
