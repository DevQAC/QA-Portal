alter table training.portal_application
	add last_updated_timestamp timestamp default current_timestamp;

alter table training.portal_application
	add last_updated_by varchar(255) default 'admin';

alter table training.portal_application
	add version integer default 1;


alter table training.portal_project
    add last_updated_timestamp timestamp default current_timestamp;

alter table training.portal_project
    add last_updated_by varchar(255) default 'admin';

alter table training.portal_project
    add version integer default 1;


alter table training.project_page
    add last_updated_timestamp timestamp default current_timestamp;

alter table training.project_page
    add last_updated_by varchar(255) default 'admin';

alter table training.project_page
    add version integer default 1;


alter table training.role
    add last_updated_timestamp timestamp default current_timestamp;

alter table training.role
    add last_updated_by varchar(255) default 'admin';

alter table training.role
    add version integer default 1;



alter table training.role_project_page
    add last_updated_timestamp timestamp default current_timestamp;

alter table training.role_project_page
    add last_updated_by varchar(255) default 'admin';

alter table training.role_project_page
    add version integer default 1;