DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS users CASCADE;

DROP SEQUENCE IF EXISTS global_seq CASCADE;

CREATE SEQUENCE global_seq
  AS INTEGER
  START WITH 1000;

CREATE TABLE users (
  id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  login     VARCHAR(255)          NOT NULL,
  password  VARCHAR(255)          NOT NULL,
  full_name VARCHAR(255),
  phone     VARCHAR(255),
  enabled   BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE roles (
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_role_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id)
  ON DELETE CASCADE
);










