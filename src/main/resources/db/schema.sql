CREATE DATABASE IF NOT EXISTS `app`;
USE `app`;

CREATE TABLE `user` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(40) NOT NULL,
	`password` VARCHAR(72) NOT NULL,
	`active` BIT NOT NULL,
	`created` DATETIME NOT NULL,
	`updated` DATETIME NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `U_username` (`username`)
) ENGINE=InnoDB;

CREATE TABLE `role` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `U_name` (`name`)
) ENGINE=InnoDB;

CREATE TABLE `user_role` (
    `user_id` INT(11) NOT NULL,
    `role_id` INT(11) NOT NULL,
    INDEX `FK_user_role_user` (`user_id`),
    INDEX `FK_user_role_role` (`role_id`),
    CONSTRAINT `FK_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
    CONSTRAINT `FK_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB;
