create database student_management_n1224c1;
use student_management_n1224c1;

create table student
(
    id    int auto_increment primary key,
    name  nvarchar(50) not null ,
    score double
);

create table teacher
(
    id    int auto_increment primary key,
    name  nvarchar(50) not null ,
    phone int
);

insert into student(id, name, score)
values(1,'anh tu',10),
      (2,'van kieu',10);

insert into teacher(id, name, phone)
values(1,'giao vien one',1010),
      (2,'giao vien two',1001);

select id, name, score from student;