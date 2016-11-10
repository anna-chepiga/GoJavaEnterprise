CREATE DATABASE restaurant;

USE restaurant;

CREATE TABLE employee (
  id           INT         NOT NULL AUTO_INCREMENT,
  first_name   VARCHAR(20) NOT NULL,
  last_name    VARCHAR(20) NOT NULL,
  birthday     DATE        NOT NULL,
  phone_number VARCHAR(10),
  position     INT         NOT NULL,
  salary       INT         NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO employee (first_name, last_name, birthday, phone_number, position, salary)
VALUES ('Anna', 'Chepiga', '1990-12-25', '0991111111', 1, 50000);
INSERT INTO employee (first_name, last_name, birthday, phone_number, position, salary)
VALUES ('Alex', 'Hello', '1989-06-13', '0992222222', 2, 40000);
INSERT INTO employee (first_name, last_name, birthday, phone_number, position, salary)
VALUES ('Chris', 'Hemsworth', '1988-04-02', '0993333333', 3, 30000);
INSERT INTO employee (first_name, last_name, birthday, phone_number, position, salary)
VALUES ('Tom', 'Hiddleston', '1992-10-14', '0994444444', 4, 20000);
INSERT INTO employee (first_name, last_name, birthday, phone_number, position, salary)
VALUES ('Eva', 'Green', '1990-04-27', '0995555555', 4, 20500);
INSERT INTO employee (first_name, last_name, birthday, phone_number, position, salary)
VALUES ('Johhny', 'Depp', '1991-05-18', '0996666666', 5, 10000);
INSERT INTO employee (first_name, last_name, birthday, phone_number, position, salary)
VALUES ('Matt', 'Bellamy', '1992-10-01', '0997777777', 5, 12000);
INSERT INTO employee (first_name, last_name, birthday, phone_number, position, salary)
VALUES ('Anna', 'Potter', '1993-04-22', '0998888888', 5, 11000);
INSERT INTO employee (first_name, last_name, birthday, phone_number, position, salary)
VALUES ('Harry', 'Potter', '1991-07-31', '0999999999', 5, 11500);

CREATE TABLE restaurant.position (
  id   INT NOT NULL,
  name VARCHAR(20),
  PRIMARY KEY (id)
);

INSERT INTO restaurant.position VALUES (1, 'SEO');
INSERT INTO restaurant.position VALUES (2, 'Art Director');
INSERT INTO restaurant.position VALUES (3, 'Manager');
INSERT INTO restaurant.position VALUES (4, 'Cooker');
INSERT INTO restaurant.position VALUES (5, 'Waiter');

CREATE TABLE restaurant.created_order (
  id           INT  NOT NULL AUTO_INCREMENT,
  waiter_id    INT  NOT NULL,
  table_number INT,
  date         DATE NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO restaurant.created_order (waiter_id, table_number, date) VALUES (6, 12, '2016-10-20');
INSERT INTO restaurant.created_order (waiter_id, table_number, date) VALUES (8, 5, '2016-10-20');
INSERT INTO restaurant.created_order (waiter_id, table_number, date) VALUES (8, 2, '2016-10-21');
INSERT INTO restaurant.created_order (waiter_id, table_number, date) VALUES (7, 4, '2016-10-21');
INSERT INTO restaurant.created_order (waiter_id, table_number, date) VALUES (9, 8, '2016-10-21');
INSERT INTO restaurant.created_order (waiter_id, table_number, date) VALUES (6, 3, '2016-10-21');

CREATE TABLE dishes_category (
  id   INT NOT NULL,
  name VARCHAR(20),
  PRIMARY KEY (id)
);

INSERT INTO dishes_category VALUES (1, 'First course');
INSERT INTO dishes_category VALUES (2, 'Main course');
INSERT INTO dishes_category VALUES (3, 'Beverage');
INSERT INTO dishes_category VALUES (4, 'Salad');
INSERT INTO dishes_category VALUES (5, 'Meat');

CREATE TABLE dish (
  id       INT         NOT NULL AUTO_INCREMENT,
  name     VARCHAR(20) NOT NULL,
  category INT         NOT NULL,
  price    DOUBLE      NOT NULL,
  weight   INT         NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO dish (name, category, price, weight) VALUES ('Kharcho', 1, 10, 200);
INSERT INTO dish (name, category, price, weight) VALUES ('Borshch', 1, 9.5, 250);
INSERT INTO dish (name, category, price, weight) VALUES ('Pilaff', 2, 8.5, 300);
INSERT INTO dish (name, category, price, weight) VALUES ('Pasta', 2, 9.2, 250);
INSERT INTO dish (name, category, price, weight) VALUES ('Tea', 3, 3, 300);
INSERT INTO dish (name, category, price, weight) VALUES ('Coffee', 3, 3.5, 250);
INSERT INTO dish (name, category, price, weight) VALUES ('Juice', 3, 4, 300);
INSERT INTO dish (name, category, price, weight) VALUES ('Greek salad', 4, 5, 300);
INSERT INTO dish (name, category, price, weight) VALUES ('Cesar', 4, 6.5, 300);
INSERT INTO dish (name, category, price, weight) VALUES ('Steak', 5, 7, 150);
INSERT INTO dish (name, category, price, weight) VALUES ('Hamburger', 5, 6.5, 250);

CREATE TABLE order_dishes_list (
  created_order_id INT,
  dish_id          INT
);

INSERT INTO order_dishes_list VALUES (1, 4);
INSERT INTO order_dishes_list VALUES (1, 5);
INSERT INTO order_dishes_list VALUES (1, 8);
INSERT INTO order_dishes_list VALUES (2, 1);
INSERT INTO order_dishes_list VALUES (2, 2);
INSERT INTO order_dishes_list VALUES (2, 7);

CREATE TABLE cooked_dishes (
  id        INT  NOT NULL AUTO_INCREMENT,
  dish_id   INT  NOT NULL,
  cooker_id INT  NOT NULL,
  order_id  INT  NOT NULL,
  date      DATE NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO cooked_dishes (dish_id, cooker_id, order_id, date) VALUES (1, 4, 1, '2016-10-20');
INSERT INTO cooked_dishes (dish_id, cooker_id, order_id, date) VALUES (5, 4, 1, '2016-10-20');
INSERT INTO cooked_dishes (dish_id, cooker_id, order_id, date) VALUES (9, 4, 1, '2016-10-20');
INSERT INTO cooked_dishes (dish_id, cooker_id, order_id, date) VALUES (3, 5, 2, '2016-10-21');
INSERT INTO cooked_dishes (dish_id, cooker_id, order_id, date) VALUES (7, 5, 2, '2016-10-21');
INSERT INTO cooked_dishes (dish_id, cooker_id, order_id, date) VALUES (10, 5, 2, '2016-10-21');

CREATE TABLE menu (
  id   INT NOT NULL,
  name VARCHAR(20),
  PRIMARY KEY (id)
);

INSERT INTO menu VALUES (1, 'Breakfast');
INSERT INTO menu VALUES (2, 'Lunch');
INSERT INTO menu VALUES (3, 'Dinner');

CREATE TABLE menu_dishes_list (
  menu_id INT,
  dish_id INT
);

INSERT INTO menu_dishes_list VALUES (1, 4);
INSERT INTO menu_dishes_list VALUES (1, 5);
INSERT INTO menu_dishes_list VALUES (1, 6);
INSERT INTO menu_dishes_list VALUES (1, 8);
INSERT INTO menu_dishes_list VALUES (2, 1);
INSERT INTO menu_dishes_list VALUES (2, 2);
INSERT INTO menu_dishes_list VALUES (2, 3);
INSERT INTO menu_dishes_list VALUES (2, 5);
INSERT INTO menu_dishes_list VALUES (2, 7);
INSERT INTO menu_dishes_list VALUES (3, 5);
INSERT INTO menu_dishes_list VALUES (3, 7);
INSERT INTO menu_dishes_list VALUES (3, 8);
INSERT INTO menu_dishes_list VALUES (3, 10);
INSERT INTO menu_dishes_list VALUES (3, 11);

CREATE TABLE ingredient (
  ID   INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20),
  PRIMARY KEY (id)
);

INSERT INTO ingredient (name) VALUE ('Potato');
INSERT INTO ingredient (name) VALUE ('Beet');
INSERT INTO ingredient (name) VALUE ('Cabbage');
INSERT INTO ingredient (name) VALUE ('Onion');
INSERT INTO ingredient (name) VALUE ('Rice');
INSERT INTO ingredient (name) VALUE ('Pasta');
INSERT INTO ingredient (name) VALUE ('Sauce');
INSERT INTO ingredient (name) VALUE ('Tea');
INSERT INTO ingredient (name) VALUE ('Coffee');
INSERT INTO ingredient (name) VALUE ('Apple juice');
INSERT INTO ingredient (name) VALUE ('Orange juice');
INSERT INTO ingredient (name) VALUE ('Sweet pepper');
INSERT INTO ingredient (name) VALUE ('Cheese');
INSERT INTO ingredient (name) VALUE ('Cucumber');
INSERT INTO ingredient (name) VALUE ('Sun oil');
INSERT INTO ingredient (name) VALUE ('Pork');
INSERT INTO ingredient (name) VALUE ('Beef');

CREATE TABLE dish_ingredients (
  dish_id       INT,
  ingredient_id INT
);

INSERT INTO dish_ingredients VALUES (1, 1);
INSERT INTO dish_ingredients VALUES (1, 3);
INSERT INTO dish_ingredients VALUES (1, 4);
INSERT INTO dish_ingredients VALUES (1, 12);
INSERT INTO dish_ingredients VALUES (1, 15);
INSERT INTO dish_ingredients VALUES (2, 1);
INSERT INTO dish_ingredients VALUES (2, 2);
INSERT INTO dish_ingredients VALUES (2, 3);
INSERT INTO dish_ingredients VALUES (2, 4);
INSERT INTO dish_ingredients VALUES (2, 15);

CREATE TABLE storage (
  ingredient_id INT NOT NULL,
  amount        INT NOT NULL,
  PRIMARY KEY (ingredient_id, amount)
);

INSERT INTO restaurant.storage VALUES (1, 100);
INSERT INTO restaurant.storage VALUES (2, 90);
INSERT INTO restaurant.storage VALUES (3, 95);
INSERT INTO restaurant.storage VALUES (4, 102);
INSERT INTO restaurant.storage VALUES (5, 50);
INSERT INTO restaurant.storage VALUES (6, 60);
INSERT INTO restaurant.storage VALUES (7, 30);
INSERT INTO restaurant.storage VALUES (8, 30);
INSERT INTO restaurant.storage VALUES (9, 40);
INSERT INTO restaurant.storage VALUES (10, 55);
INSERT INTO restaurant.storage VALUES (11, 75);
INSERT INTO restaurant.storage VALUES (12, 35);
INSERT INTO restaurant.storage VALUES (13, 28);
INSERT INTO restaurant.storage VALUES (14, 62);
INSERT INTO restaurant.storage VALUES (15, 15);
INSERT INTO restaurant.storage VALUES (16, 105);
INSERT INTO restaurant.storage VALUES (17, 88);

ALTER TABLE cooked_dishes
  ADD CONSTRAINT FOREIGN KEY (dish_id)
REFERENCES dish (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE,
  ADD CONSTRAINT FOREIGN KEY (cooker_id)
REFERENCES employee (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE,
  ADD CONSTRAINT FOREIGN KEY (order_id)
REFERENCES restaurant.created_order (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;

ALTER TABLE dish
  ADD CONSTRAINT FOREIGN KEY (category)
REFERENCES dishes_category (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;

ALTER TABLE employee
  ADD CONSTRAINT FOREIGN KEY (position)
REFERENCES restaurant.position (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;

ALTER TABLE restaurant.created_order
  ADD CONSTRAINT FOREIGN KEY (waiter_id)
REFERENCES employee (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;

ALTER TABLE storage
  ADD CONSTRAINT FOREIGN KEY (ingredient_id)
REFERENCES ingredient (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;

ALTER TABLE dish_ingredients
  ADD CONSTRAINT FOREIGN KEY (dish_id)
REFERENCES dish (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE,
  ADD CONSTRAINT FOREIGN KEY (ingredient_id)
REFERENCES ingredient (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;

ALTER TABLE menu_dishes_list
  ADD CONSTRAINT FOREIGN KEY (dish_id)
REFERENCES dish (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE,
  ADD CONSTRAINT FOREIGN KEY (menu_id)
REFERENCES menu (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;

ALTER TABLE order_dishes_list
  ADD CONSTRAINT FOREIGN KEY (created_order_id)
REFERENCES created_order (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE,
  ADD CONSTRAINT FOREIGN KEY (dish_id)
REFERENCES dish (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;