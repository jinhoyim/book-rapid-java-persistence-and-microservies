create database eblog_r2;

create table POST (
    id serial primary key not null,
    title varchar(500),
    content text
);
