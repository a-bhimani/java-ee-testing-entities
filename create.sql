CREATE TABLE DEPARTMENTS (Dept_Id INTEGER AUTO_INCREMENT NOT NULL, Description VARCHAR(2000), Dept_Name VARCHAR(255) NOT NULL UNIQUE, PRIMARY KEY (Dept_Id))
CREATE TABLE department_offices (Dept_Contact_Id INTEGER AUTO_INCREMENT NOT NULL, Addr1 VARCHAR(255) NOT NULL, Addr2 VARCHAR(255), CState INTEGER NOT NULL, City VARCHAR(255) NOT NULL, EmailId VARCHAR(255) NOT NULL UNIQUE, Phone BIGINT NOT NULL, Title VARCHAR(255) NOT NULL UNIQUE, Zip INTEGER NOT NULL, Zip_Ext INTEGER, Office_Dept_Id INTEGER NOT NULL, PRIMARY KEY (Dept_Contact_Id))
CREATE TABLE VENUES (Venue_Id INTEGER AUTO_INCREMENT NOT NULL, Addr1 VARCHAR(255) NOT NULL, Addr2 VARCHAR(255), CState INTEGER NOT NULL, City VARCHAR(255) NOT NULL, Title VARCHAR(255) NOT NULL UNIQUE, Type INTEGER NOT NULL, Zip INTEGER NOT NULL, Zip_Ext INTEGER, PRIMARY KEY (Venue_Id))
CREATE TABLE STUDENTS (Student_Id INTEGER AUTO_INCREMENT NOT NULL, EmailId VARCHAR(255) NOT NULL UNIQUE, FName VARCHAR(255) NOT NULL, Gender CHAR(1) NOT NULL, LName VARCHAR(255) NOT NULL, NotifyEvents TINYINT(1) default 0 NOT NULL, Phone BIGINT NOT NULL, Special VARCHAR(2000), Student_Number VARCHAR(36) NOT NULL UNIQUE, pwd VARCHAR(255), PRIMARY KEY (Student_Id))
CREATE TABLE event_store (Event_Id INTEGER AUTO_INCREMENT NOT NULL, AState INTEGER NOT NULL, CDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, Description VARCHAR(2000), EBegin DATETIME NOT NULL, EEnd DATETIME NOT NULL, MDate TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, Title VARCHAR(255) NOT NULL UNIQUE, Event_Dept_Id INTEGER NOT NULL, Event_Venue_Id INTEGER NOT NULL, PRIMARY KEY (Event_Id))
CREATE TABLE event_student_attends (Student_Id INTEGER NOT NULL, Event_Id INTEGER NOT NULL, PRIMARY KEY (Student_Id, Event_Id))
ALTER TABLE department_offices ADD CONSTRAINT FK_department_offices_Office_Dept_Id FOREIGN KEY (Office_Dept_Id) REFERENCES DEPARTMENTS (Dept_Id)
ALTER TABLE event_store ADD CONSTRAINT FK_event_store_Event_Venue_Id FOREIGN KEY (Event_Venue_Id) REFERENCES VENUES (Venue_Id)
ALTER TABLE event_store ADD CONSTRAINT FK_event_store_Event_Dept_Id FOREIGN KEY (Event_Dept_Id) REFERENCES DEPARTMENTS (Dept_Id)
ALTER TABLE event_student_attends ADD CONSTRAINT FK_event_student_attends_Student_Id FOREIGN KEY (Student_Id) REFERENCES STUDENTS (Student_Id)
ALTER TABLE event_student_attends ADD CONSTRAINT FK_event_student_attends_Event_Id FOREIGN KEY (Event_Id) REFERENCES event_store (Event_Id)
