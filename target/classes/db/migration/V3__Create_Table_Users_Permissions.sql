CREATE TABLE IF NOT EXISTS users_permissions (
  user_entity_id int NOT NULL,
  permissions_id int NOT NULL,
  PRIMARY KEY (user_entity_id,permissions_id),
  KEY fk_user_permissions (permissions_id),
  CONSTRAINT fk_user_permission FOREIGN KEY (user_entity_id) REFERENCES users (id),
  CONSTRAINT fk_permission FOREIGN KEY (permissions_id) REFERENCES permission (id)
) ENGINE=InnoDB