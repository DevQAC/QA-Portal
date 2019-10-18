insert into training.project_page(id, name, url, portal_project_id, tooltip, display_on_menu, icon, last_updated_timestamp, last_updated_by, version)
      values (22, 'Manage Projects', '/qa/portal/admin/manage/app-projects', 7, 'Create and update portal projects, their pages and roles', true, '',
      current_timestamp, 'admin', 1);

insert into training.role_project_page(id, role_id, project_page_id, last_updated_timestamp, last_updated_by, version)
      values (21, 10, 22, current_timestamp, 'admin', 1);