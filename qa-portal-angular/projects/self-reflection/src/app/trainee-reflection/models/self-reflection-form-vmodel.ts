import { SelfRatingModel } from './self-rating.model';

export class SelfReflectionFormViewModel {
    id: number;

    qaUser: any;

    selfReflectionReviews: any;

    selfRatings: SelfRatingModel[] = [];

    strengthsText: string;

    weaknessesText: string;

    opportunitiesText: string;

    threatsText: string;

    weekCommencing: Date;

    currentStatus: string;
}