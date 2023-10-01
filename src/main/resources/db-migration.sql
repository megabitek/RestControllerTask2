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
INSERT INTO simple_entity ( NAME, uuid) VALUES ( 'masha', '11111111-2222-3333-4444-555555555555');
INSERT INTO simple_entity ( NAME, uuid) VALUES ( 'misha', '11111111-6666-3333-4444-555555555555');

INSERT INTO anothers (uuid, NAME, simple_uuid) VALUES ('7f9e408a-1b30-47f6-aace-482dc0854115', 'bob' ,   '76bde8dd-f961-4653-85f5-bcdc4ac171f0');
INSERT INTO anothers (uuid, NAME, simple_uuid) VALUES ('9ac11aa0-6441-4821-abca-2d8588ac488e', 'tobic' , '76bde8dd-f961-4653-85f5-bcdc4ac171f0');
INSERT INTO anothers (uuid, NAME, simple_uuid) VALUES ('9ac11aa0-3333-4444-5555-2d8588ac488e', 'tuzik' , '11111111-2222-3333-4444-555555555555');
INSERT INTO anothers (uuid, NAME, simple_uuid) VALUES ('90911230-5678-9101-5555-2d8588ac488e', 'barbos' , '11111111-2222-3333-4444-555555555555');

CREATE TABLE doctor (uuid uuid DEFAULT gen_random_uuid(), NAME VARCHAR, Last_Name VARCHAR );

CREATE TABLE Doctors_Pets (doctor_uuid uuid, pets_uuid uuid);

 ALTER TABLE doctor ADD CONSTRAINT DocPrimaryKey PRIMARY KEY(uuid);

 Alter TABLE doctors_Pets ADD  CONSTRAINT FK_Doctor_pets FOREIGN KEY (doctor_uuid) REFERENCES doctor (uuid);
 ALTER TABLE Doctors_Pets ADD CONSTRAINT FK_Pets_Doctor FOREIGN KEY ( pets_uuid ) REFERENCES anothers(uuid);

INSERT INTO doctor (uuid, NAME, last_name) VALUES ('74ac3d23-6b19-4db4-ad08-ca9469a98e9c', 'ivan', 'ivanov') ;
INSERT INTO doctor (uuid, NAME, last_name) VALUES ('57f0b580-a0eb-4b8f-b2a7-6296ea63371d','petr', 'petrov') ;
INSERT INTO doctor (uuid, NAME, last_name) VALUES ('2b26475b-f71b-449f-a082-1bca4e7ca8c7', 'victor', 'sidorov') ;
INSERT INTO doctor (uuid, NAME, last_name) VALUES ('9336043d-9b26-4587-bdd7-25dc507b60e4', 'andrei', 'andreev') ;

INSERT INTO doctors_pets (doctor_uuid, pets_uuid) VALUEs ('74ac3d23-6b19-4db4-ad08-ca9469a98e9c', '7f9e408a-1b30-47f6-aace-482dc0854115' );
INSERT INTO doctors_pets (doctor_uuid, pets_uuid) VALUEs ('74ac3d23-6b19-4db4-ad08-ca9469a98e9c', '9ac11aa0-6441-4821-abca-2d8588ac488e' );
INSERT INTO doctors_pets (doctor_uuid, pets_uuid) VALUEs ('2b26475b-f71b-449f-a082-1bca4e7ca8c7', '9ac11aa0-3333-4444-5555-2d8588ac488e' );






