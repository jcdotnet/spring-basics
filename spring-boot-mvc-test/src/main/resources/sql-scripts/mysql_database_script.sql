CREATE DATABASE  IF NOT EXISTS `gradebook`;
USE `gradebook`;

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `email_address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

INSERT INTO `student` VALUES 
	(1,'Harry','Potter','harrypotter@owls.com'),
	(2,'Hermione','Granger','hermione@owls.com'),
	(3,'Draco','Malfoy','dracomalfoy@owls.com'),
	(4,'Ron','Weasley','ronweasley@owls.com'),
	(5,'John','Doe','johndoe@muggles.com');


DROP TABLE IF EXISTS `math_grade`;
CREATE TABLE `math_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `grade` double(5, 2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

INSERT INTO `math_grade` VALUES 
	(1, 1, 80),
	(2, 1, 90),
	(3, 1, 72);


DROP TABLE IF EXISTS `science_grade`;
CREATE TABLE `science_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `grade` double(5, 2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

INSERT INTO `science_grade` VALUES 
	(1, 1 , 80),
	(2, 1, 90),
	(3, 1 , 72);


DROP TABLE IF EXISTS `history_grade`;
CREATE TABLE `history_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `grade` double(5, 2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

INSERT INTO `history_grade` VALUES 
	(1, 1 , 80),
	(2, 1, 90),
	(3, 1 , 72);

UNLOCK TABLES;