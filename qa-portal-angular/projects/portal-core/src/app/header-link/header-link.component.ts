import { Component, Input } from '@angular/core';
import { DepartmentApplications } from '../_common/models/department-applications';

@Component({
  selector: 'app-header-link',
  templateUrl: './header-link.component.html',
  styleUrls: ['./header-link.component.css']
})
export class HeaderLinkComponent {
  @Input() department: DepartmentApplications;
}
