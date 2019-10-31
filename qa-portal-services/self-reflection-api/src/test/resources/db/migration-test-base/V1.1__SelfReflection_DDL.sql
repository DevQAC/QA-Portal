CREATE SCHEMA IF NOT EXISTS training;

create table if not exists training.form_type
(
	id integer not null
		constraint form_type_pk
			primary key,
	form_name varchar(255) not null,
	description varchar(255),
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);

create table if not exists training.qa_user
(
	id integer not null
		constraint qa_user_pkey
			primary key,
	user_name varchar(255) not null,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	reviewer_id integer
		constraint fk_user_id
			references training.qa_user,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null,
	role varchar(255) not null,
	cohort_id integer
);


create table if not exists training.qa_cohort
(
	id integer not null
		constraint qa_cohort_pk
			primary key,
	cohort_name varchar(255) not null,
	trainer_id integer not null,
	last_updated_timestamp timestamp not null,
   	last_updated_by varchar(255) not null,
   	start_date date not null,
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


create table if not exists training.cohort_question
(
	id integer not null
		constraint cohort_question_pk
			primary key,
	cohort_id integer not null,
	question_id integer not null,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);


create table if not exists training.reflection
(
	id integer not null
		constraint reflection_pk
			primary key,
	reviewer_id integer,
	responder_id integer,
	form_date date not null,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);


create table if not exists training.reflection_question
(
	id integer not null
		constraint reflection_question_pk
			primary key,
	reflection_id integer not null,
	question_id integer not null,
	response integer,
	trainer_response integer,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);

