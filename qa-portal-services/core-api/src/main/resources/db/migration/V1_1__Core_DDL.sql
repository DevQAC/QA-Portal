drop table if exists training.dept_role_menu_item CASCADE;

drop table if exists training.dept_role_application CASCADE;

drop table if exists training.dept_role CASCADE;

drop table if exists training.app_menu_item CASCADE;

drop table if exists training.role CASCADE;

drop table if exists training.department CASCADE;

drop table if exists training.application CASCADE;

CREATE SCHEMA IF NOT EXISTS training;

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
