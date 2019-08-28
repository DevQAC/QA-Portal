INSERT INTO training.application (id, name, url) VALUES (11, 'Trainee Current CV', '/qa/portal/cv/trainee/current');
INSERT INTO training.application (id, name, url) VALUES (12, 'CV Search', '/qa/portal/cv/admin/search');

INSERT INTO training.app_menu_item (id, name, url, app_id, tooltip) VALUES (10, 'Trainee Current CV', '/qa/portal/cv/trainee/current', 11, 'Maintain and View current CV version');
INSERT INTO training.app_menu_item (id, name, url, app_id, tooltip) VALUES (11, 'CV Search', '/qa/portal/cv/admin/search', 12, 'Search trainee CVs');

INSERT INTO training.dept_role_application (id, dept_role_id, app_id) VALUES (22, 1, 11);
INSERT INTO training.dept_role_application (id, dept_role_id, app_id) VALUES (23, 3, 12);

INSERT INTO training.dept_role_menu_item (id, dept_role_id, menu_item_id) VALUES (9, 1, 10);
INSERT INTO training.dept_role_menu_item (id, dept_role_id, menu_item_id) VALUES (10, 3, 11);
