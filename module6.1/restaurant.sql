create database restaurant;

use restaurant;

create table employee (
    id int not null auto_increment,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    birthday date not null,
    phone_number varchar(10),
    position int not null,
    salary int not null,
    primary key (id)
);

insert into employee (first_name, last_name, birthday, phone_number, position, salary) values('Anna', 'Chepiga', '1990-12-25', '0991111111', 1, 50000);
insert into employee (first_name, last_name, birthday, phone_number, position, salary) values('Alex', 'Hello', '1989-06-13', '0992222222', 2, 40000);
insert into employee (first_name, last_name, birthday, phone_number, position, salary) values('Chris', 'Hemsworth', '1988-04-02', '0993333333', 3, 30000);
insert into employee (first_name, last_name, birthday, phone_number, position, salary) values('Tom', 'Hiddleston', '1992-10-14', '0994444444', 4, 20000);
insert into employee (first_name, last_name, birthday, phone_number, position, salary) values('Eva', 'Green', '1990-04-27', '0995555555', 4, 20500);
insert into employee (first_name, last_name, birthday, phone_number, position, salary) values('Johhny', 'Depp', '1991-05-18', '0996666666', 5, 10000);
insert into employee (first_name, last_name, birthday, phone_number, position, salary) values('Matt', 'Bellamy', '1992-10-01', '0997777777', 5, 12000);
insert into employee (first_name, last_name, birthday, phone_number, position, salary) values('Anna', 'Potter', '1993-04-22', '0998888888', 5, 11000);
insert into employee (first_name, last_name, birthday, phone_number, position, salary) values('Harry', 'Potter', '1991-07-31', '0999999999', 5, 11500);

create table restaurant.position(
id int not null,
name varchar(20),
primary key (id)
);

insert into restaurant.position values(1, 'SEO');
insert into restaurant.position values(2, 'Art Director');
insert into restaurant.position values(3, 'Manager');
insert into restaurant.position values(4, 'Cooker');
insert into restaurant.position values(5, 'Waiter');

create table restaurant.order(
id int not null auto_increment,
waiter_id int not null,
dishes_list int not null,
table_number int,
date date not null,
primary key (id)
);

insert into restaurant.order (waiter_id, dishes_list, table_number, date) values(6, 1, 12, '2016-10-20');
insert into restaurant.order (waiter_id, dishes_list, table_number, date) values(8, 3, 5, '2016-10-20');
insert into restaurant.order (waiter_id, dishes_list, table_number, date) values(8, 4, 2, '2016-10-21');
insert into restaurant.order (waiter_id, dishes_list, table_number, date) values(7, 2, 4, '2016-10-21');
insert into restaurant.order (waiter_id, dishes_list, table_number, date) values(9, 5, 8, '2016-10-21');
insert into restaurant.order (waiter_id, dishes_list, table_number, date) values(6, 6, 3, '2016-10-21');

create table dish(
id int not null auto_increment,
name varchar(20) not null,
category int not null,
ingredients_list_id int not null,
price double not null,
weight int not null,
primary key (id)
);

insert into dish (name, category, ingredients_list_id, price, weight) values('Kharcho', 1, 1, 10, 200);
insert into dish (name, category, ingredients_list_id, price, weight) values('Borshch', 1, 2, 9.5, 250);
insert into dish (name, category, ingredients_list_id, price, weight) values('Pilaff', 2, 3, 8.5, 300);
insert into dish (name, category, ingredients_list_id, price, weight) values('Pasta', 2, 4, 9.2, 250);
insert into dish (name, category, ingredients_list_id, price, weight) values('Tea', 3, 5, 3, 300);
insert into dish (name, category, ingredients_list_id, price, weight) values('Coffee', 3, 6, 3.5, 250);
insert into dish (name, category, ingredients_list_id, price, weight) values('Juice', 3, 7, 4, 300);
insert into dish (name, category, ingredients_list_id, price, weight) values('Greek salad', 4, 8, 5, 300);
insert into dish (name, category, ingredients_list_id, price, weight) values('Cesar', 4, 9, 6.5, 300);
insert into dish (name, category, ingredients_list_id, price, weight) values('Steak', 5, 10, 7, 150);
insert into dish (name, category, ingredients_list_id, price, weight) values('Hamburger', 5, 11, 6.5, 250);

create table dishes_category(
id int not null,
name varchar(20),
primary key (id)
);

insert into dishes_category values(1, 'First course');
insert into dishes_category values(2, 'Main course');
insert into dishes_category values(3, 'Beverage');
insert into dishes_category values(4, 'Salad');
insert into dishes_category values(5, 'Meat');

create table cooked_dishes(
id int not null auto_increment,
dish_id int not null,
cooker_id int not null,
order_id int not null,
date date not null,
primary key (id)
);

insert into cooked_dishes (dish_id, cooker_id, order_id, date) values(1, 4, 1, '2016-10-20');
insert into cooked_dishes (dish_id, cooker_id, order_id, date) values(5, 4, 1, '2016-10-20');
insert into cooked_dishes (dish_id, cooker_id, order_id, date) values(9, 4, 1, '2016-10-20');
insert into cooked_dishes (dish_id, cooker_id, order_id, date) values(3, 5, 2, '2016-10-21');
insert into cooked_dishes (dish_id, cooker_id, order_id, date) values(7, 5, 2, '2016-10-21');
insert into cooked_dishes (dish_id, cooker_id, order_id, date) values(10, 5, 2, '2016-10-21');

create table menu(
id int not null,
name varchar(20),
dishes_list_id int not null,
primary key (id)
);

insert into menu values(1, 'Breakfast', 7);
insert into menu values(2, 'Lunch', 8);
insert into menu values(3, 'Dinner', 9);

create table dishes_list(
id int not null auto_increment,
dish_id_1 int,
dish_id_2 int,
dish_id_3 int,
dish_id_4 int,
dish_id_5 int,
dish_id_6 int,
dish_id_7 int,
dish_id_8 int,
dish_id_9 int,
dish_id_10 int,
primary key (id)
);

insert into dishes_list (dish_id_1, dish_id_2, dish_id_3) values (1, 5, 9);
insert into dishes_list (dish_id_1, dish_id_2, dish_id_3) values (3, 7, 10);
insert into dishes_list (dish_id_1, dish_id_2, dish_id_3, dish_id_4, dish_id_5) values (2, 6, 7, 9, 11);
insert into dishes_list (dish_id_1, dish_id_2) values (2, 8);
insert into dishes_list (dish_id_1, dish_id_2, dish_id_3, dish_id_4, dish_id_5, dish_id_6) values (1, 3, 4, 7, 5, 9);
insert into dishes_list (dish_id_1, dish_id_2, dish_id_3, dish_id_4) values (1, 4, 6, 10);
insert into dishes_list (dish_id_1, dish_id_2, dish_id_3, dish_id_4, dish_id_5, dish_id_6) values (3, 4, 5, 6, 7, 9);
insert into dishes_list (dish_id_1, dish_id_2, dish_id_3, dish_id_4, dish_id_5, dish_id_6, dish_id_7, dish_id_8, dish_id_9, dish_id_10) values (1, 2, 3, 4, 5, 7, 8, 9, 10, 11);
insert into dishes_list (dish_id_1, dish_id_2, dish_id_3, dish_id_4, dish_id_5, dish_id_6) values (1, 2, 5, 7, 8, 9);

create table ingredient(
ID int not null auto_increment,
name varchar(20),
primary key (id)
);

insert into ingredient (name) value('Potato');
insert into ingredient (name) value('Beet');
insert into ingredient (name) value('Cabbage');
insert into ingredient (name) value('Onion');
insert into ingredient (name) value('Rice');
insert into ingredient (name) value('Pasta');
insert into ingredient (name) value('Sauce');
insert into ingredient (name) value('Tea');
insert into ingredient (name) value('Coffee');
insert into ingredient (name) value('Apple juice');
insert into ingredient (name) value('Orange juice');
insert into ingredient (name) value('Sweet pepper');
insert into ingredient (name) value('Cheese');
insert into ingredient (name) value('Cucumber');
insert into ingredient (name) value('Sun oil');
insert into ingredient (name) value('Pork');
insert into ingredient (name) value('Beef');

create table ingredients_list(
id int not null auto_increment,
ingredient_id_1 int,
ingredient_id_2 int,
ingredient_id_3 int,
ingredient_id_4 int,
ingredient_id_5 int,
primary key (id)
);

insert into ingredients_list (ingredient_id_1, ingredient_id_2, ingredient_id_3, ingredient_id_4, ingredient_id_5) values (1, 3, 4, 12, 15);
insert into ingredients_list (ingredient_id_1, ingredient_id_2, ingredient_id_3, ingredient_id_4, ingredient_id_5) values (1, 2, 3, 4, 15);
insert into ingredients_list (ingredient_id_1, ingredient_id_2, ingredient_id_3, ingredient_id_4, ingredient_id_5) values (4, 5, 7, 15, 17);
insert into ingredients_list (ingredient_id_1, ingredient_id_2, ingredient_id_3) values (6, 7, 13);
insert into ingredients_list (ingredient_id_1) values (8);
insert into ingredients_list (ingredient_id_1) values (9);
insert into ingredients_list (ingredient_id_1, ingredient_id_2) values (10, 11);
insert into ingredients_list (ingredient_id_1, ingredient_id_2, ingredient_id_3, ingredient_id_4) values (7, 12, 13, 15);
insert into ingredients_list (ingredient_id_1, ingredient_id_2, ingredient_id_3, ingredient_id_4) values (3, 7, 13, 16);
insert into ingredients_list (ingredient_id_1, ingredient_id_2) values (17, 7);
insert into ingredients_list (ingredient_id_1, ingredient_id_2, ingredient_id_3, ingredient_id_4, ingredient_id_5) values (4, 7, 13, 14, 16);

create table storage(
ingredient_id int not null,
amount int not null,
primary key (ingredient_id, amount)
);

insert into restaurant.storage values (1, 100);
insert into restaurant.storage values (2, 90);
insert into restaurant.storage values (3, 95);
insert into restaurant.storage values (4, 102);
insert into restaurant.storage values (5, 50);
insert into restaurant.storage values (6, 60);
insert into restaurant.storage values (7, 30);
insert into restaurant.storage values (8, 30);
insert into restaurant.storage values (9, 40);
insert into restaurant.storage values (10, 55);
insert into restaurant.storage values (11, 75);
insert into restaurant.storage values (12, 35);
insert into restaurant.storage values (13, 28);
insert into restaurant.storage values (14, 62);
insert into restaurant.storage values (15, 15);
insert into restaurant.storage values (16, 105);
insert into restaurant.storage values (17, 88);

alter table cooked_dishes
add constraint foreign key (dish_id)
references dish(id)
on delete restrict
on update cascade,
add constraint foreign key (cooker_id)
references employee(id)
on delete restrict
on update cascade,
add constraint foreign key (order_id)
references restaurant.order(id)
on delete restrict
on update cascade;

alter table dish
add constraint foreign key (category)
references dishes_category(id)
on delete restrict
on update cascade,
add constraint foreign key (ingredients_list_id)
references ingredients_list(id)
on delete restrict
on update cascade;

alter table dishes_list
add constraint foreign key (dish_id_1)
references dish(id)
on delete restrict
on update cascade,
add constraint foreign key (dish_id_2)
references dish(id)
on delete restrict
on update cascade,
add constraint foreign key (dish_id_3)
references dish(id)
on delete restrict
on update cascade,
add constraint foreign key (dish_id_4)
references dish(id)
on delete restrict
on update cascade,
add constraint foreign key (dish_id_5)
references dish(id)
on delete restrict
on update cascade,
add constraint foreign key (dish_id_6)
references dish(id)
on delete restrict
on update cascade,
add constraint foreign key (dish_id_7)
references dish(id)
on delete restrict
on update cascade,
add constraint foreign key (dish_id_8)
references dish(id)
on delete restrict
on update cascade,
add constraint foreign key (dish_id_9)
references dish(id)
on delete restrict
on update cascade,
add constraint foreign key (dish_id_10)
references dish(id)
on delete restrict
on update cascade;

alter table employee
add constraint foreign key (position)
references restaurant.position(id)
on delete restrict
on update cascade;

alter table ingredients_list
add constraint foreign key (ingredient_id_1)
references ingredient(id)
on delete restrict
on update cascade,
add constraint foreign key (ingredient_id_2)
references ingredient(id)
on delete restrict
on update cascade,
add constraint foreign key (ingredient_id_3)
references ingredient(id)
on delete restrict
on update cascade,
add constraint foreign key (ingredient_id_4)
references ingredient(id)
on delete restrict
on update cascade,
add constraint foreign key (ingredient_id_5)
references ingredient(id)
on delete restrict
on update cascade;

alter table menu
add constraint foreign key (dishes_list_id)
references dishes_list(id)
on delete restrict
on update cascade;

alter table restaurant.order
add constraint foreign key (waiter_id)
references employee(id)
on delete restrict
on update cascade,
add constraint foreign key (dishes_list)
references dishes_list(id)
on delete restrict
on update cascade;

alter table storage
add constraint foreign key (ingredient_id)
references ingredient(id)
on delete restrict
on update cascade;