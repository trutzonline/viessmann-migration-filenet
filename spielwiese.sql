-- MIGSTATUS Tabelle erzeugen
create table migstatus(p8id varchar(100), status varchar(30),migtime timestamp, exception varchar(5000));

insert into migstatus(p8id) values(1);
insert into migstatus(p8id) values(2);
insert into migstatus(p8id) values(3);
insert into migstatus(p8id) values(4);
insert into migstatus(p8id) values(5);