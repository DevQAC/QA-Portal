INSERT INTO training.location (id, name, address, last_updated_timestamp, last_updated_by, version)
    values (1, 'Anchorage Quays', 'Anchorage Quays, Manchester',
            current_timestamp, 'admin', 1);

update training.cohort_course set location_id = 1;
