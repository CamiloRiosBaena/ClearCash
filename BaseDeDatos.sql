create database Login;
use Login;

SELECT user, host FROM mysql.user;

create table Users(
 id int auto_increment primary key not null,
 username nvarchar(50),
 typePassword nvarchar(50)
);

select * from Users;

insert into Users(username, typePassword) values ('admin', '123')



