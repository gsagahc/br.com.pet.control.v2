CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `owner_id` bigint NOT NULL,
  `address` varchar(100) NOT NULL,
  `city` varchar(60) NOT NULL,
  `uf` varchar(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY fk_owner_address (owner_id),
  CONSTRAINT fk_owner_address FOREIGN KEY (owner_id) REFERENCES owner_pet (id)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;