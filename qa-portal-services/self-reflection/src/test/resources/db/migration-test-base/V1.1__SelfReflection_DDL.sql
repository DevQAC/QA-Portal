CREATE SCHEMA IF NOT EXISTS training;

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
   	version integer default 1 not null
);

create table if not exists training.question
(
	id integer not null
		constraint question_pk
			primary key,
	body varchar(1000) not null,
	category varchar(255) not null,
	num_options integer not null,
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

