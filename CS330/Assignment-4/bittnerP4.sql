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
	meet_Dq BOOLEAN DEFAULT FALSE,
	PRIMARY KEY (comp_Id),
	FOREIGN KEY (school_Id) REFERENCES schools(school_Id),
	UNIQUE KEY unique_athlete (last_Name, first_Name, school_Id));

CREATE TABLE _events (
	event_Id INT AUTO_INCREMENT,
	event_Name VARCHAR(20),
	event_Type ENUM('T','F'),
	event_Gender ENUM('M','F'),
	PRIMARY KEY (event_Id));

CREATE TABLE event_Results (
	event_Id INT NOT NULL,
	comp_Id INT NOT NULL,
	school_Id INT NOT NULL,
    gender ENUM('M','F'),
	score DECIMAL(8,2),
	placing INT,
	points INT,
	event_Dq BOOLEAN DEFAULT FALSE,
	FOREIGN KEY (event_Id) REFERENCES _events(event_Id),
	FOREIGN KEY (comp_Id) REFERENCES athletes(comp_Id),
	FOREIGN KEY (school_Id) REFERENCES schools(school_Id));

CREATE TABLE meet_Results (
	school_Id INT NOT NULL,
	school_Name VARCHAR(30),
    gender ENUM('M','F'),
	points INT);

DELIMITER //
CREATE TRIGGER updateMeetResults
AFTER UPDATE ON event_Results
FOR EACH ROW
BEGIN
	DECLARE oldPoints INT;
    SET oldPoints = (SELECT points FROM meet_Results WHERE (school_Id = NEW.school_Id and gender = NEW.gender));
    
    UPDATE meet_Results
    SET points = oldPoints + NEW.points
    WHERE (school_Id = NEW.school_Id and gender = NEW.gender);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE finalMeetResults()
BEGIN
	SELECT school_Name, sum(points)
	FROM meet_Results
	GROUP BY school_Name
	ORDER BY sum(points) DESC LIMIT 1;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE getEventResults(eventId int)
BEGIN
	SELECT 
		a.event_Id,
		b.event_Name,
		c.first_Name,
		c.last_Name,
		d.school_Name,
		a.score,
		a.placing,
		a.points,
		a.event_Dq
	FROM event_Results AS a
		LEFT OUTER JOIN _events AS b
			ON a.event_Id = b.event_Id
		LEFT OUTER JOIN athletes AS c
			ON a.comp_Id = c.comp_Id
		LEFT OUTER JOIN schools AS d
			ON a.school_Id = d.school_ID
	WHERE a.event_Id = eventId;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE scoreEvent(eventId int)
BEGIN 
	DECLARE rId INT; 
	DECLARE i INT;
	DECLARE p INT; 
	SET i = 0; 
	WHILE i <= 6 DO 
		SET rId = (SELECT placing FROM event_Results WHERE event_Id = eventId ORDER BY score 
			ASC LIMIT i,1); 
		IF i = 0 THEN SET p = 10; END IF; 
		IF i = 1 THEN SET p = 8; END IF; 
		IF i = 2 THEN SET p = 6; END IF; 
		IF i = 3 THEN SET p = 4; END IF; 
		IF i = 4 THEN SET p = 2; END IF; 
		IF i = 5 THEN SET p = 1; END IF; 
		UPDATE event_Results SET points = p WHERE (placing = rId AND event_ID = eventId); 
		SET i = i + 1; 
	END WHILE; 
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE athleteEventDq(eventId INT, compId INT)
BEGIN
	UPDATE event_Results
    SET score = 0,
		placing = 0,
        points = 0,
        event_Dq = TRUE
	WHERE ((event_Id = eventId) AND (comp_Id = compId));
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE fullAthleteDq(compId INT)
BEGIN
	DECLARE dqCount INT;
    SET dqCount = (SELECT count(comp_Id) FROM event_Results WHERE (event_Dq = TRUE) AND (comp_Id = compId));
    IF dqCount >= 4
		THEN
			UPDATE athletes
			SET meet_Dq = TRUE
			WHERE (comp_Id = compId);
	END IF;
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
		('100m Sprint', 'T', 'M'),
		('110m Hurdles', 'T', 'M'),
		('1600m Run', 'T', 'M'),
		('200m Sprint', 'T', 'M'),
		('High Jump', 'F', 'M'),
		('Long Jump', 'F', 'M'),
		('Shotput', 'F', 'M'),
		('Pole Vault', 'F', 'M'),
		('100m Sprint', 'T', 'F'),
		('110m Hurdles', 'T', 'F'),
		('1600m Run', 'T', 'F'),
		('200m Sprint', 'T', 'F'),
		('High Jump', 'F', 'F'),
		('Long Jump', 'F', 'F'),
		('Shotput', 'F', 'F'),
		('Pole Vault', 'F', 'F');

INSERT INTO event_Results (event_Id, comp_Id, school_Id, gender, score, placing, points, event_Dq)
	VALUES
		(1, 3, 1, 'M', 9.58, 1, 0, FALSE),
		(1, 74, 8, 'M', 9.62, 2, 0, FALSE),
		(1, 14, 2, 'M', 9.77, 3, 0, FALSE),
		(1, 82, 9, 'M', 9.95, 4, 0, FALSE),
		(2, 22, 3, 'M', 12.8, 1, 10, FALSE),
		(2, 62, 7, 'M', 13.1, 2, 8, FALSE),
		(2, 31, 4, 'M', 13.4, 3, 6, FALSE),
		(2, 95, 10, 'M', 13.5, 4, 4, FALSE),
		(3, 83, 9, 'M', 241.03, 1, 10, FALSE),
		(3, 44, 5, 'M', 241.15, 2, 8, FALSE),
		(3, 51, 6, 'M', 241.37, 3, 6, FALSE),
		(3, 64, 7, 'M', 241.86, 4, 4, FALSE),
		(4, 21, 3, 'M', 19.19, 1, 10, FALSE),
		(4, 62, 7, 'M', 19.23, 2, 8, FALSE),
		(4, 73, 8, 'M', 19.34, 3, 6, FALSE),
		(4, 4, 1, 'M', 19.66, 4, 4, FALSE),
		(5, 33, 4, 'M', 2.45, 1, 10, FALSE),
		(5, 84, 9, 'M', 2.3, 2, 8, FALSE),
		(5, 92, 10, 'M', 2.27, 3, 6, FALSE),
		(5, 42, 5, 'M', 2.15, 4, 4, FALSE),
		(6, 15, 2, 'M', 8.95, 1, 10, FALSE),
		(6, 81, 9, 'M', 8.90, 2, 8, FALSE),
		(6, 1, 1, 'M', 8.5, 3, 6, FALSE),
		(6, 93, 10, 'M', 8.4, 4, 4, FALSE),
		(7, 13, 2, 'M', 23.37, 1, 10, FALSE),
		(7, 35, 4, 'M', 23.06, 2, 8, FALSE),
		(7, 55, 6, 'M', 22.89, 3, 6, FALSE),
		(7, 45, 5, 'M', 22.78, 4, 4, FALSE),
		(8, 71, 8, 'M', 6.18, 1, 10, FALSE),
		(8, 2, 1, 'M', 5.96, 2, 8, FALSE),
		(8, 91, 10, 'M', 5.95, 3, 6, FALSE),
		(8, 53, 6, 'M', 5.46, 4, 4, FALSE),
		(9, 17, 2, 'F', 10.49, 1, 10, FALSE),
		(9, 27, 3, 'F', 10.55, 2, 8, FALSE),
		(9, 68, 7, 'F', 10.63, 3, 6, FALSE),
		(9, 97, 10, 'F', 10.64, 4, 4, FALSE),
		(10, 38, 4, 'F', 13.2, 1, 10, FALSE),
		(10, 18, 2, 'F', 13.5, 2, 8, FALSE),
		(10, 57, 6, 'F', 13.8, 3, 6, FALSE),
		(10, 76, 8, 'F', 14.0, 4, 4, FALSE),
		(11, 87, 9, 'F', 252.33, 1, 10, FALSE),
		(11, 7, 1, 'F', 252.46, 2, 8, FALSE),
		(11, 29, 3, 'F', 253.01, 3, 6, FALSE),
		(11, 50, 5, 'F', 253.07, 4, 4, FALSE),
		(12, 60, 6, 'F', 21.34, 1, 10, FALSE),
		(12, 67, 7, 'F', 21.42, 2, 8, FALSE),
		(12, 96, 10, 'F', 21.65, 3, 6, FALSE),
		(12, 80, 8, 'F', 21.98, 4, 4, FALSE),
		(13, 40, 4, 'F', 2.09, 1, 10, FALSE),
		(13, 59, 6, 'F', 2.05, 2, 8, FALSE),
		(13, 78, 8, 'F', 1.98, 3, 6, FALSE),
		(13, 70, 7, 'F', 1.76, 4, 4, FALSE),
		(14, 66, 7, 'F', 7.52, 1, 10, FALSE),
		(14, 36, 4, 'F', 7.46, 2, 8, FALSE),
		(14, 56, 6, 'F', 7.27, 3, 6, FALSE),
		(14, 100, 10, 'F', 7.14, 4, 4, FALSE),
		(15, 69, 7, 'F', 22.63, 1, 10, FALSE),
		(15, 98, 10, 'F', 22.18, 2, 8, FALSE),
		(15, 26, 3, 'F', 21.96, 3, 6, FALSE),
		(15, 37, 4, 'F', 21.77, 4, 4, FALSE),
		(16, 19, 2, 'F', 5.06, 1, 10, FALSE),
		(16, 58, 6, 'F', 4.99, 2, 8, FALSE),
		(16, 90, 9, 'F', 4.86, 3, 6, FALSE),
		(16, 46, 5, 'F', 4.62, 4, 4, FALSE);

INSERT INTO meet_results (school_Id, school_Name, gender, points)
	VALUES
		(1, 'Gateway Senior HS', 'M', 28),
		(2, 'Penn Hill Senior HS', 'M', 26),
		(3, 'Plum Senior HS', 'M', 20),
		(4, 'Kiski Area HS', 'M', 24),
		(5, 'Butler Area HS', 'M', 16),
		(6, 'Shaler Area HS', 'M', 16),
		(7, 'Greensburg Area HS', 'M', 20),
		(8, 'Central Catholic', 'M', 24),
		(9, 'North Hills Senior HS', 'M', 30),
		(10, 'Latrobe Senior HS', 'M', 20),
		(11, 'Gateway Senior HS', 'F', 8),
		(12, 'Penn Hill Senior HS', 'F', 28),
		(13, 'Plum Senior HS', 'F', 20),
		(14, 'Kiski Area HS', 'F', 32),
		(15, 'Butler Area HS', 'F', 8),
		(16, 'Shaler Area HS', 'F', 38),
		(17, 'Greensburg Area HS', 'F', 38),
		(18, 'Central Catholic', 'F', 14),
		(19, 'North Hills Senior HS', 'F', 16),
		(20, 'Latrobe Senior HS', 'F', 22);