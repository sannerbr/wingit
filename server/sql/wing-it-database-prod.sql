drop database if exists wing_it_prod;
create database wing_it_prod;
use wing_it_prod;

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
    hidden bit(1) not null,
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
        references user(user_id)
);

create table order_plane (
    order_id int not null,
    plane_id int not null,
    number_ordered int not null,
    constraint pk_order_plane
        primary key(order_id, plane_id),
    constraint fk_order_plane_order_id
        foreign key (order_id)
        references `order`(order_id),
    constraint fk_order_plane_plane_id
        foreign key (plane_id)
        references plane(plane_id)
);

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