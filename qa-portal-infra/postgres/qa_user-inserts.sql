
INSERT INTO training.qa_user(
	id, user_name, reviewer_id, last_updated_timestamp, last_updated_by)
	VALUES (1, 'admin', null, current_timestamp , 'admin');

INSERT INTO training.qa_user(
	id, user_name, reviewer_id, last_updated_timestamp, last_updated_by)
	VALUES (2, 'scott-super', 1, current_timestamp, 'admin');

