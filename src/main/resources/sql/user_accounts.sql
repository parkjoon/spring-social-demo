-- Table: user_accounts

-- DROP TABLE user_accounts;

CREATE TABLE user_accounts
(
  id bigint NOT NULL,
  creation_time timestamp without time zone NOT NULL,
  modification_time timestamp without time zone NOT NULL,
  version bigint NOT NULL,
  email character varying(100) NOT NULL,
  first_name character varying(100) NOT NULL,
  last_name character varying(100) NOT NULL,
  password character varying(255),
  role character varying(20) NOT NULL,
  sign_in_provider character varying(20),
  CONSTRAINT user_accounts_pkey PRIMARY KEY (id),
  CONSTRAINT uk_f9sl209luxhu4rylls0h1m625 UNIQUE (email)
)
WITH (
  OIDS=FALSE
);
