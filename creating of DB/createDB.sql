use MonitoringOfPrices;

CREATE TABLE IF NOT EXISTS `MonitoringOfPrices`.`user` (
  `id` INT NOT NULL,
  `name` VARCHAR(25) NOT NULL,
  `surname` VARCHAR(25) NOT NULL,
  `email` VARCHAR(25) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MonitoringOfPrices`.`category` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MonitoringOfPrices`.`subcategory` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `id_category` INT NOT NULL,
  INDEX `category_id_idx` (`id_category` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `category_id`
    FOREIGN KEY (`id_category`)
    REFERENCES `MonitoringOfPrices`.`category` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MonitoringOfPrices`.`price` (
`id` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `start_date` DATE NOT NULL,
  `isActive` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `MonitoringOfPrices`.`product` (
  `id` INT NOT NULL,
  `name` VARCHAR(60) NOT NULL,
  `manufacture` VARCHAR(45) NOT NULL,
  `weight` DECIMAL(10,3) NOT NULL,
  `subcategory_id` INT NOT NULL,
  `price_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `subcategory_id_idx` (`subcategory_id` ASC) VISIBLE,
  INDEX `fk_product_price1_idx` (`price_id` ASC) VISIBLE,
  CONSTRAINT `subcategory_id`
    FOREIGN KEY (`subcategory_id`)
    REFERENCES `MonitoringOfPrices`.`subcategory` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `price_id`
    FOREIGN KEY (`price_id`)
    REFERENCES `MonitoringOfPrices`.`price` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MonitoringOfPrices`.`store` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MonitoringOfPrices`.`credentials` (
  `login` VARCHAR(60) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `role` VARCHAR(5) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`login`),
  INDEX `fk_credentials_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `id_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `MonitoringOfPrices`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MonitoringOfPrices`.`product_store` (
  `store_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  INDEX `product_id_idx` (`product_id` ASC) VISIBLE,
  PRIMARY KEY (`store_id`, `product_id`),
  CONSTRAINT `store_id`
    FOREIGN KEY (`store_id`)
    REFERENCES `MonitoringOfPrices`.`store` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `MonitoringOfPrices`.`product` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MonitoringOfPrices`.`old_prices` (
	`price_id` INT NOT NULL,
	`product_id` INT NOT NULL,
	PRIMARY KEY (`price_id`, `product_id`))
ENGINE = InnoDB;

show tables from MonitoringOfPrices;

