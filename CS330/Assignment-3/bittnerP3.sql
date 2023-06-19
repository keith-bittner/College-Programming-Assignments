DROP DATABASE IF EXISTS
	dbBittnerTrack;

CREATE DATABASE dbBittnerTrack;

USE dbBittnerTrack;

CREATE TABLE schools (
	school_Id INT AUTO_INCREMENT,
	school_Name VARCHAR(30),
	PRIMARY KEY (school_Id),
    UNIQUE KEY (school_Name));
    
CREATE TABLE athletes (
	comp_Id INT AUTO_INCREMENT,
	last_Name VARCHAR(20),
	first_Name VARCHAR(20),
	gender ENUM('M','F'),
	school_Id INT NOT NULL,
	meet_Dq BOOLEAN,
	PRIMARY KEY (comp_Id),
	FOREIGN KEY (school_Id) REFERENCES schools(school_Id),
	UNIQUE KEY unique_athlete (last_Name, first_Name, school_Id));

CREATE TABLE _events (
	event_Id INT AUTO_INCREMENT,
	event_Name VARCHAR(20),
	event_Type VARCHAR(10),
	event_Gender ENUM('M','F'),
	PRIMARY KEY (event_Id));

CREATE TABLE event_Results (
	event_Id INT NOT NULL,
	comp_Id INT NOT NULL,
	school_Id INT NOT NULL,
	score DECIMAL(8,2),
	placing INT,
	points INT,
	event_Dq BOOLEAN,
	FOREIGN KEY (event_Id) REFERENCES _events(event_Id),
	FOREIGN KEY (comp_Id) REFERENCES athletes(comp_Id),
	FOREIGN KEY (school_Id) REFERENCES schools(school_Id));

CREATE TABLE meet_Results (
	school_Id INT NOT NULL,
	school_Name VARCHAR(30),
	points INT);

DELIMITER //
CREATE TRIGGER updateMeetResults
AFTER INSERT ON event_Results
FOR EACH ROW
BEGIN
	DECLARE oldPoints INT;
    SET oldPoints = (SELECT points FROM meet_Results WHERE school_Id = NEW.school_Id);
    
    UPDATE meet_Results
    SET points = oldPoints + NEW.points
    WHERE (school_Id = NEW.school_Id);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE finalMeetResults()
BEGIN
	SELECT school_Name, points
	FROM meet_Results
	ORDER BY points DESC LIMIT 3;
END //
DELIMITER ;

INSERT INTO Schools (school_Name)
	VALUES
		('Gateway Senior HS'),
		('Penn Hill Senior HS'),
		('Plum Senior HS'),
		('Kiski Area HS'),
		('Butler Area HS'),
		('Shaler Area HS'),
		('Greensburg Area HS'),
		('Central Catholic'),
		('North Hills Senior HS'),
		('Latrobe Senior HS');

INSERT INTO Athletes (last_Name, first_Name, gender, school_Id, meet_Dq)
	VALUES
		('Howard', 'Chadwick', 'M', 1, FALSE),
		('Edwards', 'Bart', 'M', 1, FALSE),
		('Fenton', 'Rick', 'M', 1, FALSE),
		('Donnelly', 'Tom', 'M', 1, FALSE),
		('Gordon', 'William', 'M', 1, FALSE),
		('Dunbar', 'Hayden', 'F', 1, FALSE),
		('Mcneill', 'Charlize', 'F', 1, FALSE),
		('Jennson', 'Rylee', 'F', 1, FALSE),
		('Yoman', 'Marilyn', 'F', 1, FALSE),
		('Heaton', 'Alison', 'F', 1, FALSE),
		('Jackson', 'Enoch', 'M', 2, FALSE),
		('Wright', 'Julius', 'M', 2, FALSE),
		('Alcroft', 'Matthew', 'M', 2, FALSE),
		('Walker', 'Hayden', 'M', 2, FALSE),
		('Phillips', 'Chad', 'M', 2, FALSE),
		('Vane', 'Gina', 'F', 2, FALSE),
		('Farmer', 'Mandy', 'F', 2, FALSE),
		('Carter', 'Erin', 'F', 2, FALSE),
		('Emmett', 'Gladys', 'F', 2, FALSE),
		('Robe', 'Bethany', 'F', 2, FALSE),
		('Quinn', 'Bryon', 'M', 3, FALSE),
		('Lee', 'Russel', 'M', 3, FALSE),
		('Woodcock', 'Henry', 'M', 3, FALSE),
		('Coleman', 'Roger', 'M', 3, FALSE),
		('Rosenbloom', 'Benny', 'M', 3, FALSE),
		('Jarrett', 'Louise', 'F', 3, FALSE),
		('Villiger', 'Parker', 'F', 3, FALSE),
		('Robertson', 'Daphne', 'F', 3, FALSE),
		('Hunt', 'Gwenyth', 'F', 3, FALSE),
		('Buckley', 'Noelle', 'F', 3, FALSE),
		('Rodgers', 'Chris', 'M', 4, FALSE),
		('Bristow', 'Chad', 'M', 4, FALSE),
		('Anderson', 'Jayden', 'M', 4, FALSE),
		('Adams', 'Aiden', 'M', 4, FALSE),
		('Smith', 'Daron', 'M', 4, FALSE),
		('Welsch', 'Priscilla', 'F', 4, FALSE),
		('Gunn', 'Gemma', 'F', 4, FALSE),
		('Vane', 'Samantha', 'F', 4, FALSE),
		('Harrington', 'Keira', 'F', 4, FALSE),
		('Palmer', 'Jessica', 'F', 4, FALSE),
		('Egerton', 'Percy', 'M', 5, FALSE),
		('Blackburn', 'Rufus', 'M', 5, FALSE),
		('Judd', 'William', 'M', 5, FALSE),
		('Barclay', 'Danny', 'M', 5, FALSE),
		('Casey', 'Ronald', 'M', 5, FALSE),
		('Knight', 'Adalind', 'F', 5, FALSE),
		('Wood', 'Esmeralda', 'F', 5, FALSE),
		('Stewart', 'Andie', 'F', 5, FALSE),
		('Wilson', 'Ciara', 'F', 5, FALSE),
		('Squire', 'Stella', 'F', 5, FALSE),
		('Bishop', 'Rufus', 'M', 6, FALSE),
		('Robertson', 'Martin', 'M', 6, FALSE),
		('Allington', 'George', 'M', 6, FALSE),
		('Larsen', 'Brad', 'M', 6, FALSE),
		('Powell', 'Anthony', 'M', 6, FALSE),
		('Oliver', 'Agnes', 'F', 6, FALSE),
		('Morris', 'Mandy', 'F', 6, FALSE),
		('Roth', 'Gloria', 'F', 6, FALSE),
		('Dillon', 'Jasmine', 'F', 6, FALSE),
		('Bentley', 'Amy', 'F', 6, FALSE),
		('Craig', 'Chuck', 'M', 7, FALSE),
		('Allcott', 'Tom', 'M', 7, FALSE),
		('Weatcroft', 'Gabriel', 'M', 7, FALSE),
		('Taylor', 'Domenic', 'M', 7, FALSE),
		('Crawley', 'Denis', 'M', 7, FALSE),
		('Osmond', 'Natalie', 'F', 7, FALSE),
		('Becker', 'Andie', 'F', 7, FALSE),
		('Wild', 'Cassandra', 'F', 7, FALSE),
		('Spencer', 'Morgan', 'F', 7, FALSE),
		('Uttridge', 'Francesca', 'F', 7, FALSE),
		('Reynolds', 'Ron', 'M', 8, FALSE),
		('Lindsay', 'Tom', 'M', 8, FALSE),
		('Jones', 'Roger', 'M', 8, FALSE),
		('Everett', 'Marvin', 'M', 8, FALSE),
		('Ellwood', 'Ethan', 'M', 8, FALSE),
		('Hall', 'Lara', 'F', 8, FALSE),
		('Funnell', 'Karen', 'F', 8, FALSE),
		('Shelton', 'Margaret', 'F', 8, FALSE),
		('Mills', 'Tara', 'F', 8, FALSE),
		('Bayliss', 'Melissa', 'F', 8, FALSE),
		('Quinnell', 'Chad', 'M', 9, FALSE),
		('Logan', 'Benny', 'M', 9, FALSE),
		('Chapman', 'Mike', 'M', 9, FALSE),
		('Watt', 'Tyler', 'M', 9, FALSE),
		('Horton', 'Manuel', 'M', 9, FALSE),
		('Eaton', 'Priscilla', 'F', 9, FALSE),
		('Carter', 'Freya', 'F', 9, FALSE),
		('Morrison', 'Andrea', 'F', 9, FALSE),
		('Potts', 'Martha', 'F', 9, FALSE),
		('Raven', 'Celia', 'F', 9, FALSE),
		('Lakey', 'Chad', 'M', 10, FALSE),
		('Holmes', 'Jack', 'M', 10, FALSE),
		('Ryan', 'Erick', 'M', 10, FALSE),
		('Avery', 'Denis', 'M', 10, FALSE),
		('Kennedy', 'Danny', 'M', 10, FALSE),
		('Furnell', 'Margot', 'F', 10, FALSE),
		('Harper', 'Isabel', 'F', 10, FALSE),
		('Cann', 'Peyton', 'F', 10, FALSE),
		('Grey', 'Liv', 'F', 10, FALSE),
		('Lewin', 'Amelia', 'F', 10, FALSE);

INSERT INTO _events (event_Name, event_Type, event_Gender)
	VALUES
		('100m Sprint', 'Track', 'M'),
		('110m Hurdles', 'Track', 'M'),
		('1600m Run', 'Track', 'M'),
		('200m Sprint', 'Track', 'M'),
		('High Jump', 'Field', 'M'),
		('Long Jump', 'Field', 'M'),
		('Shotput', 'Field', 'M'),
		('Pole Vault', 'Field', 'M'),
		('100m Sprint', 'Track', 'F'),
		('110m Hurdles', 'Track', 'F'),
		('1600m Run', 'Track', 'F'),
		('200m Sprint', 'Track', 'F'),
		('High Jump', 'Field', 'F'),
		('Long Jump', 'Field', 'F'),
		('Shotput', 'Field', 'F'),
		('Pole Vault', 'Field', 'F');

INSERT INTO event_Results (event_Id, comp_Id, school_Id, score, placing, points, event_Dq)
	VALUES
		(1, 3, 1, 9.58, 1, 10, FALSE),
		(1, 74, 8, 9.62, 2, 8, FALSE),
		(1, 14, 2, 9.77, 3, 6, FALSE),
		(1, 82, 9, 9.95, 4, 4, FALSE),
		(2, 22, 3, 12.8, 1, 10, FALSE),
		(2, 62, 7, 13.1, 2, 8, FALSE),
		(2, 31, 4, 13.4, 3, 6, FALSE),
		(2, 95, 10, 13.5, 4, 4, FALSE),
		(3, 83, 9, 241.03, 1, 10, FALSE),
		(3, 44, 5, 241.15, 2, 8, FALSE),
		(3, 51, 6, 241.37, 3, 6, FALSE),
		(3, 64, 7, 241.86, 4, 4, FALSE),
		(4, 21, 3, 19.19, 1, 10, FALSE),
		(4, 62, 7, 19.23, 2, 8, FALSE),
		(4, 73, 8, 19.34, 3, 6, FALSE),
		(4, 4, 1, 19.66, 4, 4, FALSE),
		(5, 33, 4, 2.45, 1, 10, FALSE),
		(5, 84, 9, 2.3, 2, 8, FALSE),
		(5, 92, 10, 2.27, 3, 6, FALSE),
		(5, 42, 5, 2.15, 4, 4, FALSE),
		(6, 15, 2, 8.95, 1, 10, FALSE),
		(6, 81, 9, 8.90, 2, 8, FALSE),
		(6, 1, 1, 8.5, 3, 6, FALSE),
		(6, 93, 10, 8.4, 4, 4, FALSE),
		(7, 13, 2, 23.37, 1, 10, FALSE),
		(7, 35, 4, 23.06, 2, 8, FALSE),
		(7, 55, 6, 22.89, 3, 6, FALSE),
		(7, 45, 5, 22.78, 4, 4, FALSE),
		(8, 71, 8, 6.18, 1, 10, FALSE),
		(8, 2, 1, 5.96, 2, 8, FALSE),
		(8, 91, 10, 5.95, 3, 6, FALSE),
		(8, 53, 6, 5.46, 4, 4, FALSE),
		(9, 17, 2, 10.49, 1, 10, FALSE),
		(9, 27, 3, 10.55, 2, 8, FALSE),
		(9, 68, 7, 10.63, 3, 6, FALSE),
		(9, 97, 10, 10.64, 4, 4, FALSE),
		(10, 38, 4, 13.2, 1, 10, FALSE),
		(10, 18, 2, 13.5, 2, 8, FALSE),
		(10, 57, 6, 13.8, 3, 6, FALSE),
		(10, 76, 8, 14.0, 4, 4, FALSE),
		(11, 87, 9, 252.33, 1, 10, FALSE),
		(11, 7, 1, 252.46, 2, 8, FALSE),
		(11, 29, 3, 253.01, 3, 6, FALSE),
		(11, 50, 5, 253.07, 4, 4, FALSE),
		(12, 60, 6, 21.34, 1, 10, FALSE),
		(12, 67, 7, 21.42, 2, 8, FALSE),
		(12, 96, 10, 21.65, 3, 6, FALSE),
		(12, 80, 8, 21.98, 4, 4, FALSE),
		(13, 40, 4, 2.09, 1, 10, FALSE),
		(13, 59, 6, 2.05, 2, 8, FALSE),
		(13, 78, 8, 1.98, 3, 6, FALSE),
		(13, 70, 7, 1.76, 4, 4, FALSE),
		(14, 66, 7, 7.52, 1, 10, FALSE),
		(14, 36, 4, 7.46, 2, 8, FALSE),
		(14, 56, 6, 7.27, 3, 6, FALSE),
		(14, 100, 10, 7.14, 4, 4, FALSE),
		(15, 69, 7, 22.63, 1, 10, FALSE),
		(15, 98, 10, 22.18, 2, 8, FALSE),
		(15, 26, 3, 21.96, 3, 6, FALSE),
		(15, 37, 4, 21.77, 4, 4, FALSE),
		(16, 19, 2, 5.06, 1, 10, FALSE),
		(16, 58, 6, 4.99, 2, 8, FALSE),
		(16, 90, 9, 4.86, 3, 6, FALSE),
		(16, 46, 5, 4.62, 4, 4, FALSE);

INSERT INTO meet_results (school_Id, school_Name, points)
	VALUES
		(1, 'Gateway Senior HS', 36),
		(2, 'Penn Hill Senior HS', 54),
		(3, 'Plum Senior HS', 40),
		(4, 'Kiski Area HS', 56),
		(5, 'Butler Area HS', 24),
		(6, 'Shaler Area HS', 54),
		(7, 'Greensburg Area HS', 58),
		(8, 'Central Catholic', 38),
		(9, 'North Hills Senior HS', 46),
		(10, 'Latrobe Senior HS', 42);