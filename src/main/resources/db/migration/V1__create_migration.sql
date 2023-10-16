create schema if not exists labs;
create table labs.lab (
                     id  bigserial not null,
                     description varchar(255),
                     name varchar(255),
                     status int4,
                     username varchar(255),
                     registerDate DATE,
                     primary key (id)
);