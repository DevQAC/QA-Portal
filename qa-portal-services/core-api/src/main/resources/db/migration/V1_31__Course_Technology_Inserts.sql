
delete from training.course_technology where id = 1;

delete from training.course_technology where id = 2;

delete from training.course_technology where id = 3;

delete from training.course_technology where id = 4;

delete from training.technology where id = 27;

INSERT INTO training.technology(
	id, technology_name, technology_category_id, search_string, last_updated_timestamp, last_updated_by, version)
	VALUES (30, 'Azure', 4, 'azure',
	        current_timestamp, 'admin', 1);

INSERT INTO training.technology(
	id, technology_name, technology_category_id, search_string, last_updated_timestamp, last_updated_by, version)
	VALUES (31, 'OOD', 10, 'object-oriented-design',
	        current_timestamp, 'admin', 1);

INSERT INTO training.technology(
	id, technology_name, technology_category_id, search_string, last_updated_timestamp, last_updated_by, version)
	VALUES (32, 'SOLID', 10, 'solid',
	        current_timestamp, 'admin', 1);

INSERT INTO training.technology(
	id, technology_name, technology_category_id, search_string, last_updated_timestamp, last_updated_by, version)
	VALUES (33, 'Functional Programming', 10, 'functional-programming',
	        current_timestamp, 'admin', 1);

UPDATE training.technology set technology_category_id = 10 where technology_category_id = 9;

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (5, 7, 11,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (6, 7, 28,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (7, 8, 13,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (8, 8, 12,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (9, 9, 14,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (10, 10, 30,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (11, 11, 30,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (12, 12, 9,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (13, 12, 10,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (14, 1, 1,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (15, 1, 5,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (16, 1, 8,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (17, 1, 28,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (18, 2, 31,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (19, 2, 32,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (20, 3, 1,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (21, 3, 6,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (22, 3, 8,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (23, 3, 29,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (24, 4, 1,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (25, 4, 5,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (26, 4, 8,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (27, 4, 28,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (28, 5, 1,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (29, 5, 5,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (30, 5, 8,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (31, 5, 28,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (32, 5, 20,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (33, 5, 21,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (34, 5, 25,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (35, 6, 1,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (36, 6, 5,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (37, 6, 8,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (38, 6, 28,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (39, 6, 21,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (40, 6, 25,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (41, 6, 17,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (42, 13, 33,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (43, 14, 2,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (44, 14, 6,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (45, 14, 9,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (46, 15, 2,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (47, 15, 6,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (48, 15, 9,
	        current_timestamp, 'admin', 1);

INSERT INTO training.course_technology(
	id, course_id, technology_id, last_updated_timestamp, last_updated_by, version)
	VALUES (49, 15, 17,
	        current_timestamp, 'admin', 1);












