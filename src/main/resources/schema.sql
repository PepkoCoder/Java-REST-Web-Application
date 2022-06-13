CREATE TABLE hardware (
    code VARCHAR(8) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL ,
    price DECIMAL NOT NULL ,
    type VARCHAR(50) NOT NULL ,
    amountInStorage INT NOT NULL
);

CREATE TABLE review (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    text TEXT NOT NULL,
    rating INT NOT NULL,
    hardware_code VARCHAR(8) NOT NULL,
    FOREIGN KEY(hardware_code) REFERENCES hardware(code) ON DELETE CASCADE
);

CREATE TABLE user (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(1000) NOT NULL
);

CREATE TABLE authority (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    authority_name VARCHAR(100) NOT NULL
);

CREATE TABLE user_authority (
    user_id INT NOT NULL,
    authority_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (authority_id) REFERENCES authority(id)
);