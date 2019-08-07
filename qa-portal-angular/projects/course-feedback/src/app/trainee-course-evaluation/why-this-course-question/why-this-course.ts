import {Component} from '@angular/core';

@Component({
    selector: 'radio-why-this-course',
    templateUrl: 'why-this-course.html',
})


export class WhyThisCourseFeedBack {
whyResponse: string;
question;
options; 
}

export function optionsInput(WhyThisCourseFeedBack) {
    if (WhyThisCourseFeedBack.whyResponse == 'other') {
        return true;
    }
}