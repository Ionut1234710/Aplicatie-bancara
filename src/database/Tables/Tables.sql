CREATE TABLE `jdbc`.`transaction` (
  `idtransaction` INT NOT NULL AUTO_INCREMENT,
  `currency` VARCHAR(45) NOT NULL,
  `transaction_hour` VARCHAR(45) NOT NULL,
  `transaction_day` VARCHAR(45) NOT NULL,
  `transaction_month` VARCHAR(45) NOT NULL,
  `transaction_year` VARCHAR(45) NOT NULL,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`idtransaction`),
  INDEX `account_id` (`account_id` ASC) VISIBLE,
  CONSTRAINT `account_id_fk`
    FOREIGN KEY (`account_id`)
    REFERENCES `jdbc`.`account` (`idaccount`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE `jdbc`.`card` (
  `idcard` INT NOT NULL AUTO_INCREMENT,
  `card_number` VARCHAR(45) NOT NULL,
  `expiration_month` VARCHAR(45) NOT NULL,
  `expiration_year` VARCHAR(45) NOT NULL,
  `customer_name` VARCHAR(45) NOT NULL,
  `pin` VARCHAR(45) NOT NULL,
  `account_id2` INT NOT NULL,
  PRIMARY KEY (`idcard`),
  UNIQUE INDEX `card_number_UNIQUE` (`card_number` ASC) VISIBLE,
  INDEX `account_id2` (`account_id2` ASC) VISIBLE,
  CONSTRAINT `account_id_fk2`
    FOREIGN KEY (`account_id2`)
    REFERENCES `jdbc`.`account` (`idaccount`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE `jdbc`.`contact` (
  `phone_number` VARCHAR(45) NOT NULL,
  `mail_address` VARCHAR(45) NOT NULL,
  `facebook_address` VARCHAR(45) NOT NULL,
  `bank_id` INT NOT NULL,
  PRIMARY KEY (`phone_number`),
  INDEX `bank_id` (`bank_id` ASC) VISIBLE,
  CONSTRAINT `bank_id_fk`
    FOREIGN KEY (`bank_id`)
    REFERENCES `jdbc`.`bank` (`idbank`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE `jdbc`.`customer` (
  `idcustomer` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `cnp` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `account_id3` INT NOT NULL,
  PRIMARY KEY (`idcustomer`),
  UNIQUE INDEX `cnp_UNIQUE` (`cnp` ASC) VISIBLE,
  INDEX `account_id3` (`account_id3` ASC) VISIBLE,
  CONSTRAINT `account_id_fk3`
    FOREIGN KEY (`account_id3`)
    REFERENCES `jdbc`.`account` (`idaccount`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE `jdbc`.`account` (
  `idaccount` INT NOT NULL,
  `iban` VARCHAR(45) NOT NULL,
  `bic` VARCHAR(45) NOT NULL,
  `balance` DOUBLE NOT NULL,
  `currency` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idaccount_ex`),
  UNIQUE INDEX `iban_UNIQUE` (`ibanex` ASC) VISIBLE);