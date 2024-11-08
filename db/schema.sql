CREATE SCHEMA IF NOT EXISTS my_schema;
SET search_path TO my_schema;
CREATE TABLE IF NOT EXISTS roles
(
    role_id    INTEGER NOT NULL CONSTRAINT roles_pk PRIMARY KEY,
    role_value VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
    user_id      UUID NOT NULL CONSTRAINT users_pk PRIMARY KEY,
    first_name   VARCHAR(50) NOT NULL,
    last_name    VARCHAR(50) NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    password     VARCHAR(100) NOT NULL,
    phone_number VARCHAR(25),
    roles_id     INTEGER NOT NULL CONSTRAINT users_roles REFERENCES roles(role_id)
);

CREATE TABLE IF NOT EXISTS procedures
(
    procedure_id       INTEGER NOT NULL CONSTRAINT procedure_pk PRIMARY KEY,
    procedure_name     VARCHAR(255) NOT NULL,
    procedure_price    DECIMAL(10, 2) CHECK (procedure_price > 0),
    procedure_duration INTEGER CHECK (procedure_duration > 0)
);

CREATE TABLE IF NOT EXISTS master_has_procedures
(
    master_execution_id    INTEGER NOT NULL CONSTRAINT master_has_procedures_pk PRIMARY KEY,
    master_id              UUID NOT NULL REFERENCES users(user_id),
    procedure_id           INTEGER NOT NULL REFERENCES procedures(procedure_id)
);

CREATE TABLE IF NOT EXISTS working_days
(
    working_day_id  SERIAL NOT NULL CONSTRAINT working_days_pk PRIMARY KEY,
    master_id       UUID NOT NULL CONSTRAINT working_days_master REFERENCES users(user_id),
    working_date    DATE NOT NULL CHECK (working_date > CURRENT_DATE),
    work_start      TIME NOT NULL,
    work_end        TIME NOT NULL
);

CREATE TABLE IF NOT EXISTS bookings
(
    booking_id         SERIAL NOT NULL CONSTRAINT bookings_pk PRIMARY KEY,
    client_id          UUID NOT NULL CONSTRAINT bookings_client REFERENCES users(user_id),
    reservation_start  TIME NOT NULL,
    reservation_end    TIME NOT NULL,
    procedure_id       INTEGER NOT NULL CONSTRAINT bookings_procedure REFERENCES procedures(procedure_id),
    working_day_id     INTEGER NOT NULL CONSTRAINT bookings_working_day REFERENCES working_days(working_day_id),
    CONSTRAINT check_reservation CHECK (reservation_start < reservation_end)
);


