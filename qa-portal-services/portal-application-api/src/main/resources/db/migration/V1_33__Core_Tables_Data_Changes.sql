update training.portal_application set description = 'QA HR Application' where id = 2;
update training.portal_application set description = 'QA Training Application' where id = 1;
update training.portal_application set description = 'QA Finance Application' where id = 3;
update training.portal_application set description = 'QA Portal Home' where id = 4;

update training.portal_application set base_url = '/qa/portal/training' where id = 1;
update training.portal_application set base_url = '/qa/portal/hr' where id = 2;
update training.portal_application set base_url = '/qa/portal/finance' where id = 3;
update training.portal_application set base_url = '/qa/portal/home' where id = 4;

update training.portal_project set name = 'Reflection' where id = 1;
update training.portal_project set name = 'Feedback' where id = 2;
update training.portal_project set name = 'CV Management' where id = 3;
update training.portal_project set name = 'Home' where id = 4;

update training.project_page set portal_project_id = 1 where id in (1, 2, 4, 5);
update training.project_page set portal_project_id = 2 where id in (6, 7, 8, 9, 12);
update training.project_page set portal_project_id = 3 where id in (10, 11);
update training.project_page set portal_project_id = 4 where id = 3;

delete from training.portal_project where id > 4;

update training.role set portal_application_id = 1 where id in (1, 2, 3);
update training.role set portal_application_id = 2 where id in (4, 5, 6);
update training.role set portal_application_id = 3 where id in (7, 8, 9);

insert into training.role (id, name, portal_application_id) values (11, 'any', 4);

insert into training.role_project_page (id, role_id, project_page_id) VALUES (12, 2, 12);
insert into training.role_project_page (id, role_id, project_page_id) VALUES (13, 11, 3);

update training.project_page set url = '/qa/portal/training/cv/trainee/current' where id = 10;
update training.project_page set url = '/qa/portal/training/cv/admin/search' where id = 11;
insert into training.project_page (id, name, url, portal_project_id, tooltip, display_on_menu, icon)
            values (13, 'Trainee View CV', '/qa/portal/training/cv/trainee/view', 3, 'View Trainee Selected Cv Version', false, 'face');
insert into training.project_page (id, name, url, portal_project_id, tooltip, display_on_menu, icon)
            values (14, 'Admin View CV', '/qa/portal/training/cv/admin/view', 3, 'View Selected Cv Version', false, 'face');
