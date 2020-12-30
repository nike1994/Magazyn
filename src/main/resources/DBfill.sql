CREATE TABLE product (id INT NOT NULL AUTO_INCREMENT,
                    EAN VARCHAR(12),
                    name VARCHAR(255),
                    quantity INT,
                    PRIMARY KEY(id)
                     );
INSERT INTO product (EAN,name,quantity) VALUES ("123456789102", "klawiatura",10);
INSERT INTO product (EAN,name,quantity) VALUES ("123555789152", "myszka",5);
INSERT INTO product (EAN,name,quantity) VALUES ("987456789105", "monitor",20);
INSERT INTO product (EAN,name,quantity) VALUES ("456456345602", "procesor",12);
INSERT INTO product (EAN,name,quantity) VALUES ("343456789102", "ram",14);
INSERT INTO product (EAN,name,quantity) VALUES ("748593845692", "pendrive",40);

CREATE TABLE users (id INT NOT NULL AUTO_INCREMENT,
                      login VARCHAR(255),
                      pass VARCHAR(255),
                      PRIMARY KEY(id));

INSERT INTO users (login,pass) VALUES ("admin", "admin");