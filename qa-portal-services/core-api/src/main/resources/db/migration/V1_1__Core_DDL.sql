drop table if exists training.dept_role_menu_item CASCADE;

drop table if exists training.dept_role_application CASCADE;

drop table if exists training.portal_application CASCADE;

drop table if exists training.portal_project CASCADE;

drop table if exists training.project_page CASCADE;

drop table if exists training.role_project_page CASCADE;

drop table if exists training.role_menu_item CASCADE;

drop table if exists training.dept_role CASCADE;

drop table if exists training.app_menu_item CASCADE;

drop table if exists training.role CASCADE;

drop table if exists training.department CASCADE;

drop table if exists training.application CASCADE;

drop table if exists training.question CASCADE;

drop table if exists training.cohort_question CASCADE;

drop table if exists training.qa_user CASCADE;

drop table if exists training.qa_cohort CASCADE;

drop table if exists training.reflection CASCADE;

drop table if exists training.reflection_question CASCADE;

drop table if exists training.qa_cohort CASCADE;

drop table if exists training.qa_user CASCADE;

CREATE SCHEMA IF NOT EXISTS training;

create schema if not exists training authorization postgres;

drop sequence if exists training.app_menu_item_sequence;

drop sequence if exists training.application_sequence;

drop sequence if exists training.cohort_question_sequence;

drop sequence if exists training.department_sequence;

drop sequence if exists training.dept_role_sequence;

drop sequence if exists training.dept_role_application_sequence;

drop sequence if exists training.dept_role_menu_item_sequence;

drop sequence if exists training.qa_cohort_sequence;

drop sequence if exists training.qa_user_sequence;

drop sequence if exists training.question_sequence;

drop sequence if exists training.reflection_sequence;

drop sequence if exists training.reflection_question_sequence;

drop sequence if exists training.role_sequence;

create table if not exists training.application
(
	id integer not null
		constraint application_pkey
			primary key,
	name varchar(255) not null,
	url varchar(255) not null
);

alter table training.application owner to postgres;


create table if not exists training.department
(
	id integer not null
		constraint department_pkey
			primary key,
	name varchar(255) not null,
	description varchar(255),
	display_order integer default 0 not null
);

alter table training.department owner to postgres;


create table training.role
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    constraint role_pkey PRIMARY KEY (id)
);

alter table training.role owner to postgres;


create table if not exists training.app_menu_item
(
	id integer not null
		constraint "app-menu-item_pkey"
			primary key,
	name varchar(255) not null,
	url varchar(255) not null,
	app_id integer not null
		constraint fk_app_id
			references training.application,
	tooltip varchar(255)
);

alter table training.app_menu_item owner to postgres;


create table if not exists training.dept_role
(
	id integer not null
		constraint "dept-role_pkey"
			primary key,
	dept_id integer not null
		constraint fk_dept_id
			references training.department,
	role_id integer not null
		constraint fk_role_id
			references training.role
);

alter table training.dept_role owner to postgres;


create table if not exists training.dept_role_application
(
	id integer not null
		constraint "dept-role-application_pkey"
			primary key,
	dept_role_id integer not null
		constraint fk_dept_role_id
			references training.dept_role,
	app_id integer not null
		constraint fk_app_id
			references training.application
);

alter table training.dept_role_application owner to postgres;


create table if not exists training.dept_role_menu_item
(
	id integer not null
		constraint dept_role_menu_item_pk
			primary key,
	dept_role_id integer not null,
	menu_item_id integer not null
);

alter table training.dept_role_menu_item owner to postgres;

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

alter table training.qa_user owner to postgres;


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

alter table training.qa_cohort owner to postgres;

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

alter table training.question owner to postgres;


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

alter table training.cohort_question owner to postgres;


create table if not exists training.reflection
(
	id integer not null
		constraint reflection_pk
			primary key,
	reviewer_id integer,
	responder_id integer,
	form_date date not null,
	trainer_feedback varchar(4000),
	learning_pathway varchar(4000),
	strengths varchar(4000),
	weaknesses varchar(4000),
	opportunities varchar(4000),
	threats varchar(4000),
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	status varchar(20) not null,
	version integer default 1 not null
);

alter table training.reflection owner to postgres;


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

alter table training.reflection_question owner to postgres;

--

create sequence training.app_menu_item_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.app_menu_item_sequence owner to postgres;

create sequence training.application_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.application_sequence owner to postgres;

create sequence training.cohort_question_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.cohort_question_sequence owner to postgres;

create sequence training.department_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.department_sequence owner to postgres;

create sequence training.dept_role_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.dept_role_sequence owner to postgres;

create sequence training.dept_role_application_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.dept_role_application_sequence owner to postgres;

create sequence training.dept_role_menu_item_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.dept_role_menu_item_sequence owner to postgres;

create sequence training.qa_cohort_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.qa_cohort_sequence owner to postgres;

create sequence training.qa_user_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.qa_user_sequence owner to postgres;

create sequence training.question_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.question_sequence owner to postgres;

create sequence training.reflection_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.reflection_sequence owner to postgres;

create sequence training.reflection_question_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.reflection_question_sequence owner to postgres;

create sequence training.role_sequence
    minvalue 50
    maxvalue 999999999;

alter sequence training.role_sequence owner to postgres;

