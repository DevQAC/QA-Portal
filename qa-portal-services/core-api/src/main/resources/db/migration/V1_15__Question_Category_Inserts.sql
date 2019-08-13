INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (1, 'Technical Skills', false, null, 1, 'RADIO_BUTTON', 'HORIZONTAL',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (2, 'Soft Skills', false, null, 1, 'RADIO_BUTTON', 'HORIZONTAL',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (3, 'Attitude', false, null, 1, 'RADIO_BUTTON', 'HORIZONTAL',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (4, 'Evaluation Course Selection', false, null, 2, 'CHECK_BOX', 'INLINE',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (5, 'Evaluation Pre Course Experience', false, null, 2, 'RADIO_BUTTON', 'HORIZONTAL',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (6, 'Evaluation Trainer', false, null, 2, 'RADIO_BUTTON', 'HORIZONTAL',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (7, 'Evaluation Course Materials', false, null, 2, 'RADIO_BUTTON', 'HORIZONTAL',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (8, 'Evaluation Training Effectiveness', false, null, 2, 'RADIO_BUTTON', 'HORIZONTAL',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (9, 'Evaluation Recommend QA', false, null, 2, 'RADIO_BUTTON', 'HORIZONTAL',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (10, 'Evaluation Continued Professional Development', false, null, 2, 'CHECK_BOX', 'INLINE',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (11, 'Environment', true, 'Environment comments:', 3, 'RADIO_BUTTON', 'HORIZONTAL',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (12, 'Delegates', true, 'Delegate comments:', 3, 'RADIO_BUTTON', 'HORIZONTAL',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (13, 'Courseware', true, 'Courseware comments:', 3, 'RADIO_BUTTON', 'HORIZONTAL',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (14, 'Problem Reporting', true, 'Courseware comments:', 3, 'RADIO_BUTTON', 'VERTICAL',
        current_timestamp, 'admin', 1);

INSERT INTO training.question_category(
    id, category_name, has_comment, comment_label, form_type_id, selection_type, display_direction,
    last_updated_timestamp, last_updated_by, version)
VALUES (15, 'Overall Satisfaction', true, 'What could be done to improve the quality of this course:', 3, 'RADIO_BUTTON', 'VERTICAL',
        current_timestamp, 'admin', 1);