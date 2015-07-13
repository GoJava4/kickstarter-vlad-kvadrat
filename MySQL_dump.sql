###################################
# CREATE TABLES
###################################

CREATE TABLE IF NOT EXISTS categories
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(255)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS projects
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  short_description TEXT,
  current_money INTEGER,
  need_money INTEGER,
  adding_date DATETIME,
  ending_date DATETIME,
  category_id INTEGER,
  user_id INTEGER,
  full_description_id INTEGER,
  successfully_finished BOOLEAN,

  FOREIGN KEY (category_id) REFERENCES categories(id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (full_description_id) REFERENCES full_descriptions(id)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS quotes
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  value LONGTEXT,
  author VARCHAR(50)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS users (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(25) UNIQUE,
  password VARCHAR(100),
  role_id INTEGER,
  active BOOLEAN,
  username VARCHAR(255),
  email VARCHAR(255),
  personal_info TEXT,
  FOREIGN KEY (role_id) REFERENCES user_roles(id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS user_roles (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS pictures (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  image BLOB NOT NULL
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS full_descriptions (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  full_description LONGTEXT
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS payment_bonuses (
  id           INTEGER AUTO_INCREMENT PRIMARY KEY,
  project_id   INTEGER NOT NULL,
  min_money    INTEGER NOT NULL,
  bonus_full_description_id INTEGER NOT NULL,
  bonuses_left INTEGER NOT NULL,
  FOREIGN KEY (bonus_full_description_id) REFERENCES full_descriptions (id),
  FOREIGN KEY (project_id) REFERENCES projects(id)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS payment_statuses (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  status VARCHAR(255)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS payments (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  amount DOUBLE NOT NULL,
  date DATETIME,
  project_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  status_id INTEGER NOT NULL,
  FOREIGN KEY (project_id) REFERENCES projects(id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (status_id) REFERENCES payment_statuses(id)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS comments (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  project_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  comment TEXT,
  date DATETIME,
  FOREIGN KEY (project_id) REFERENCES projects(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS questions (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  project_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  question TEXT NOT NULL,
  date DATETIME,
  FOREIGN KEY (project_id) REFERENCES projects(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS answers (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  user_id INTEGER NOT NULL,
  question_id INTEGER NOT NULL,
  answer TEXT NOT NULL,
  date DATETIME,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (question_id) REFERENCES questions(id)
)ENGINE = InnoDB;

#################################
# INSERT DATA
#################################

#--------------------------------
#CATEGORIES
#--------------------------------
INSERT INTO categories(name) VALUES ('Category 1'), ('Category 2'), ('Category 3');

#--------------------------------
#PROJECTS
#--------------------------------
INSERT INTO projects
(name, short_description, current_money, need_money, days_left, history, url_video, category_id)
VALUES
  ('name 1', 'short deskr 1', 1000, 5000, 30, 'history 1', 'video url 1', 1),
  ('name 2', 'short deskr 2', 1000, 5000, 30, 'history 2', 'video url 2', 1),
  ('name 3', 'short deskr 3', 1000, 5000, 30, 'history 3', 'video url 3', 2),
  ('name 4', 'short deskr 4', 1000, 5000, 30, 'history 4', 'video url 4', 2),
  ('name 5', 'short deskr 5', 1000, 5000, 30, 'history 5', 'video url 5', 3);

#--------------------------------
#QUOTES
#--------------------------------
INSERT INTO quotes(value, author)
VALUES
  ('Quote 1', 'Author 1'),
  ('Quote 2', 'Author 2'),
  ('Quote 3', 'Author 3');