
insert into training.portal_application(id, name, description, display_order, base_url, last_updated_timestamp, last_updated_by, version)
       values (5, 'Administration', 'Portal Administration Application', 5, '/qa/portal/admin',
          current_timestamp, 'admin', 1);

insert into training.portal_project(id, name, last_updated_timestamp, last_updated_by, version)
       values (5, 'User Management',
       current_timestamp, 'admin', 1);

insert into training.portal_project(id, name, last_updated_timestamp, last_updated_by, version)
       values (6, 'Cohort Management',
       current_timestamp, 'admin', 1);

insert into training.portal_project(id, name, last_updated_timestamp, last_updated_by, version)
       values (7, 'Application Management',
       current_timestamp, 'admin', 1);

insert into training.portal_project(id, name, last_updated_timestamp, last_updated_by, version)
       values (8, 'Course Management',
       current_timestamp, 'admin', 1);

insert into training.project_page(id, name, url, portal_project_id, tooltip, display_on_menu, icon, last_updated_timestamp, last_updated_by, version)
      values (15, 'Manage Users', '/qa/portal/admin/manage/users', 5, 'Create and update users and their roles', true, '',
      current_timestamp, 'admin', 1);

insert into training.project_page(id, name, url, portal_project_id, tooltip, display_on_menu, icon, last_updated_timestamp, last_updated_by, version)
      values (16, 'Manage Cohorts', '/qa/portal/admin/manage/cohorts', 6, 'Create and update cohorts and their course schedules', true, '',
      current_timestamp, 'admin', 1);

insert into training.project_page(id, name, url, portal_project_id, tooltip, display_on_menu, icon, last_updated_timestamp, last_updated_by, version)
      values (17, 'Manage Applications', '/qa/portal/admin/manage/applications', 7, 'Create and update applications, their projects and pages', true, '',
      current_timestamp, 'admin', 1);

insert into training.project_page(id, name, url, portal_project_id, tooltip, display_on_menu, icon, last_updated_timestamp, last_updated_by, version)
      values (18, 'Manage Courses', '/qa/portal/admin/manage/courses', 8, 'Create and update courses and their technologies', true, '',
      current_timestamp, 'admin', 1);

insert into training.project_page(id, name, url, portal_project_id, tooltip, display_on_menu, icon, last_updated_timestamp, last_updated_by, version)
      values (19, 'Course Evaluation Summary', '/qa/portal/training/feedback/trainer/evaluation/course/summary', 2, 'Trainer Course Evaluation Summary', false, '',
      current_timestamp, 'admin', 1);

update training.role set portal_application_id = 5 where id = 10;

insert into training.role_project_page(id, role_id, project_page_id, last_updated_timestamp, last_updated_by, version)
      values (14, 10, 15, current_timestamp, 'admin', 1);

insert into training.role_project_page(id, role_id, project_page_id, last_updated_timestamp, last_updated_by, version)
      values (15, 10, 16, current_timestamp, 'admin', 1);

insert into training.role_project_page(id, role_id, project_page_id, last_updated_timestamp, last_updated_by, version)
      values (16, 10, 17, current_timestamp, 'admin', 1);

insert into training.role_project_page(id, role_id, project_page_id, last_updated_timestamp, last_updated_by, version)
      values (17, 10, 18, current_timestamp, 'admin', 1);

insert into training.role_project_page(id, role_id, project_page_id, last_updated_timestamp, last_updated_by, version)
      values (18, 2, 19, current_timestamp, 'admin', 1);

update training.project_page set url ='/qa/portal/training/self-reflection/trainee/new' where id = 2;

update training.project_page set url ='/qa/portal/training/self-reflection/trainee/history' where id = 1;

update training.project_page set url ='/qa/portal/training/self-reflection/trainer/cohorts/trainees' where id = 4;

update training.project_page set url ='/qa/portal/training/self-reflection/admin/cohorts/summary' where id = 5;