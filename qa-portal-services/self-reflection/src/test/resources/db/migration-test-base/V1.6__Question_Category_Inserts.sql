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