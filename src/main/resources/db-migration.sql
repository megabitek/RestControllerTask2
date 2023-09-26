CREATE DATABASE DOCKERDATABASE;

CREATE USER docker;

CREATE EXTENSION IF NOT EXISTS pgcrypto;


CREATE TABLE simple_entity
(uuid uuid  DEFAULT gen_random_uuid(),
NAME VARCHAR);

ALTER TABLE simple_entity ADD CONSTRAINT SEPrimaryKey PRIMARY KEY(uuid);

CREATE TABLE anothers (uuid uuid  DEFAULT gen_random_uuid(),  NAME varchar, simple_uuid uuid);

ALTER TABLE anothers ADD CONSTRAINT AnPrimaryKey PRIMARY KEY(uuid);

ALTER TABLE anothers ADD  CONSTRAINT FK_Anothers FOREIGN KEY (simple_uuid) REFERENCES simple_entity (uuid);



INSERT INTO simple_entity ( NAME, uuid) VALUES ( 'vova', '76bde8dd-f961-4653-85f5-bcdc4ac171f0');


INSERT INTO anothers (NAME, simple_uuid) VALUES ('bob' , '76bde8dd-f961-4653-85f5-bcdc4ac171f0');
INSERT INTO anothers (NAME, simple_uuid) VALUES ('tobic' , '76bde8dd-f961-4653-85f5-bcdc4ac171f0')