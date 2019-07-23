CREATE SCHEMA training
    AUTHORIZATION postgres;

CREATE TABLE training.application
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    url character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT application_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)

TABLESPACE pg_default;

ALTER TABLE training.application
    OWNER to postgres;

CREATE TABLE training.app_menu_item
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    url character varying(255) COLLATE pg_catalog."default" NOT NULL,
    level integer NOT NULL,
    app_id integer NOT NULL,
    tooltip character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT "app-menu-item_pkey" PRIMARY KEY (id),
    CONSTRAINT fk_app_id FOREIGN KEY (app_id)
        REFERENCES training.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE training.app_menu_item
    OWNER to postgres;


CREATE TABLE training.department
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    display_order integer NOT NULL DEFAULT 0,
    CONSTRAINT department_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE training.department
    OWNER to postgres;

CREATE TABLE training.role
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    level integer NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE training.role
    OWNER to postgres;


CREATE TABLE training.dept_role
(
    id integer NOT NULL,
    dept_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT "dept-role_pkey" PRIMARY KEY (id),
    CONSTRAINT fk_dept_id FOREIGN KEY (dept_id)
        REFERENCES training.department (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id)
        REFERENCES training.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE training.dept_role
    OWNER to postgres;


CREATE TABLE training.dept_role_application
(
    id integer NOT NULL,
    dept_role_id integer NOT NULL,
    app_id integer NOT NULL,
    CONSTRAINT "dept-role-application_pkey" PRIMARY KEY (id),
    CONSTRAINT fk_app_id FOREIGN KEY (app_id)
        REFERENCES training.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_dept_role_id FOREIGN KEY (dept_role_id)
        REFERENCES training.dept_role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE training.dept_role_application
    OWNER to postgres;


CREATE TABLE training.qa_user
(
    id integer NOT NULL,
    user_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    reviewer_id integer,
    last_updated_timestamp timestamp without time zone NOT NULL,
    last_updated_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    version integer NOT NULL DEFAULT 1,
    CONSTRAINT qa_user_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (reviewer_id)
        REFERENCES training.qa_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE training.qa_user
    OWNER to postgres;


CREATE TABLE training.qa_user_self_reflection_form
(
    id integer NOT NULL,
    qa_user_id integer NOT NULL,
    strengths_text character varying(4000) COLLATE pg_catalog."default" NOT NULL,
    weaknesses_text character varying(4000) COLLATE pg_catalog."default" NOT NULL,
    opportunities_text character varying(4000) COLLATE pg_catalog."default" NOT NULL,
    threats_text character varying(4000) COLLATE pg_catalog."default" NOT NULL,
    last_updated_timestamp timestamp without time zone NOT NULL,
    last_updated_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    week_commencing date NOT NULL DEFAULT CURRENT_DATE,
    version integer NOT NULL DEFAULT 1,
    CONSTRAINT qa_user_self_reflection_form_pkey PRIMARY KEY (id),
    CONSTRAINT fk_qa_user_id FOREIGN KEY (qa_user_id)
        REFERENCES training.qa_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE training.qa_user_self_reflection_form
    OWNER to postgres;

CREATE TABLE training.self_reflection_status
(
    id integer NOT NULL,
    status_text character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_updated_timestamp timestamp without time zone NOT NULL,
    last_updated_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    version integer NOT NULL DEFAULT 1,
    CONSTRAINT self_reflection_status_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE training.self_reflection_status
    OWNER to postgres;

CREATE TABLE training.qa_user_self_reflection_form_status
(
    id integer NOT NULL,
    qa_user_self_reflection_form_id integer NOT NULL,
    self_reflection_status_id integer NOT NULL,
    qa_user_id integer NOT NULL,
    last_updated_timestamp timestamp without time zone NOT NULL,
    last_updated_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    version integer NOT NULL DEFAULT 1,
    CONSTRAINT qa_user_self_reflection_form_status_pkey PRIMARY KEY (id),
    CONSTRAINT fk_qa_user_id FOREIGN KEY (qa_user_id)
        REFERENCES training.qa_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_qa_user_self_reflection_form_id FOREIGN KEY (qa_user_self_reflection_form_id)
        REFERENCES training.qa_user_self_reflection_form (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_self_reflection_status_id FOREIGN KEY (self_reflection_status_id)
        REFERENCES training.self_reflection_status (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE training.qa_user_self_reflection_form_status
    OWNER to postgres;


CREATE TABLE training.self_rating_question
(
    id integer NOT NULL,
    question_text character varying(255) COLLATE pg_catalog."default" NOT NULL,
    num_options integer NOT NULL,
    last_updated_timestamp timestamp without time zone NOT NULL,
    last_updated_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    version integer NOT NULL DEFAULT 1,
    CONSTRAINT self_rating_question_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE training.self_rating_question
    OWNER to postgres;


CREATE TABLE training.self_rating
(
    id integer NOT NULL,
    self_rating_question_id integer NOT NULL,
    qa_user_self_reflection_form_id integer NOT NULL,
    selected_rating character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_updated_timestamp timestamp without time zone NOT NULL,
    last_updated_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    version integer NOT NULL DEFAULT 1,
    CONSTRAINT self_rating_pkey PRIMARY KEY (id),
    CONSTRAINT fk_qa_user_self_reflection_form_id FOREIGN KEY (qa_user_self_reflection_form_id)
        REFERENCES training.qa_user_self_reflection_form (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_self_rating_question_id FOREIGN KEY (self_rating_question_id)
        REFERENCES training.self_rating_question (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE training.self_rating
    OWNER to postgres;



CREATE TABLE training.self_reflection_review
(
    id integer NOT NULL,
    qa_user_id integer NOT NULL,
    qa_user_self_reflection_form_id integer NOT NULL,
    trainer_comments character varying(4000) COLLATE pg_catalog."default" NOT NULL,
    learning_pathway character varying(4000) COLLATE pg_catalog."default" NOT NULL,
    last_updated_timestamp timestamp without time zone NOT NULL,
    last_updated_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    version integer NOT NULL DEFAULT 1,
    CONSTRAINT self_reflection_review_pkey PRIMARY KEY (id),
    CONSTRAINT fk_qa_user_id FOREIGN KEY (qa_user_id)
        REFERENCES training.qa_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_qa_user_self_reflection_form_id FOREIGN KEY (qa_user_self_reflection_form_id)
        REFERENCES training.qa_user_self_reflection_form (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE training.self_reflection_review
    OWNER to postgres;




CREATE SEQUENCE training.application_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999999
    CACHE 1;

ALTER SEQUENCE training.application_sequence
    OWNER TO postgres;


CREATE SEQUENCE training.dept_role_app_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999999
    CACHE 1;

ALTER SEQUENCE training.dept_role_app_sequence
    OWNER TO postgres;


CREATE SEQUENCE training.dept_role_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999999
    CACHE 1;

ALTER SEQUENCE training.dept_role_sequence
    OWNER TO postgres;


CREATE SEQUENCE training.dept_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999999
    CACHE 1;

ALTER SEQUENCE training.dept_sequence
    OWNER TO postgres;


CREATE SEQUENCE training.menu_item_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999999
    CACHE 1;

ALTER SEQUENCE training.menu_item_sequence
    OWNER TO postgres;


CREATE SEQUENCE training.qa_user_self_reflection_form_sequence
    INCREMENT 1
    START 65
    MINVALUE 1
    MAXVALUE 999999999
    CACHE 1;

ALTER SEQUENCE training.qa_user_self_reflection_form_sequence
    OWNER TO postgres;


CREATE SEQUENCE training.qa_user_self_reflection_form_status_sequence
    INCREMENT 1
    START 58
    MINVALUE 1
    MAXVALUE 999999999
    CACHE 1;

ALTER SEQUENCE training.qa_user_self_reflection_form_status_sequence
    OWNER TO postgres;


CREATE SEQUENCE training.role_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999999
    CACHE 1;

ALTER SEQUENCE training.role_sequence
    OWNER TO postgres;


CREATE SEQUENCE training.self_rating_question_sequence
    INCREMENT 1
    START 50
    MINVALUE 1
    MAXVALUE 999999999
    CACHE 1;

ALTER SEQUENCE training.self_rating_question_sequence
    OWNER TO postgres;


CREATE SEQUENCE training.self_rating_sequence
    INCREMENT 1
    START 79
    MINVALUE 1
    MAXVALUE 9999999
    CACHE 1;

ALTER SEQUENCE training.self_rating_sequence
    OWNER TO postgres;


CREATE SEQUENCE training.self_reflection_review_sequence
    INCREMENT 1
    START 50
    MINVALUE 1
    MAXVALUE 999999999
    CACHE 1;

ALTER SEQUENCE training.self_reflection_review_sequence
    OWNER TO postgres;


CREATE SEQUENCE training.self_reflection_status_sequence
    INCREMENT 1
    START 50
    MINVALUE 1
    MAXVALUE 999999999
    CACHE 1;

ALTER SEQUENCE training.self_reflection_status_sequence
    OWNER TO postgres;
