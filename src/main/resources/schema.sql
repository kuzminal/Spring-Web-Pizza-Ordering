create table if not exists pizza_order (
    id bigserial PRIMARY KEY NOT NULL ,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
    );
create table if not exists pizza (
    id bigserial PRIMARY KEY NOT NULL ,
    name varchar(50) not null,
    pizza_order bigint not null,
    pizza_order_key bigint not null,
    created_at timestamp not null
    );
create table if not exists ingredient_ref (
    ingredient varchar(4) not null,
    pizza bigint not null,
    pizza_key bigint not null
    );
create table if not exists ingredient (
    id varchar(4) not null PRIMARY KEY ,
    name varchar(25) not null,
    type varchar(10) not null
    );

create table if not exists "user" (
    id bigserial PRIMARY KEY NOT NULL ,
    username varchar(25) not null,
    password varchar(150) not null,
    fullname varchar(50) not null,
    street varchar(150) not null,
    city varchar(50) not null,
    state varchar(2) not null,
    zip varchar(10) not null,
    phone_number varchar(25) not null
    );