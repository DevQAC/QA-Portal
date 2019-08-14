import { Component, OnInit, Output } from '@angular/core';
import { EventEmitter } from 'events';

@Component({
  selector: 'app-save-button',
  templateUrl: './save-button.component.html',
  styleUrls: ['./save-button.component.css']
})
export class SaveButtonComponent implements OnInit {

  @Output()
  public save = new EventEmitter();

  constructor() { }

  public emitSave():void{
    //this.save.emit();
  }
  ngOnInit() {
  }

}
