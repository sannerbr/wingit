drop database if exists wing_it_test;
create database wing_it_test;
use wing_it_test;

create table manufacturer (
    manufacturer_id int primary key auto_increment,
    name varchar(50) not null
);

create table model (
    model_id int primary key auto_increment,
    name varchar(50) not null,
    manufacturer_id int not null,
    constraint fk_model_manufacturer_id
        foreign key (manufacturer_id)
        references manufacturer(manufacturer_id)
);

create table `type` (
    type_id int primary key auto_increment,
    name varchar(20) not null
);

create table size (
    size_id int primary key auto_increment,
    size varchar(45) not null
);


create table plane (
    plane_id int primary key auto_increment,
    model_id int not null UNIQUE,
    size_id int not null,
    type_id int not null,
    price int not null,
    quantity int not null,
    seating_capacity int not null,
    height int not null,
    length int not null,
    wingspan int not null,
    `range` int not null,
    `description` varchar(500) not null,
    constraint fk_plane_model_id
        foreign key (model_id)
        references model(model_id),
    constraint fk_plane_size_size_id
        foreign key (size_id)
        references size(size_id),
    constraint fk_plane_type_type_id
        foreign key (type_id)
        references type(type_id)
);

create table image (
    image_id int primary key auto_increment,
    plane_id int not null,
    image longblob not null,
    constraint fk_image_plane_id
        foreign key (plane_id)
        references plane(plane_id)
);

create table role (
    role_id int primary key auto_increment,
    role varchar(10) not null
);

create table user (
    user_id int primary key auto_increment,
    role_id int not null,
    username varchar(50) not null,
    password_hash varchar(50) not null,
    email varchar(248) not null,
    phone varchar(20) not null,
    address varchar(200) not null,
    company varchar(45) null,
    constraint fk_user_role_role_id
        foreign key (role_id)
        references role(role_id)
);

create table `order` (
    order_id int primary key auto_increment,
    user_id int not null,
    order_date date not null,
    total_cost decimal(12,2) not null,
    constraint fk_order_user_user_id
        foreign key (user_id)
        references user(user_id),
);

create table order_plane (
    order_id int not null,
    plane_id int not null,
    number_ordered int not null,
    constraint pk_order_plane
        primary_key(order_id, plane_id),
    constraint fk_order_plane_order_id
        foreign key (order_id)
        references `order`(order_id),
    constraint fk_order_plane_plane_id
        foreign key (plane_id)
        references plane(plane_id)
);


delimiter //
create procedure set_known_good_state()
begin
    truncate table order_plane;
    truncate table order;
    truncate table user;
    truncate table plane;
    truncate table model;
    truncate table manufacturer;

insert into manufacturer(name)
    values ('Boeing'),
            ('Airbus');

insert into model(name, manufacturer_id)
    values ('747', 1),
            ('777', 1),
            ('A220', 2);

insert into plane(model_id, size_id, type_id, price, quantity, seating_capacity,
                    height, length, wingspan, `range`, `description`)
    values (1, 1, 1, 100.00, 1, 100, 10, 10, 10, 100, 'Boeing 747 desc'),
            (2, 2, 2, 200.00, 2, 200, 20, 20, 20, 200, 'Boeing 777 desc'),
            (3, 3, 3, 300.00, 3, 300, 30, 30, 30, 300, 'Airbus A220 desc');

insert into user(role_id, username, password_hash, email, phone, address)
    values(1, 'customer', 'cust-pw-hash', 'cust@cust.com', '111-111-1111', '111 1st St.'),
            (1, 'buyer', 'buyer-pw-hash', 'buy@buy.com', '222-222-2222', '222 2nd St'),
            (2, 'admin', 'admin-pw-hash', 'admin@admin.com', '333-333-3333', '333 3rd St');


insert into `order`(user_id, order_date, total_cost)
    values (1, '2020-01-01', 100),
            (1, '2020-02-02', 200),
            (2, '2020-03-03', 300);

insert into order_plane (order_id, plane_id, number_ordered)
    values(1, 1, 1),
            (1, 2, 1),
            (2, 2, 2),
            (3, 3, 3);

end//
delimiter ;

insert into role (role)
    values ('user'),
            ('admin');

insert into size(size)
    values ('small'),
            ('medium'),
            ('large');

insert into `type`(name)
    values ('commercial'),
            ('cargo'),
            ('private');

insert into `order`(user_id, plane_id, order_date, total_cost)
    values (1, 1, '2020-01-01', 100),
            (1, 2, '2020-02-02', 200),
            (2, 3, '2020-03-03', 300);
            
