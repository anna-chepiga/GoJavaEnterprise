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

create table restaurant.created_order(
    id int not null auto_increment,
    waiter_id int not null,
    table_number int,
    date date not null,
    primary key (id)
);

insert into restaurant.created_order(waiter_id,  table_number, date) values(6, 12, '2016-10-20');
insert into restaurant.created_order(waiter_id,  table_number, date) values(8, 5, '2016-10-20');
insert into restaurant.created_order(waiter_id, table_number, date) values(8, 2, '2016-10-21');
insert into restaurant.created_order(waiter_id, table_number, date) values(7, 4, '2016-10-21');
insert into restaurant.created_order(waiter_id, table_number, date) values(9, 8, '2016-10-21');
insert into restaurant.created_order(waiter_id, table_number, date) values(6, 3, '2016-10-21');

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

create table dish(
    id int not null auto_increment,
    name varchar(20) not null,
    category int not null,
    price double not null,
    weight int not null,
    primary key (id)
);

insert into dish (name, category, price, weight) values('Kharcho', 1, 10, 200);
insert into dish (name, category, price, weight) values('Borshch', 1, 9.5, 250);
insert into dish (name, category, price, weight) values('Pilaff', 2, 8.5, 300);
insert into dish (name, category, price, weight) values('Pasta', 2, 9.2, 250);
insert into dish (name, category, price, weight) values('Tea', 3, 3, 300);
insert into dish (name, category, price, weight) values('Coffee', 3, 3.5, 250);
insert into dish (name, category, price, weight) values('Juice', 3, 4, 300);
insert into dish (name, category, price, weight) values('Greek salad', 4, 5, 300);
insert into dish (name, category, price, weight) values('Cesar', 4, 6.5, 300);
insert into dish (name, category, price, weight) values('Steak', 5, 7, 150);
insert into dish (name, category, price, weight) values('Hamburger', 5, 6.5, 250);

create table order_dishes_list(
    created_order_id int,
    dish_id int);

insert into order_dishes_list values(1, 4);
insert into order_dishes_list values(1, 5);
insert into order_dishes_list values(1, 8);
insert into order_dishes_list values(2, 1);
insert into order_dishes_list values(2, 2);
insert into order_dishes_list values(2, 7);

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
    primary key (id)
);

insert into menu values(1, 'Breakfast');
insert into menu values(2, 'Lunch');
insert into menu values(3, 'Dinner');

create table menu_dishes_list(
    menu_id int,
    dish_id int
);

insert into menu_dishes_list values(1, 4);
insert into menu_dishes_list values(1, 5);
insert into menu_dishes_list values(1, 6);
insert into menu_dishes_list values(1, 8);
insert into menu_dishes_list values(2, 1);
insert into menu_dishes_list values(2, 2);
insert into menu_dishes_list values(2, 3);
insert into menu_dishes_list values(2, 5);
insert into menu_dishes_list values(2, 7);
insert into menu_dishes_list values(3, 5);
insert into menu_dishes_list values(3, 7);
insert into menu_dishes_list values(3, 8);
insert into menu_dishes_list values(3, 10);
insert into menu_dishes_list values(3, 11);

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

create table dish_ingredients(
    dish_id int,
    ingredient_id int
);

insert into dish_ingredients values(1,1);
insert into dish_ingredients values(1,3);
insert into dish_ingredients values(1,4);
insert into dish_ingredients values(1,12);
insert into dish_ingredients values(1,15);
insert into dish_ingredients values(2,1);
insert into dish_ingredients values(2,2);
insert into dish_ingredients values(2,3);
insert into dish_ingredients values(2,4);
insert into dish_ingredients values(2,15);

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
references restaurant.created_order(id)
    on delete restrict
    on update cascade;

alter table dish
    add constraint foreign key (category)
references dishes_category(id)
    on delete restrict
    on update cascade;

alter table employee
    add constraint foreign key (position)
references restaurant.position(id)
    on delete restrict
    on update cascade;

alter table restaurant.created_order
    add constraint foreign key (waiter_id)
references employee(id)
    on delete restrict
    on update cascade;

alter table storage
    add constraint foreign key (ingredient_id)
references ingredient(id)
    on delete restrict
    on update cascade;

alter table dish_ingredients
    add constraint foreign key(dish_id)
references dish(id)
    on delete restrict
    on update cascade,
    add constraint foreign key(ingredient_id)
references ingredient(id)
    on delete restrict
    on update cascade;

alter table menu_dishes_list
    add constraint foreign key(dish_id)
references dish(id)
    on delete restrict
    on update cascade,
    add constraint foreign key(menu_id)
references menu(id)
    on delete restrict
    on update cascade;

alter table order_dishes_list
    add constraint foreign key(created_order_id)
references created_order(id)
    on delete restrict
    on update cascade,
    add constraint foreign key(dish_id)
references dish(id)
    on delete restrict
    on update cascade;