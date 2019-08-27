import { Component, OnInit, Input } from '@angular/core';
import { DepartmentApplications } from '../_common/models/department-applications';

@Component({
  selector: 'app-header-link',
  templateUrl: './header-link.component.html',
  styleUrls: ['./header-link.component.css']
})
export class HeaderLinkComponent implements OnInit {
  @Input() department: DepartmentApplications;

  constructor() { }

  ngOnInit() {
    console.warn(this.department);
  }

}
