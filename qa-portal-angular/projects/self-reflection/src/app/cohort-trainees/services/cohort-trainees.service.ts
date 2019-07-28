import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

export class CohortTraineesService{
    constructor(private http: HttpClient) { }
}