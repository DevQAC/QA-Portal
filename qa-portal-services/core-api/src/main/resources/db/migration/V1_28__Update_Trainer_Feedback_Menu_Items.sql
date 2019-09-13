update training.application set url = '/qa/portal/training/feedback/trainer/history' where id = 10;

update training.app_menu_item set display_on_menu = false, url = '/qa/portal/training/feedback/trainer/course', name = 'Trainer Course Feedback' where id = 9;

INSERT INTO training.app_menu_item (id, name, url, app_id, tooltip) VALUES (12, 'Trainer Feedback History', '/qa/portal/training/feedback/trainer/history', 10, 'Trainer Courses and Feedback Status');

