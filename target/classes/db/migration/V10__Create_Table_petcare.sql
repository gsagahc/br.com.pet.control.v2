CREATE TABLE `pet_care` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fk_pet` bigint NOT NULL,
  `care_date` DATE NULL,
  `grooming` varchar(1) DEFAULT 'N' NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;