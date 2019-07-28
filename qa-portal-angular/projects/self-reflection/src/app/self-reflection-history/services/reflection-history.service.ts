import { Injectable } from '@angular/core';
import {reflection} from '../models/reflections';
import { HttpClient } from '@angular/common/http';

const reflections: reflection[] = [
    {id:1, content:'Self Reflection Form', date: '19/07/2019'},
    {id:2, content:'Self Reflection Form', date: '12/07/2019'},
    {id:2, content:'Self Reflection Form', date: '12/07/2019'},
    {id:1, content:'Self Reflection Form', date: '19/07/2019'},
    {id:2, content:'Self Reflection Form', date: '12/07/2019'},
    {id:2, content:'Self Reflection Form', date: '12/07/2019'},
    {id:1, content:'Self Reflection Form', date: '19/07/2019'},
    {id:2, content:'Self Reflection Form', date: '12/07/2019'},
    {id:2, content:'Self Reflection Form', date: '12/07/2019'}
]

@Injectable({
    providedIn: 'root'
})
export class ReflectionHistoryService {

    constructor(private http: HttpClient){}

    public getTraineeReflections(): reflection[] {
        return reflections;
    }

}