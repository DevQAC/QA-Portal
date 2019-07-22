INSERT INTO training.self_reflection_status(
	id, status_text, last_updated_timestamp, last_updated_by, version)
	VALUES (1, 'Saved', current_timestamp , 'admin', 1);

INSERT INTO training.self_reflection_status(
	id, status_text, last_updated_timestamp, last_updated_by)
	VALUES (2, 'Submitted', current_timestamp , 'admin', 1);

INSERT INTO training.self_reflection_status(
	id, status_text, last_updated_timestamp, last_updated_by)
	VALUES (3, 'Reviewed', current_timestamp , 'admin', 1);
