drop table if exists training.form_type CASCADE;

drop table if exists training.question CASCADE;

drop table if exists training.question_category CASCADE;


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





