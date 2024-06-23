CREATE TABLE `pet_cad` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `gender` varchar(255) NOT NULL,
  `pet_breed` varchar(50) NOT NULL,
  `pet_kind` varchar(50) NOT NULL,
  `pet_name` varchar(80) NOT NULL,
  `owner_id` bigint NOT NULL,
   PRIMARY KEY (`id`),
   KEY fk_owner (owner_id),
   CONSTRAINT fk_owner FOREIGN KEY (owner_id) REFERENCES owner_pet (id)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;