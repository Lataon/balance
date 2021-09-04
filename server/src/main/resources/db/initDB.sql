DROP TABLE users IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
  id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name             VARCHAR(255)                 NOT NULL,
  tariff           VARCHAR(255)                 NOT NULL,
  balance          INTEGER                      NOT NULL
);