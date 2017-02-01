create database hibernate_set;
 
use hibernate_set;
 
create table messagea ( 
id bigint(20) not null auto_increment, 
text varchar(255) null default null, 
primary key (id) 
); 

create table messageb ( 
id bigint(20) not null auto_increment, 
text varchar(255) null default null, 
primary key (id) 
); 
