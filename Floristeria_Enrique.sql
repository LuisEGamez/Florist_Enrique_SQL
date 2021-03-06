-- MySQL Script generated by MySQL Workbench
-- Wed Apr  6 12:20:27 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Florist_Enrique_SQL
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Florist_Enrique_SQL
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Florist_Enrique_SQL` DEFAULT CHARACTER SET utf8 ;
USE `Florist_Enrique_SQL` ;

-- -----------------------------------------------------
-- Table `Florist_Enrique_SQL`.`decorations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Florist_Enrique_SQL`.`decorations` (
  `id_decoration` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `material` ENUM('wood', 'plastic') NOT NULL,
  `price` DOUBLE(6,2) NOT NULL,
  `quantity` INT(11) NOT NULL,
  PRIMARY KEY (`id_decoration`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Florist_Enrique_SQL`.`flowers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Florist_Enrique_SQL`.`flowers` (
  `id_flower` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `color` VARCHAR(10) NOT NULL,
  `price` DOUBLE(6,2) NOT NULL,
  `quantity` INT(11) NOT NULL,
  PRIMARY KEY (`id_flower`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Florist_Enrique_SQL`.`tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Florist_Enrique_SQL`.`tickets` (
  `id_ticket` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `Total` DOUBLE(6,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id_ticket`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Florist_Enrique_SQL`.`tickets_has_decorations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Florist_Enrique_SQL`.`tickets_has_decorations` (
  `tickets_id` INT(11) NOT NULL,
  `decorations_id` INT(11) NOT NULL,
  INDEX `fk_tickets_has_decorations_decorations1_idx` (`decorations_id` ASC) ,
  INDEX `fk_tickets_has_decorations_tickets1_idx` (`tickets_id` ASC) ,
  CONSTRAINT `fk_tickets_has_decorations_decorations1`
    FOREIGN KEY (`decorations_id`)
    REFERENCES `Florist_Enrique_SQL`.`decorations` (`id_decoration`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_has_decorations_tickets1`
    FOREIGN KEY (`tickets_id`)
    REFERENCES `Florist_Enrique_SQL`.`tickets` (`id_ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Florist_Enrique_SQL`.`tickets_has_flowers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Florist_Enrique_SQL`.`tickets_has_flowers` (
  `tickets_id` INT(11) NOT NULL,
  `flowers_id` INT(11) NOT NULL,
  INDEX `fk_tickets_has_flowers_flowers1_idx` (`flowers_id` ASC) ,
  INDEX `fk_tickets_has_flowers_tickets1_idx` (`tickets_id` ASC) ,
  CONSTRAINT `fk_tickets_has_flowers_flowers1`
    FOREIGN KEY (`flowers_id`)
    REFERENCES `Florist_Enrique_SQL`.`flowers` (`id_flower`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_has_flowers_tickets1`
    FOREIGN KEY (`tickets_id`)
    REFERENCES `Florist_Enrique_SQL`.`tickets` (`id_ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Florist_Enrique_SQL`.`trees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Florist_Enrique_SQL`.`trees` (
  `id_tree` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `height` DOUBLE(4,2) NOT NULL,
  `price` DOUBLE(6,2) NOT NULL,
  `quantity` INT(11) NOT NULL,
  PRIMARY KEY (`id_tree`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Florist_Enrique_SQL`.`tickets_has_trees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Florist_Enrique_SQL`.`tickets_has_trees` (
  `ticket_id` INT(11) NOT NULL,
  `tree_id` INT(11) NOT NULL,
  INDEX `fk_ticket_has_tree_tree1_idx` (`tree_id` ASC) ,
  INDEX `fk_ticket_has_tree_ticket1_idx` (`ticket_id` ASC) ,
  CONSTRAINT `fk_ticket_has_tree_ticket1`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `Florist_Enrique_SQL`.`tickets` (`id_ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_has_tree_tree1`
    FOREIGN KEY (`tree_id`)
    REFERENCES `Florist_Enrique_SQL`.`trees` (`id_tree`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
