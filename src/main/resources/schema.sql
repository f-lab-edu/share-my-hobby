DROP TABLE IF EXISTS user CASCADE;
CREATE TABLE user (
  id            bigint NOT NULL AUTO_INCREMENT,
  email         varchar(50) NOT NULL,
  nickname      varchar(30) NOT NULL,
  password        varchar(80) NOT NULL,
  status        varchar(30) NOT NULL,
  last_login_at datetime DEFAULT NULL,
  update_at 	datetime DEFAULT NULL,
  create_at     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (id),
  CONSTRAINT unq_member_email UNIQUE (email),
  CONSTRAINT unq_member_nickname UNIQUE (nickname)
);

DROP TABLE IF EXISTS profile CASCADE;
CREATE TABLE profile (
  id                bigint NOT NULL AUTO_INCREMENT,
  user_id           bigint NOT NULL,
  profile_image_url varchar(255),
  status_message    varchar(100),
  PRIMARY KEY (id),
  CONSTRAINT unq_profile_user_id UNIQUE (user_id),
  CONSTRAINT fk_profile_to_user FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);