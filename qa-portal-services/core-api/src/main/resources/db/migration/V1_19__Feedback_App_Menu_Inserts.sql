INSERT INTO training.application (id, name, url) VALUES (8, 'Trainee Course Evaluation', '/qa/portal/training/trainee/feedback/evaluation');
INSERT INTO training.application (id, name, url) VALUES (9, 'Trainer Evaluation', '/qa/portal/training/trainer/feedback/evaluation');
INSERT INTO training.application (id, name, url) VALUES (10, 'Trainer Feedback', '/qa/portal/training/trainer/feedback');

INSERT INTO training.app_menu_item (id, name, url, app_id, tooltip) VALUES (6, 'Current Trainee Evaluation', '/qa/portal/training/trainee/feedback/evaluation', 8, 'Create or update current trainee course evaluation');
INSERT INTO training.app_menu_item (id, name, url, app_id, tooltip) VALUES (7, 'Trainee Evaluation History', '/qa/portal/training/trainee/feedback/evaluation', 8, 'View trainee course evaluation history');
INSERT INTO training.app_menu_item (id, name, url, app_id, tooltip) VALUES (8, 'Trainer Evaluation History', '/qa/portal/training/trainer/feedback/evaluation/history', 9, 'View trainer course evaluation history');
INSERT INTO training.app_menu_item (id, name, url, app_id, tooltip) VALUES (9, 'Current Trainer Feedback', '/qa/portal/training/trainer/feedback', 10, 'Create or update current trainer course feedback');
INSERT INTO training.app_menu_item (id, name, url, app_id, tooltip) VALUES (10, 'Trainer Feedback History', '/qa/portal/training/trainee/feedback/history', 10, 'View trainer course feedback history');

INSERT INTO training.dept_role_application (id, dept_role_id, app_id) VALUES (19, 1, 8);
INSERT INTO training.dept_role_application (id, dept_role_id, app_id) VALUES (20, 2, 9);
INSERT INTO training.dept_role_application (id, dept_role_id, app_id) VALUES (21, 2, 10);

INSERT INTO training.dept_role_menu_item (id, dept_role_id, menu_item_id) VALUES (5, 1, 6);
INSERT INTO training.dept_role_menu_item (id, dept_role_id, menu_item_id) VALUES (6, 1, 7);
INSERT INTO training.dept_role_menu_item (id, dept_role_id, menu_item_id) VALUES (7, 2, 8);
INSERT INTO training.dept_role_menu_item (id, dept_role_id, menu_item_id) VALUES (8, 2, 9);
INSERT INTO training.dept_role_menu_item (id, dept_role_id, menu_item_id) VALUES (9, 2, 10);