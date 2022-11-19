CREATE TABLE foods (
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(200) NOT NULL,
   created_on DATE NOT NULL,
   updated_on DATE NOT NULL,
   serve_date DATE NOT NULL,
   meal VARCHAR(10) NOT NULL,
   approval BOOLEAN NOT NULL DEFAULT 0,
   PRIMARY KEY (id),
);

CREATE TABLE users (
   id INT NOT NULL AUTO_INCREMENT,
   first_name VARCHAR(200) NOT NULL,
   last_name VARCHAR(200) NOT NULL,
   email VARCHAR(20) NOT NULL,
   username VARCHAR(200) NOT NULL,
   password VARCHAR(130) NOT NULL,
   created_on DATE NOT NULL,
   updated_on DATE NOT NULL,
   is_blocked BOOLEAN NOT NULL DEFAULT 0,
   role VARCHAR(20) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (username, email)
);

CREATE TABLE reviews (
   id INT NOT NULL AUTO_INCREMENT,
   description VARCHAR(500) NOT NULL,
   created_on DATE NOT NULL,
   updated_on DATE NOT NULL,
   food_id INT NOT NULL,
   user_id INT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (food_id) REFERENCES foods (id),
   FOREIGN KEY (user_id) REFERENCES users (id)
);
