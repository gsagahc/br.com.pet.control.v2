CREATE TABLE `pet_cad` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `gender` varchar(255) NOT NULL,
  `pet_breed` varchar(50) NOT NULL,
  `pet_kind` varchar(50) NOT NULL,
  `pet_name` varchar(80) NOT NULL,
  `fk_cpf` varchar(12) NOT NULL,
   PRIMARY KEY (id),
   KEY fk_owner (fk_cpf),
   CONSTRAINT fk_owner FOREIGN KEY (fk_cpf) REFERENCES owner_pet (cpf)
 ) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;