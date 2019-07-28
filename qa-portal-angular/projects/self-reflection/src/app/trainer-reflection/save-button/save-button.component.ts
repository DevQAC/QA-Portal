import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-save-button',
  templateUrl: './save-button.component.html',
  styleUrls: ['./save-button.component.css']
})
export class SaveButtonComponent implements OnInit {

  @Output()
  public save = new EventEmitter();

  @Input()
  public disabled = false;

  constructor() { }

  public emitSave(): void {
    this.save.emit();
  }

  ngOnInit() {
  }

}
