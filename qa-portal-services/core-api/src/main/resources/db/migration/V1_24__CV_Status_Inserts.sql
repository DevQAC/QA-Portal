INSERT INTO training.cv_status(
	id, status_name, last_updated_timestamp, last_updated_by, version)
	VALUES (1, 'In Progress',
	       current_timestamp, 'admin', 1);

INSERT INTO training.cv_status(
	id, status_name, last_updated_timestamp, last_updated_by, version)
	VALUES (2, 'For Review',
	       current_timestamp, 'admin', 1);

INSERT INTO training.cv_status(
	id, status_name, last_updated_timestamp, last_updated_by, version)
	VALUES (3, 'Failed Review',
	       current_timestamp, 'admin', 1);

INSERT INTO training.cv_status(
	id, status_name, last_updated_timestamp, last_updated_by, version)
	VALUES (4, 'Approved',
	       current_timestamp, 'admin', 1);
