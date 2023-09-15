CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE simple_entity
(uuid uuid,
NAME VARCHAR)


ALTER TABLE simple_entity ADD uuid uuid  DEFAULT gen_random_uuid()

 ALTER TABLE simple_entity ADD CONSTRAINT SEPrimaryKey PRIMARY KEY(uuid)

 CREATE TABLE anothers (uuid uuid  DEFAULT gen_random_uuid(),  NAME varchar, simple_uuid uuid)


ALTER TABLE anothers ADD CONSTRAINT AnPrimaryKey PRIMARY KEY(uuid)


Alter TABLE anothers ADD  CONSTRAINT FK_Anothers FOREIGN KEY (simple_uuid) REFERENCES simple_entity (uuid)