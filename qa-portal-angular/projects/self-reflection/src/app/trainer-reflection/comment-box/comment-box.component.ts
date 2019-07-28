import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-comment-box',
  templateUrl: './comment-box.component.html',
  styleUrls: ['./comment-box.component.css']
})
export class CommentBoxComponent implements OnInit {

  @Input()
  public comments: string;
  @Input()
  public title: string;
  @Input()
  public placeHolder: string;
  @Output()
  public save = new EventEmitter();
  constructor() {
    this.comments = this.comments || '';
    this.title = this.title || '';
    this.placeHolder = this.placeHolder || '';
  }

  public emitSave(event: any): void {
    this.save.emit(event);
  }

  ngOnInit() {
  }

}
