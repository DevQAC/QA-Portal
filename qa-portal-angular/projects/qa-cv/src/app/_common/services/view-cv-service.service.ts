import { Injectable } from '@angular/core';
import { TestBed } from '@angular/core/testing';

@Injectable({
  providedIn: 'root'
})

export class ViewCvServiceService {

  constructor() { }


  getCvById(id) {
    if (id === "test") {
      return true;
    } else {
      return false;
    }
  }


  
}