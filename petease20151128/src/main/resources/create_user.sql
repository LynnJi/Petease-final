#drop schema petease; 
create schema petease; 
drop user 'petease'@'localhost';
create user 'petease'@'localhost' identified by '111111';
grant all privileges on petease.* to 'petease'@'localhost' with grant option;
show grants for 'petease'@'localhost';