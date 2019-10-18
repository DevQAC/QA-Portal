insert into training.project_page(id, name, url, portal_project_id, tooltip, display_on_menu, icon, last_updated_timestamp, last_updated_by, version)
      values (23, 'Manage Locations', '/qa/portal/admin/manage/locations', 7, 'Create and update locations for cohorts courses', true, '',
      current_timestamp, 'admin', 1);

insert into training.role_project_page(id, role_id, project_page_id, last_updated_timestamp, last_updated_by, version)
      values (22, 10, 23, current_timestamp, 'admin', 1);

insert into training.project_page(id, name, url, portal_project_id, tooltip, display_on_menu, icon, last_updated_timestamp, last_updated_by, version)
      values (24, 'Manage Roles', '/qa/portal/admin/manage/roles', 7, 'Create and update roles for accessing portal pages', true, '',
      current_timestamp, 'admin', 1);

insert into training.role_project_page(id, role_id, project_page_id, last_updated_timestamp, last_updated_by, version)
      values (23, 10, 24, current_timestamp, 'admin', 1);