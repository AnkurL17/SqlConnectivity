CREATE TABLE `student` (
  `rollNo` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `marks` float NOT NULL,
  `age` int(11) NOT NULL,
  `gender` varchar(7) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`rollNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
