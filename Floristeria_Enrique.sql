-- MySQL Script generated by MySQL Workbench
-- Wed Mar 30 16:01:25 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Florist_Enrique_SQL
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Florist_Enrique_SQL
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Florist_Enrique_SQL` DEFAULT CHARACTER SET utf8 ;
USE `Florist_Enrique_SQL` ;

-- -----------------------------------------------------
-- Table `Florist_Enrique_SQL`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Florist_Enrique_SQL`.`ticket` (
  `id_ticket` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `price` DECIMAL(6,2) NOT NULL,
  `height` DECIMAL(4,2) NOT NULL,
  PRIMARY KEY (`id_ticket`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Florist_Enrique_SQL`.`tree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Florist_Enrique_SQL`.`tree` (
  `id_tree` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `height` DECIMAL(4,2) NOT NULL,
  `ticket_id_ticket` INT NOT NULL,
  PRIMARY KEY (`id_tree`),
  INDEX `fk_tree_ticket_idx` (`ticket_id_ticket` ASC) ,
  CONSTRAINT `fk_tree_ticket`
    FOREIGN KEY (`ticket_id_ticket`)
    REFERENCES `Florist_Enrique_SQL`.`ticket` (`id_ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Florist_Enrique_SQL`.`flower`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Florist_Enrique_SQL`.`flower` (
  `id_flower` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `color` VARCHAR(10) NOT NULL,
  `ticket_id_ticket` INT NOT NULL,
  PRIMARY KEY (`id_flower`),
  INDEX `fk_flower_ticket1_idx` (`ticket_id_ticket` ASC) ,
  CONSTRAINT `fk_flower_ticket1`
    FOREIGN KEY (`ticket_id_ticket`)
    REFERENCES `Florist_Enrique_SQL`.`ticket` (`id_ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Florist_Enrique_SQL`.`decoration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Florist_Enrique_SQL`.`decoration` (
  `id_decoration` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `material` ENUM('wood', 'plastic') NOT NULL,
  `ticket_id_ticket` INT NOT NULL,
  PRIMARY KEY (`id_decoration`),
  INDEX `fk_decoration_ticket1_idx` (`ticket_id_ticket` ASC) ,
  CONSTRAINT `fk_decoration_ticket1`
    FOREIGN KEY (`ticket_id_ticket`)
    REFERENCES `Florist_Enrique_SQL`.`ticket` (`id_ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;