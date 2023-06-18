-- liquibase formatted sql
-- changeset liquibase:initial splitStatements:false

CREATE SCHEMA IF NOT EXISTS aux;

CREATE OR REPLACE FUNCTION aux.create_active_view(schema_name character varying, table_name character varying)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
BEGIN
    EXECUTE (
      'CREATE VIEW   ' || schema_name || '.' || table_name || '_active AS (' ||
      'SELECT * FROM ' || schema_name || '.' || table_name || ' WHERE t_active IS TRUE);'
    );
END;
$function$;

CREATE OR REPLACE FUNCTION aux.drop_active_views()
 RETURNS void
 LANGUAGE plpgsql
AS $function$
DECLARE
  r RECORD;
BEGIN
  FOR r IN
    SELECT t.table_schema, t.table_name
    FROM information_schema.tables t
    WHERE
        t.table_type = 'VIEW'
      AND t.table_name like '%\_active'
    LOOP
      EXECUTE 'DROP VIEW ' || r.table_schema || '.' || r.table_name || ';';
    END LOOP;

END;
$function$;

CREATE SCHEMA IF NOT EXISTS mng;

CREATE TABLE IF NOT EXISTS mng.user (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    last_name VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    status BOOLEAN DEFAULT false,
    registration_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    t_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_user_username ON mng.user (username);
CREATE INDEX IF NOT EXISTS idx_user_email ON mng.user (email);
CREATE INDEX IF NOT EXISTS idx_user_first_name ON mng.user (first_name);
CREATE INDEX IF NOT EXISTS idx_user_middle_name ON mng.user (middle_name);
CREATE INDEX IF NOT EXISTS idx_user_last_name ON mng.user (last_name);
CREATE INDEX IF NOT EXISTS idx_user_registration_time ON mng.user (registration_time);

SELECT aux.create_active_view('mng', 'user');

CREATE TABLE IF NOT EXISTS mng.email (
    id SERIAL PRIMARY KEY,
    recipient VARCHAR(100) NOT NULL,
    subject VARCHAR(255),
    content TEXT NOT NULL,
    sent_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    t_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_email_recipient ON mng.email (recipient);
CREATE INDEX IF NOT EXISTS idx_email_sent_on ON mng.email (sent_on);

SELECT aux.create_active_view('mng', 'email');