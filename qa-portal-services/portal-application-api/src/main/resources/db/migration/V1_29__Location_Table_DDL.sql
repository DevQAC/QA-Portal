drop table if exists training.location CASCADE;

drop sequence if exists training.location_sequence;

drop sequence if exists training.technology_category_sequence;

drop sequence if exists training.technology_sequence;

drop sequence if exists training.course_technology_sequence;

drop sequence if exists training.cv_status_sequence;

create table if not exists training.location
(
	id integer not null
		constraint location_pk
			primary key,
	name varchar(255) not null,
	address varchar(512),
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);

alter table training.location owner to postgres;

alter table training.cohort_course
	add location_id int;

create sequence training.location_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.location_sequence owner to postgres;

create sequence training.technology_category_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.technology_category_sequence owner to postgres;

create sequence training.technology_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.technology_sequence owner to postgres;

create sequence training.course_technology_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.course_technology_sequence owner to postgres;

create sequence training.cv_status_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.cv_status_sequence owner to postgres;
