import { Component, OnInit, Input } from '@angular/core';

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
  constructor() {
    this.comments = this.comments || '';
    this.title = this.title || '';
    this.placeHolder = this.placeHolder || '';
  }

  ngOnInit() {
  }

}
