CREATE DATABASE IF NOT EXISTS application;
-- 
USE application;
--
CREATE TABLE IF NOT EXISTS employee
(
id INTEGER AUTO_INCREMENT,
firstname VARCHAR(50) NOT NULL,
lastname VARCHAR(50) NOT NULL,
age INTEGER NOT NULL,
sex CHARACTER(1) DEFAULT 'N',
datebirth DATE NOT NULL,
jobstatus BOOLEAN DEFAULT FALSE,
levelofeducation VARCHAR(50) DEFAULT 'N/A',
salary DOUBLE DEFAULT 0.0,
netsalary DOUBLE GENERATED ALWAYS AS (salary - (salary * 0.257)) VIRTUAL,/*STORED,*/
PRIMARY KEY(id ASC, datebirth ASC),
INDEX datebirth_indx (datebirth ASC)
);
--
INSERT INTO employee(Id, Firstname, Lastname, Age, Sex, Datebirth, Jobstatus, Salary) 
VALUES (1, 'Panagiotis', 'Chronopoulos', 28, 'M', '1997-03-27', TRUE, 1000.0);
INSERT INTO employee(Firstname, Lastname, Age, Datebirth) VALUES ('nikos', 'stergiou', 28, '1997-02-17');
INSERT INTO employee(Firstname, Lastname, Age, Sex, Datebirth, Jobstatus, Salary, Levelofeducation) VALUES ('Popi', 'theofanopoulou', 30, 'F', '1995-04-15', TRUE, 1000.0, '4');
INSERT INTO employee(Firstname, Lastname, Age, Sex, Datebirth, Jobstatus, Salary, Levelofeducation) VALUES ('Makis', 'arvanitis', 40, 'M', '1985-05-21', TRUE, 2000.0, '5');
INSERT INTO employee(Firstname, Lastname, Age, Sex, Datebirth, Jobstatus, Salary, Levelofeducation) VALUES ('Giwrgos', 'maggos', 27, 'M', '1998-10-05', TRUE, 3000.0, '6');
INSERT INTO employee(Firstname, Lastname, Age, Sex, Datebirth, Jobstatus, Salary, Levelofeducation) VALUES ('alexandros', 'Spyropoulos', 30, 'M', '1995-04-19', TRUE, 2500.0, '7');
INSERT INTO employee(Firstname, Lastname, Age, Sex, Datebirth, Jobstatus, Salary, Levelofeducation) VALUES ('niki', 'alexiou', 33, 'F', '1992-04-19', TRUE, 1500.0, '8');
COMMIT;
--
SELECT * FROM employee ORDER BY Id ASC;
--
--
--
--
--
USE application;
--
DELETE FROM employee;
COMMIT;
--
TRUNCATE TABLE employee;
--
ALTER TABLE employee MODIFY id INTEGER NOT NULL;
--
ALTER TABLE employee DROP INDEX datebirth_indx;
--
ALTER TABLE employee DROP PRIMARY KEY;
--
DROP TABLE IF EXISTS employee;
--
DROP DATABASE IF EXISTS application;