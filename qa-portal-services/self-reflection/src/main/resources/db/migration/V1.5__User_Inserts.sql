INSERT INTO training.qa_user(
	id, user_name, first_name, last_name, reviewer_id, last_updated_timestamp, last_updated_by, version, role, cohort_id)
	VALUES (1, 'Trainer 1', 'Trainer_First', 'Trainer_Last', null, current_timestamp , 'admin', 1, 'TRAINER', 1);

INSERT INTO training.qa_user(
	id, user_name, first_name, last_name, reviewer_id, last_updated_timestamp, last_updated_by, version, role, cohort_id)
	VALUES (2, 'Trainere 1', 'Trainee_First', 'Trainee_Last', 1, current_timestamp , 'admin', 1, 'TRAINEE', 1);
