-- public.owners definition

-- Drop table

-- DROP TABLE owners;

CREATE TABLE owners (
	id serial4 NOT NULL,
	fullname varchar(50) NOT NULL,
	address varchar(50) NOT NULL,
	phone varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	username varchar(30) NULL,
	CONSTRAINT owners_pkey PRIMARY KEY (id)
);


-- public.species definition

-- Drop table

-- DROP TABLE species;

CREATE TABLE species (
	id int4 NOT NULL,
	"name" varchar(30) NOT NULL,
	CONSTRAINT species_pkey PRIMARY KEY (id)
);


-- public.users definition

-- Drop table

-- DROP TABLE users;

CREATE TABLE users (
	username varchar(50) NOT NULL,
	"password" varchar(500) NOT NULL,
	enabled bool NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (username)
);


-- public.authorities definition

-- Drop table

-- DROP TABLE authorities;

CREATE TABLE authorities (
	username varchar(50) NOT NULL,
	authority varchar(50) NOT NULL,
	CONSTRAINT authorities_pk PRIMARY KEY (username, authority),
	CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);


-- public.clinic definition

-- Drop table

-- DROP TABLE clinic;

CREATE TABLE clinic (
	id serial4 NOT NULL,
	"name" varchar(200) NOT NULL,
	email varchar(50) NULL,
	phone varchar(50) NULL,
	address varchar(50) NULL,
	owner_id int4 NULL,
	CONSTRAINT pk_clinic PRIMARY KEY (id),
	CONSTRAINT uk_clinic UNIQUE (name),
	CONSTRAINT clinic_owners_fk FOREIGN KEY (owner_id) REFERENCES owners(id)
);


-- public.pets definition

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


-- public.appointments definition

-- Drop table

-- DROP TABLE appointments;

CREATE TABLE appointments (
	id serial4 NOT NULL,
	pet_id int4 NOT NULL,
	clinic_id int4 NOT NULL,
	date_ timestamp NOT NULL,
	reason varchar(250) NULL,
	CONSTRAINT appointments_pkey PRIMARY KEY (id),
	CONSTRAINT fk_appointments_clinic FOREIGN KEY (clinic_id) REFERENCES clinic(id),
	CONSTRAINT fk_appointments_pet FOREIGN KEY (pet_id) REFERENCES pets(id)
);


-- public.clinic_records definition

-- Drop table

-- DROP TABLE clinic_records;

CREATE TABLE clinic_records (
	id serial4 NOT NULL,
	pet_id int4 NOT NULL,
	date_ timestamp NOT NULL,
	diagnose varchar(50) NOT NULL,
	treatment varchar(50) NOT NULL,
	CONSTRAINT clinic_records_pkey PRIMARY KEY (id),
	CONSTRAINT clinic_records_pets_fk FOREIGN KEY (pet_id) REFERENCES pets(id)
);