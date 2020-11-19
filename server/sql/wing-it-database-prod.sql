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
    hidden boolean not null,
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
    password_hash varchar(500) not null,
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

insert into manufacturer(name)
    values ('Boeing'),
            ('Airbus');

insert into model(name, manufacturer_id)
    values ('747', 1),
            ('777', 1),
            ('A400M', 2),
            ('C-150', 1),
            ('Learjet 23', 2),
            ('Douglas DC-3', 2);
            
insert into size(size)
    values ('small'),
            ('medium'),
            ('large');

insert into `type`(name)
    values ('commercial'),
            ('cargo'),
            ('private');

insert into plane(model_id, size_id, type_id, price, quantity, seating_capacity,
                    height, length, wingspan, hidden, `range`, `description`)
    values (1, 2, 1, 418000.00, 15, 410, 63, 250, 224, 0,  7730, 'The First Class and Business Class sections of the 747-8 enable airlines to offer passengers the most private and premium accommodations in the sky. So it''s no surprise that on high-volume routes the 747-8 offers premium revenue potential. And with more than 400 seats available, this 747 creates a unique opportunity to maximize the bottom-line potential of any high-volume route. It''s premium value, delivered, for operators and passengers.'),
            (2, 2, 1, 315000.00, 22, 317, 61, 209, 212, 0, 8555, 'The Boeing 777''s unique combination of superior range, outstanding fuel efficiency and passenger-preferred comfort has created long-range success for carriers around the world. And the 777-300ER now gives operators a perfect opportunity to extend that success. Recent upgrades further reduce costs and boost revenue, and the 777''s flying experience is still number one with passengers. A more profitable fleet and more satisfied passengers.'),
            (4, 1, 3, 650000.00, 21, 8, 19, 80, 76, 1, 3150, 'The all-new C-150 is purpose-built from the ground up — redesigned to be the toughest, most productive C-150 ever. This is what happens when you merge premium-grade muscle with finely tuned intelligence and design. A beast with brains. A relentlessly tough, high-strength, military-grade, aluminum-alloy body and torture-tested high-strength steel frame with new tech to help you work smarter and harder.'),
            (3, 3, 2, 360000.00, 15, 25, 48, 148, 139, 0, 4800, 'The A400M "Atlas" is the most advanced, proven and certified airlifter available, combining 21st century state-of-the-art technologies to fulfil the current and upcoming armed forces’ needs. The A400M combines the capability to carry strategic loads with the ability to deliver even into tactical locations with small and unprepared airstrips and can act as a frontline-tanker. One aircraft that can do the work of three – the A400M.');
            
insert into role (role)
    values ('user'),
            ('admin');
            
insert into user(role_id, username, password_hash, email, phone, address)
    values(1, 'customer', 'cust-pw-hash', 'cust@cust.com', '111-111-1111', '111 1st St.'),
            (1, 'buyer', 'buyer-pw-hash', 'buy@buy.com', '222-222-2222', '222 2nd St'),
            (2, 'admin', 'admin-pw-hash', 'admin@admin.com', '333-333-3333', '333 3rd St');


insert into `order`(user_id, order_date, total_cost)
    values (1, '2020-01-01', 733000),
            (1, '2020-02-02', 630000),
            (2, '2020-03-03', 1950000);

insert into order_plane (order_id, plane_id, number_ordered)
    values(1, 1, 1),
            (1, 2, 1),
            (2, 2, 2),
            (3, 3, 3);


