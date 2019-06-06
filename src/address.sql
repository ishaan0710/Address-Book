CREATE DATABASE IF NOT EXISTS AddressBook;
USE AddressBook;

DROP TABLE IF EXISTS PersonalInformation;
CREATE TABLE PersonalInformation(
    id int(3) PRIMARY KEY AUTO_INCREMENT,
    name varchar(10) NOT NULL,
    mobile varchar(10),
    address varchar(30)
) ENGINE = InnoDB;

