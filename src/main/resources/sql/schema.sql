DROP TABLE IF EXISTS user CASCADE;
CREATE TABLE user (
  id            bigint NOT NULL AUTO_INCREMENT,
  email         varchar(50) NOT NULL,
  nickname      varchar(30) NOT NULL,
  password      varchar(80) NOT NULL,
  status        varchar(30) NOT NULL,
  last_login_at datetime DEFAULT NULL,
  update_at 	datetime DEFAULT NULL,
  create_at     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (id),
  CONSTRAINT unq_member_email UNIQUE (email),
  CONSTRAINT unq_member_nickname UNIQUE (nickname)
);

DROP TABLE IF EXISTS category CASCADE;
CREATE TABLE category (
  id                int NOT NULL AUTO_INCREMENT,
  category_name		varchar(30) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS hobby CASCADE;
CREATE TABLE hobby (
  id                int NOT NULL AUTO_INCREMENT,
  hobby_name		varchar(30) NOT NULL,
  category_id		int NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_hobby_to_category FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS user_like_hobby CASCADE;
CREATE TABLE user_like_hobby (
  user_id           bigint NOT NULL,
  hobby_id			int NOT NULL,
  category_id		int NOT NULL,
  PRIMARY KEY (user_id, hobby_id),
  CONSTRAINT fk_like_hobby_to_user FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_like_hobby_to_hobby FOREIGN KEY (hobby_id) REFERENCES hobby (id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_like_hobby_to_category FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE RESTRICT ON UPDATE CASCADE
);