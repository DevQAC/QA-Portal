drop table if exists training.technology_category CASCADE;

drop table if exists training.technology CASCADE;

drop table if exists training.course_technology CASCADE;

drop table if exists training.cv_status CASCADE;

create table if not exists training.technology_category
(
	id integer not null
		constraint technology_category_pk
			primary key,
	category_name varchar(255) not null,
	search_string varchar(255) not null,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);

alter table training.technology_category owner to postgres;


create table if not exists training.technology
(
	id integer not null
		constraint technology_pk
			primary key,
	technology_name varchar(255) not null,
	technology_category_id integer not null,
	search_string varchar(255) not null,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);

alter table training.technology owner to postgres;


create table if not exists training.course_technology
(
	id integer not null
		constraint course_technology_pk
			primary key,
	course_id integer not null,
	technology_id integer not null,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);

alter table training.course_technology owner to postgres;


create table if not exists training.cv_status
(
	id integer not null
		constraint cv_status_pk
			primary key,
	status_name varchar(255) not null,
	last_updated_timestamp timestamp not null,
	last_updated_by varchar(255) not null,
	version integer default 1 not null
);

alter table training.cv_status owner to postgres;






