import { Component, OnInit } from '@angular/core';
import { TechnologyService } from '../_common/technology.service';
import { ActivatedRoute } from '@angular/router';
import { TechnologyCategoryModel } from 'projects/portal-core/src/app/_common/models/technology-category.model';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-technology-detail',
  templateUrl: './technology-detail.component.html',
  styleUrls: ['./technology-detail.component.css']
})
export class TechnologyDetailComponent implements OnInit {

  public techCategory: TechnologyCategoryModel;
  public technologyForm: FormGroup;

  public isLoading = true;

  constructor(
    private techService: TechnologyService,
    private aR: ActivatedRoute,
    private errorService: QaErrorHandlerService) {
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
      this.technologyForm.enable();
    }, err => this.errorService.handleError(err));
  }

}
