delete from training.question;

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                       VALUES (6, 'How ambitious have you been to work on projects out of the Academy to improve your skills?', '3',
                              '2019-08-06 14:44:28.745289', 'admin', 1,
                              false, null, '["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                       VALUES (5, 'How well have you managed your time this week at the Academy?', '3',
                               '2019-08-06 14:44:28.745289', 'admin', 1,
                              false, null, '["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (2, 'How well would you be able to explain the concepts you have learnt this week to a peer?', '1',
                               '2019-08-06 14:44:28.745289', 'admin', 1,
                              false, null, '["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (1, 'How well have you been able to use the technologies and tools you have learnt this week to solve a solution?', '1',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                              false, null, '["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (4, 'How well have you been able to present ideas and concepts to the group this week?', '2',
                               '2019-08-06 14:44:28.745289', 'admin', 1,
                              false, null, '["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (3, 'How well have you driven high standards through collaboration and teamwork this week?', '2',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                              false, null, '["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (7, 'Our learning venue and facilities What was the main reason for attending your course?', '4',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, '', '["To learn new skills in my current role", "Encouraged by my line manager", "To achieve the certification", "It is part of my overall personal development", "Other (please specify)"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (8, 'Why did you choose to book your training with QA?', '4',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                false, null, '["I just heard about QA but never tried you before", "Someone called me from QA", "QA is my company''s chosen provider", "I saw an advert and then booked with QA", "Someone recommended QA to me", "I''ve used QA before for training"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (9, 'The pre booking process. Any required pre-course work and venue information supplied made me feel fully prepared for the course?', '5',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, 'Please provide any comments on your pre-course experience', '["N/A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (10, 'The venue, classroom and technology used had a positive impact on my overall learning experience', '5',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, 'Please provide any comments on the learning venue, facilities and technology', '["N/A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]');


INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (11, 'The trainer''s knowledge, delivery and experience helped me to learn more effectively', '6',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, 'Please provide any comments you have about our trainer', '["N/A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (12, 'The course materials had a positive impact on my learning experience', '7',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, 'Please provide any comments you have on your course materials', '["N/A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (13, 'What I learned will have a positive impact on my job performance', '8',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, 'Please provide any comments on your overall learning experience', '["N/A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (14, 'As a result of your overall experience, how likely are you to recommend QA to a colleague or friend?', '9',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, 'What is your primary reason for this response?', '["N/A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (15, 'lease let us know if you would like further information on the following areas. Please note, by checking the box(es) below we will send you a single update email to give you more information about the areas you are interested in. We will not add you to any 3rd party lists or marketing emails unless you have already given your permission to us.', '10',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, '', '["Cyber Security", "Project and Programme Management", "Web and App development / Devops", "Cloud", "Desktop applications (MS Office, Adobe etc)", "Service Management / ITIL", "Business Skills", "Leadership and Management", "IT / Digital Resourcing Solutions", "QA Higher Education Degrees", "QA Apprenticeships", "QA Consulting", "TAP Programmes (The Training Foundation", "Other (please specify)"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (16, '1. The room was set up correctly for the course:', '11',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                false, '', '["9", "8", "7", "6", "5", "4", "3", "2", "1"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (17, '2. The hardware / software was configured and functioned correctly:', '11',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                false, '', '["9", "8", "7", "6", "5", "4", "3", "2", "1"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (18, '3. The hardware level was suitable for the course:', '11',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                false, '', '["9", "8", "7", "6", "5", "4", "3", "2", "1"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (19, '4. The physical environment was conducive to learning:', '11',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                false, '', '["9", "8", "7", "6", "5", "4", "3", "2", "1"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (20, '1. The delegates had the correct level of prerequisite knowledge for the course:', '12',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                false, '', '["9", "8", "7", "6", "5", "4", "3", "2", "1"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (21, '2. The delegates were energetic and enthusiastic during the course:', '12',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                false, '', '["9", "8", "7", "6", "5", "4", "3", "2", "1"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (22, '3. The delegates actively took part in class activities:', '12',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                false, '', '["9", "8", "7", "6", "5", "4", "3", "2", "1"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (23, '4. The content of the course manual was appropriate to audience learning objectives:', '12',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                false, '', '["9", "8", "7", "6", "5", "4", "3", "2", "1"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (24, '1. The content of the course manual was accurate and error free:', '13',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                false, '', '["9", "8", "7", "6", "5", "4", "3", "2", "1"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (25, '2. The slides and learning aids were accurate and error free:', '13',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                false, '', '["9", "8", "7", "6", "5", "4", "3", "2", "1"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (26, '3. The balance between time spent on presentation and class exercises was appropriate:', '13',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                false, '', '["9", "8", "7", "6", "5", "4", "3", "2", "1"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (27, '4. The manuals were available at start of course:', '13',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                false, '', '["9", "8", "7", "6", "5", "4", "3", "2", "1"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (28, 'Do you have any environment problems to report:', '14',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, '', '["Yes", "No"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (29, 'Do you have any delegate problems to report:', '14',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, '', '["Yes", "No"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (30, 'Do you have any courseware problems to report:', '14',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, '', '["Yes", "No"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (31, 'Do you have any facilities problems to report:', '14',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, '', '["Yes", "No"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (32, 'Do you have any pre-course problems to report:', '14',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, '', '["Yes", "No"]');

INSERT INTO training.question (id, body, category_id,
                               last_updated_timestamp, last_updated_by, version,
                               has_comment, comment_label, selection_options)
                        VALUES (33, 'Overall, how would you rate the event asa a quality learning experience:', '15',
                                '2019-08-06 14:44:28.745289', 'admin', 1,
                                true, '', '["Very Satisfied", "Somewhat Satisfied", "Somewhat Dissatisfied", "Very Dissatisfied"]');



