insert into training.portal_project(id, name, last_updated_timestamp, last_updated_by, version)
       values (9, 'Form Management',
       current_timestamp, 'admin', 1);

insert into training.project_page(id, name, url, portal_project_id, tooltip, display_on_menu, icon, last_updated_timestamp, last_updated_by, version)
      values (21, 'Manage Forms', '/qa/portal/admin/manage/forms', 9, 'Manage Forms and Questions', true, '',
      current_timestamp, 'admin', 1);

insert into training.role_project_page(id, role_id, project_page_id, last_updated_timestamp, last_updated_by, version)
      values (20, 10, 21, current_timestamp, 'admin', 1);
