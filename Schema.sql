-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION pg_database_owner;

-- Drop table

-- DROP TABLE address;

CREATE TABLE address (
	id serial4 NOT NULL,
	country_code varchar(50) NOT NULL,
	region varchar(50) NULL,
	city varchar(50) NOT NULL,
	zip_code varchar(10) NULL,
	address_line_1 varchar(200) NOT NULL,
	address_line_2 varchar(200) NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id),
	CONSTRAINT address_countries_fk FOREIGN KEY (country_code) REFERENCES countries("name")
);

-- Drop table

-- DROP TABLE appointments;

CREATE TABLE appointments (
	id serial4 NOT NULL,
	pet_id int4 NOT NULL,
	clinic_id int4 NOT NULL,
	date_ timestamp NOT NULL,
	reason varchar(250) NULL,
	deleted bool DEFAULT false NOT NULL,
	CONSTRAINT appointments_pkey PRIMARY KEY (id),
	CONSTRAINT fk_appointments_clinic FOREIGN KEY (clinic_id) REFERENCES clinic(id),
	CONSTRAINT fk_appointments_pet FOREIGN KEY (pet_id) REFERENCES pets(id)
);

-- Drop table

-- DROP TABLE authorities;

CREATE TABLE authorities (
	username varchar(50) NOT NULL,
	authority varchar(50) NOT NULL,
	CONSTRAINT authorities_pk PRIMARY KEY (username, authority),
	CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);

-- Drop table

-- DROP TABLE clinic;

CREATE TABLE clinic (
	id serial4 NOT NULL,
	"name" varchar(200) NOT NULL,
	email varchar(50) NULL,
	phone varchar(50) NULL,
	owner_id int4 NULL,
	address_id int4 NULL,
	CONSTRAINT pk_clinic PRIMARY KEY (id),
	CONSTRAINT uk_clinic UNIQUE (name),
	CONSTRAINT clinic_owners_fk FOREIGN KEY (owner_id) REFERENCES owners(id),
	CONSTRAINT fk_clinic_address FOREIGN KEY (address_id) REFERENCES address(id)
);

-- Drop table

-- DROP TABLE clinic_records;

CREATE TABLE clinic_records (
	id serial4 NOT NULL,
	pet_id int4 NOT NULL,
	date_ timestamp NOT NULL,
	diagnose varchar(50) NOT NULL,
	treatment varchar(50) NOT NULL,
	appointments_id int4 NULL,
	CONSTRAINT clinic_records_pkey PRIMARY KEY (id),
	CONSTRAINT clinic_records_appointments_fk FOREIGN KEY (appointments_id) REFERENCES appointments(id),
	CONSTRAINT clinic_records_pets_fk FOREIGN KEY (pet_id) REFERENCES pets(id)
);

-- Drop table

-- DROP TABLE countries;

CREATE TABLE countries (
	code varchar(50) NOT NULL,
	"name" varchar(50) NOT NULL,
	CONSTRAINT countries_pk PRIMARY KEY (code),
	CONSTRAINT countries_unique UNIQUE (name)
);

-- Drop table

-- DROP TABLE owners;

CREATE TABLE owners (
	id serial4 NOT NULL,
	fullname varchar(50) NOT NULL,
	phone varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	username varchar(30) NULL,
	address_id int4 NULL,
	CONSTRAINT owners_pkey PRIMARY KEY (id),
	CONSTRAINT fk_owners_address FOREIGN KEY (address_id) REFERENCES address(id),
	CONSTRAINT owners_users_fk FOREIGN KEY (username) REFERENCES users(username)
);

-- Drop table

-- DROP TABLE pets;

CREATE TABLE pets (
	id serial4 NOT NULL,
	species_id int4 NOT NULL,
	breed varchar(50) NOT NULL,
	birth_date date NULL,
	owner_id int4 NOT NULL,
	"name" varchar(80) NULL,
	CONSTRAINT pets_pkey PRIMARY KEY (id),
	CONSTRAINT fk_pet_species FOREIGN KEY (species_id) REFERENCES species(id),
	CONSTRAINT pk_pet_owners FOREIGN KEY (owner_id) REFERENCES owners(id)
);

-- Drop table

-- DROP TABLE species;

CREATE TABLE species (
	id int4 NOT NULL,
	"name" varchar(30) NOT NULL,
	CONSTRAINT species_pkey PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE users;

CREATE TABLE users (
	username varchar(50) NOT NULL,
	"password" varchar(500) NOT NULL,
	enabled bool NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (username)
);