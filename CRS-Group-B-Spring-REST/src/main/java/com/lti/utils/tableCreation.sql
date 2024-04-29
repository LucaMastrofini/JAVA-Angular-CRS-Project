create database if not exists CRSDatabase;
use CRSDatabase;

CREATE TABLE Roles(
	roleID int NOT NULL UNIQUE,
	name varchar(20) NOT NULL,
	description varchar(200),
	CONSTRAINT PK_RoleID PRIMARY KEY (roleID)
);

CREATE TABLE User(
	userID int NOT NULL AUTO_INCREMENT,
	username varchar(24) NOT NULL UNIQUE,
	password varchar(24) NOT NULL,
	role int NOT NULL,
	CONSTRAINT PK_UserID PRIMARY KEY (userID),
	CONSTRAINT FK_Role FOREIGN KEY (role)
	REFERENCES Roles(roleID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Student(
	studentID int NOT NULL UNIQUE,
	name varchar(60) NOT NULL,
	registrationApproved bool,
	CONSTRAINT PK_Student PRIMARY KEY (studentID),
	CONSTRAINT FK_StudentID FOREIGN KEY (studentID) 
	REFERENCES User(userID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Professor(
	professorID int NOT NULL UNIQUE,
	name varchar(60) NOT NULL,
	CONSTRAINT PK_Professor PRIMARY KEY (professorID),
	CONSTRAINT FK_ProfesorID FOREIGN KEY (professorID)
	REFERENCES User(userID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Admin(
	adminID int NOT NULL UNIQUE,
	CONSTRAINT PK_Admin PRIMARY KEY (adminID),
	CONSTRAINT FK_AdminID FOREIGN KEY (adminID)
	REFERENCES User(userID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Course(
	courseID int NOT NULL AUTO_INCREMENT,
	name varchar(20),
	department varchar(20),
	description varchar(60),
	professorID int,
	prereqID int,
	CONSTRAINT PK_Course PRIMARY KEY (courseID),
	CONSTRAINT FK_ProfessorID FOREIGN KEY (professorID)
	REFERENCES Professor(professorID) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FK_PrereqID FOREIGN KEY (prereqID)
	REFERENCES Course(courseID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE RegisteredCourse(
	studentID int NOT NULL,
	courseID int NOT NULL,
	feePaid bool DEFAULT 0,
	grade double,
	CONSTRAINT PK_StudentCourse PRIMARY KEY (studentID, courseID),
	CONSTRAINT FK_StudentID1 FOREIGN KEY (studentID)
	REFERENCES Student(studentID) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FK_CourseID1 FOREIGN KEY (courseID)
	REFERENCES Course(courseID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE SemesterRegistration(
	studentID int NOT NULL,
	semester int NOT NULL,
	CONSTRAINT PK_StudentSemester PRIMARY KEY (studentID, semester),
	CONSTRAINT FK_StudentID2 FOREIGN KEY (studentID)
	REFERENCES Student(studentID) ON DELETE CASCADE ON UPDATE CASCADE
);