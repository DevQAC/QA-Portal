export class QuestionModel {
    id: number;
    body: string;
    hasComment: boolean;
    commentLabel: string;
    selectionOptionsJson: string;
    selectionOptionsList: string[];
    selectionOptions_PLACEHOLDER: any;
}