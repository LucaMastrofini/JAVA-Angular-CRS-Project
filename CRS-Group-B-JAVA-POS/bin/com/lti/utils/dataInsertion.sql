USE CRSDatabase;
INSERT INTO Roles(roleID,name,description) VALUES
(1,'Admin','Administrator/Registrar'),
(2,'Professor','Professor'),
(3,'Student','Full or Part time student');

INSERT INTO User(username,password,role) VALUES
('lucam',1234,3),
('amit',1234,2),
('nikhil',1234,1);

INSERT INTO Student(studentID,name,registrationApproved) VALUES(
1,'Luca',1
);

INSERT INTO Professor(professorID,name) VALUES(
2,'Amit'
);

INSERT INTO Admin VALUES(3);

INSERT INTO Course(name,department,description,professorID,prereqID) VALUES
('Math101','Math','Basics of math',NULL,NULL),
('Science101','Science','Basics of science',NULL,NULL),
('Math102','Math','Builds on basics',NULL,1);