CREATE TABLE students_login (LOGINID INTEGER AUTO_INCREMENT NOT NULL, enc_pwd VARCHAR(255), USERNAME VARCHAR(63) UNIQUE, PRIMARY KEY (LOGINID))
CREATE TABLE departments (PID INTEGER AUTO_INCREMENT NOT NULL, Description VARCHAR(2000), Dept_Name VARCHAR(255) NOT NULL UNIQUE, PRIMARY KEY (PID))
CREATE TABLE event_store (PID INTEGER AUTO_INCREMENT NOT NULL, AState INTEGER NOT NULL, CDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, Description VARCHAR(2000), EBegin DATETIME NOT NULL, EEnd DATETIME NOT NULL, MDate TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, Title VARCHAR(255) NOT NULL UNIQUE, Event_Dept_Id INTEGER NOT NULL, Event_Venue_Id INTEGER NOT NULL, PRIMARY KEY (PID))
CREATE TABLE students (PID INTEGER AUTO_INCREMENT NOT NULL, EmailId VARCHAR(255) NOT NULL UNIQUE, FName VARCHAR(255) NOT NULL, Gender CHAR(1) NOT NULL, LName VARCHAR(255) NOT NULL, NotifyEvents TINYINT(1) default 0 NOT NULL, Phone BIGINT NOT NULL, Special VARCHAR(2000), Student_Number VARCHAR(36) NOT NULL UNIQUE, LoginId INTEGER NOT NULL UNIQUE, PRIMARY KEY (PID))
CREATE TABLE department_offices (PID INTEGER AUTO_INCREMENT NOT NULL, Addr1 VARCHAR(255) NOT NULL, Addr2 VARCHAR(255), CState INTEGER NOT NULL, City VARCHAR(255) NOT NULL, EmailId VARCHAR(255) NOT NULL UNIQUE, Phone BIGINT NOT NULL, Title VARCHAR(255) NOT NULL UNIQUE, Zip INTEGER NOT NULL, Zip_Ext INTEGER, Office_Dept_Id INTEGER NOT NULL, PRIMARY KEY (PID))
CREATE TABLE venues (PID INTEGER AUTO_INCREMENT NOT NULL, Addr1 VARCHAR(255) NOT NULL, Addr2 VARCHAR(255), CState INTEGER NOT NULL, City VARCHAR(255) NOT NULL, Title VARCHAR(255) NOT NULL UNIQUE, Type INTEGER NOT NULL, Zip INTEGER NOT NULL, Zip_Ext INTEGER, PRIMARY KEY (PID))
CREATE TABLE event_student_attends (Event_Id INTEGER NOT NULL, Student_Id INTEGER NOT NULL, PRIMARY KEY (Event_Id, Student_Id))
ALTER TABLE event_store ADD CONSTRAINT FK_event_store_Event_Venue_Id FOREIGN KEY (Event_Venue_Id) REFERENCES venues (PID)
ALTER TABLE event_store ADD CONSTRAINT FK_event_store_Event_Dept_Id FOREIGN KEY (Event_Dept_Id) REFERENCES departments (PID)
ALTER TABLE students ADD CONSTRAINT FK_students_LoginId FOREIGN KEY (LoginId) REFERENCES students_login (LOGINID)
ALTER TABLE department_offices ADD CONSTRAINT FK_department_offices_Office_Dept_Id FOREIGN KEY (Office_Dept_Id) REFERENCES departments (PID)
ALTER TABLE event_student_attends ADD CONSTRAINT FK_event_student_attends_Event_Id FOREIGN KEY (Event_Id) REFERENCES event_store (PID)
ALTER TABLE event_student_attends ADD CONSTRAINT FK_event_student_attends_Student_Id FOREIGN KEY (Student_Id) REFERENCES students (PID)
