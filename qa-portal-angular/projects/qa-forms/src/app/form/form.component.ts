import {Component, Input, Output, EventEmitter, OnInit} from '@angular/core';
import {IFormModel, ICategoryResponse} from '../_common/models';

@Component({
  selector: 'app-qa-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  @Input() model: IFormModel;
  @Output() modelChange = new EventEmitter<IFormModel>();

  ngOnInit() {
  }

  onCategoryChange(categoryResponse: ICategoryResponse, index: number): void {
    this.model.categoryResponses[index] = categoryResponse;
    // this.modelChange.emit(this.model);

    // console.log(`FormComponent::onCategoryChange`,
    //   `\n\t model:`, this.model,
    //   `\n\t category response changed:`, categoryResponse
    // );
  }
}
