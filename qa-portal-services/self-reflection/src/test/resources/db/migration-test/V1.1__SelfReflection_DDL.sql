CREATE SCHEMA training;

create table if not exists training.qa_user
(
	id integer not null
		constraint qa_user_pkey
			primary key,
	user_name varchar(255) not null,
	reviewer_id integer
		constraint fk_user_id
			references training.qa_user,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null,
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
   	version integer default 1 not null,
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


