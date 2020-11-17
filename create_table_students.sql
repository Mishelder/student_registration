create table students(
id integer default students_sq.nextval not null,
first_name varchar2(40) not null,
last_name varchar2(50) not null,
email varchar2(60) not null
);

create sequence students_sq increment by 1 start with 1;

alter table students add constraint primary_key_id_students primary key(id);
alter table students add constraint unique_email unique(email);

insert into students (first_name,last_name,email)
values ('Misha','Skorohodov','misha@luv2code.com');

delete from students where id =1;
commit;