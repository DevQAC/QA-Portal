insert into training.project_page(id, name, url, portal_project_id, tooltip, display_on_menu, icon, last_updated_timestamp, last_updated_by, version)
      values (20, 'Manage Technologies', '/qa/portal/admin/manage/technologies', 8, 'Create and update technologies', true, 'qa-manage-technology',
      current_timestamp, 'admin', 1);

insert into training.role_project_page(id, role_id, project_page_id, last_updated_timestamp, last_updated_by, version)
      values (19, 10, 20, current_timestamp, 'admin', 1);