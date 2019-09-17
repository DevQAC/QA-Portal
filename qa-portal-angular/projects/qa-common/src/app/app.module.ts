import { MatButtonModule, MatCheckboxModule } from '@angular/material';
import { MatInputModule } from '@angular/material/input';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTabsModule } from '@angular/material/tabs';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule, MatIconRegistry } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RatedQuestionComponent } from './rated-question/rated-question.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { DomSanitizer } from '@angular/platform-browser';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { SearchBarBackdropComponent } from './search-bar-backdrop/search-bar-backdrop.component';
import { CommonModule } from '@angular/common';

const materialModules = [
  MatCheckboxModule,
  MatCheckboxModule,
  MatButtonModule,
  MatInputModule,
  MatAutocompleteModule,
  MatDatepickerModule,
  MatFormFieldModule,
  MatRadioModule,
  MatSelectModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatMenuModule,
  MatSidenavModule,
  MatToolbarModule,
  MatListModule,
  MatGridListModule,
  MatCardModule,
  MatStepperModule,
  MatTabsModule,
  MatExpansionModule,
  MatButtonToggleModule,
  MatChipsModule,
  MatIconModule,
  MatProgressSpinnerModule,
  MatProgressBarModule,
  MatDialogModule,
  MatTooltipModule,
  MatSnackBarModule,
  MatTableModule,
  MatSortModule,
  MatPaginatorModule
];

@NgModule({
  declarations: [
    AppComponent,
    RatedQuestionComponent,
    SearchBarComponent,
    SearchBarBackdropComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ...materialModules,
    ReactiveFormsModule,
    ToastrModule.forRoot(
      {
        positionClass: 'toast-bottom-center',
        closeButton: true,
        timeOut: 7000
      }
    )
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports: [
    CommonModule,
    FormsModule,
    ...materialModules,
    RatedQuestionComponent,
    ReactiveFormsModule,
    SearchBarComponent,
    SearchBarBackdropComponent
  ]
})
export class QaCommonModule {
  constructor(
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer
  ) {
    this.matIconRegistry.addSvgIcon('qa-logo', this.domSanitizer.bypassSecurityTrustResourceUrl('/assets/qa.svg'));
  }
}
