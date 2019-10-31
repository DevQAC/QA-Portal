import { Component, OnInit } from '@angular/core';
import { TechnologyService } from '../_common/technology.service';
import { ActivatedRoute } from '@angular/router';
import { TechnologyCategoryModel } from 'projects/portal-core/src/app/_common/models/technology-category.model';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ENTER, COMMA } from '@angular/cdk/keycodes';
import { TechnologyModel } from 'projects/portal-core/src/app/_common/models/technology.model';
import { MatChipInputEvent } from '@angular/material';
import { finalize } from 'rxjs/operators';
import { QaToastrService } from 'projects/portal-core/src/app/_common/services/qa-toastr.service';

@Component({
  selector: 'app-technology-detail',
  templateUrl: './technology-detail.component.html',
  styleUrls: ['./technology-detail.component.css']
})
export class TechnologyDetailComponent implements OnInit {

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  technologies: TechnologyModel[] = [];

  public techCategory: TechnologyCategoryModel;
  public technologyForm: FormGroup;

  public isLoading = true;

  constructor(
    private techService: TechnologyService,
    private aR: ActivatedRoute,
    private errorService: QaErrorHandlerService,
    private toastr: QaToastrService) {
    this.technologyForm = new FormBuilder().group({
      categoryName: ['', Validators.required]
    });
    this.technologyForm.disable();
  }

  ngOnInit() {
    this.techService.getCategoryById(this.aR.snapshot.params.id).subscribe(category => {
      this.techCategory = category;
      this.isLoading = false;
      this.technologyForm.patchValue(this.techCategory);
      this.technologies = this.techCategory.technologies;
      this.technologyForm.enable();
      this.isLoading = false;
    }, err => this.errorService.handleError(err));
  }

  public onSaveCategoryClicked() {

    this.techCategory = {
      ...this.techCategory,
      ...this.technologyForm.value,
      technologies: this.technologies
    };
    this.technologyForm.disable();
    this.isLoading = true;
    this.techService.saveCategory(this.techCategory)
      .pipe(finalize(() => {
        this.technologyForm.enable();
        this.isLoading = false;
      })).subscribe(() => {
        this.toastr.showSuccess('Category updated');
      }, err => this.errorService.handleError(err));
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.technologies.push({ technologyName: value.trim() } as TechnologyModel);
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
    this.technologyForm.markAsDirty();
  }

  remove(tech: TechnologyModel): void {
    const index = this.technologies.indexOf(tech);

    if (index >= 0) {
      this.technologies.splice(index, 1);
    }
    this.technologyForm.markAsDirty();
  }
}
