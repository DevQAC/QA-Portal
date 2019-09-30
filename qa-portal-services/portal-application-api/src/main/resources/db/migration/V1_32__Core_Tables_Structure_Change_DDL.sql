
alter table training.department rename to portal_application;

alter table training.portal_application
	add base_url varchar(255);

alter table training.application rename to portal_project;

alter table training.portal_project drop column url;

alter table training.role
	add portal_application_id int not null default 1;

alter table training.dept_role_menu_item rename to role_project_page;

alter table training.role_project_page rename column dept_role_id to role_id;

alter table training.role_project_page rename column menu_item_id to project_page_id;

drop table training.dept_role_application;

drop table training.dept_role;

alter table training.app_menu_item rename to project_page;

alter table training.project_page
	add icon varchar(255);

alter table training.project_page rename column app_id to portal_project_id;






