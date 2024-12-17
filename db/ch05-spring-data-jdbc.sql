create database eblog;

create table POST (
                      id serial primary key not null,
                      title varchar(500),
                      content text,
                      author int
);
create table AUTHOR (
                        id serial primary key not null,
                        name varchar(100),
                        age int
);
insert into author(name, age) values('Raj Malhorta', 35);
insert into post(title, content, author) values('Sample Title', 'Sample Content', 1);