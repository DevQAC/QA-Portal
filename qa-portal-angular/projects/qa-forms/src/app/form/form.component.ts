import { Component, Input, Output, EventEmitter } from '@angular/core';
import { ICategory } from '../_common/models/form-category.model';

@Component({
  selector: 'app-qa-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent {
  @Input() model: ICategory[];
  @Output() modelChange = new EventEmitter<ICategory[]>();

  onCategoryChange(category: ICategory, index: number): void {
    this.model[index] = category;
    this.modelChange.emit(this.model);

    console.log(`FormComponent::onCategoryChange`,
      `\n\t model:`, this.model,
      `\n\t category changed:`, category
    );
  }
}
