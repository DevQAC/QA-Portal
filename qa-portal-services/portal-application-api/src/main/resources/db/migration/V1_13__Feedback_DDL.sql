drop table if exists training.form_type CASCADE;

drop table if exists training.question CASCADE;

drop table if exists training.question_category CASCADE;

drop table if exists training.cohort_course CASCADE;

drop table if exists training.course CASCADE;

drop table if exists training.comment CASCADE;

drop table if exists training.question_response CASCADE;

drop table if exists training.question_category_response CASCADE;

drop table if exists training.cohort_course_feedback CASCADE;

drop table if exists training.cohort_course_evaluation CASCADE;

drop sequence if exists training.form_type_sequence;

drop sequence if exists training.question_sequence;

drop sequence if exists training.question_category_sequence;

drop sequence if exists training.cohort_course_sequence;

drop sequence if exists training.course_sequence;

drop sequence if exists training.comment_sequence;

drop sequence if exists training.question_response_sequence;

drop sequence if exists training.question_category_response_sequence;

drop sequence if exists training.cohort_course_feedback_sequence;

drop sequence if exists training.cohort_course_evaluation_sequence;


create table if not exists training.form_type
(
	id integer not null
		constraint form_type_pk
			primary key,
	form_name varchar(255) not null,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);

alter table training.form_type owner to postgres;


create table if not exists training.question_category
(
	id integer not null
		constraint question_category_pk
			primary key,
	category_name varchar(255) not null,
	has_comment boolean default false not null,
	selection_type varchar(255) default 'RADIO_BUTTON',
	display_direction varchar(255) default 'HORIZONTAL',
	comment_label varchar(510),
	form_type_id integer not null,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);

create table if not exists training.question
(
    id integer not null
        constraint question_pk
            primary key,
    body varchar(1000) not null,
    last_updated_timestamp timestamp not null,
    last_updated_by varchar(255) not null,
    version integer default 1 not null,
    category_id integer,
    has_comment boolean default false not null,
    comment_label varchar(510),
    selection_options text default '[''1'', ''2'', ''3'', ''4'', ''5'', ''6'', ''7'', ''8'', ''9'', ''10'']' not null
);

alter table training.question owner to postgres;

create table if not exists training.course
(
	id integer not null
		constraint course_pk
			primary key,
	course_name varchar(255) not null,
	course_code varchar(255) not null,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);

alter table training.course owner to postgres;


create table if not exists training.cohort_course
(
	id integer not null
		constraint cohort_course_pk
			primary key,
	course_id integer not null,
	cohort_id integer not null,
	trainer_id integer not null,
	start_date date not null,
	end_date date,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);

alter table training.cohort_course owner to postgres;

create table if not exists training.comment
(
	id integer not null
		constraint comment_pk
			primary key,
	content text,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);

alter table training.comment owner to postgres;


create table if not exists training.question_response
(
	id integer not null
		constraint question_response_pk
			primary key,
	question_id integer not null,
	comment_id integer,
	response_values varchar(510),
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null,
	new_column integer,
	question_category_response_id integer
);

alter table training.question_response owner to postgres;



create table if not exists training.question_category_response
(
	id integer not null
		constraint question_category_response_pk
			primary key,
	comment_id integer,
	cohort_course_evaluation_id integer,
	cohort_course_feedback_id integer,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null,
	discriminator varchar(255) not null,
	question_category_id integer not null
);

alter table training.question_category_response owner to postgres;



create table if not exists training.cohort_course_feedback
(
	id integer not null
		constraint cohort_course_feedback_pk
			primary key,
	cohort_course_id integer not null,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null,
	status varchar(255)
);

alter table training.cohort_course_feedback owner to postgres;



create table if not exists training.cohort_course_evaluation
(
	id integer not null
		constraint course_evaluation_pk
			primary key,
	trainee_id integer not null,
	cohort_course_id integer not null,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null,
	status varchar(255)
);

alter table training.cohort_course_evaluation owner to postgres;





create sequence training.form_type_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.form_type_sequence owner to postgres;


create sequence training.question_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.question_sequence owner to postgres;


create sequence training.question_category_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.question_category_sequence owner to postgres;


create sequence training.course_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.course_sequence owner to postgres;


create sequence training.cohort_course_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.cohort_course_sequence owner to postgres;


create sequence training.comment_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.comment_sequence owner to postgres;


create sequence training.question_response_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.question_response_sequence owner to postgres;


create sequence training.question_category_response_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.question_category_response_sequence owner to postgres;


create sequence training.cohort_course_feedback_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.cohort_course_feedback_sequence owner to postgres;


create sequence training.cohort_course_evaluation_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.cohort_course_evaluation_sequence owner to postgres;
