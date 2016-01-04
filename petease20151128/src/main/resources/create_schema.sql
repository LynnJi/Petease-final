USE petease;

#DROP TABLE authentication;

CREATE TABLE authentication
(
token varchar(256) NOT NULL,
user_id varchar(40) NOT NULL,
created_date  datetime NOT NULL,
deleted_date datetime,
CONSTRAINT pk_authentication_token PRIMARY KEY (token)
);

#DROP TABLE user;

CREATE TABLE user
(
user_id varchar(40) NOT NULL,
password varchar(256) NOT NULL,
auth_type char(2) NOT NULL,
first_name varchar(40) NOT NULL,
last_name varchar(40) NOT NULL,
prefer_name varchar(40) NOT NULL,
gender char(2) NOT NULL,
DOB date,
address varchar(100),
city varchar(40),
state varchar(2),
prefer_pets varchar(50),
feeding_pets boolean,
pet_name varchar(40),
career char(2),
created_date  datetime NOT NULL,
deleted_date datetime,
CONSTRAINT pk_user_user_id PRIMARY KEY (user_id)
);
#Alter table User;

#DROP TABLE post;

CREATE TABLE post
(
post_id bigint NOT NULL AUTO_INCREMENT,
user_id varchar(40) NOT NULL,
topic varchar(100) NOT NULL,
content longtext NOT NULL,
pic_url varchar(900),
created_date  datetime NOT NULL,
reported boolean,
date_reported datetime,
comment_admin tinytext,
status char(2),
deleted_date datetime,
CONSTRAINT pk_post_post_id PRIMARY KEY (post_id)
);
#ALTER TABLE post
#ADD CONSTRAINT fk_post_user_user_id FOREIGN KEY (user_id) REFERENCES user(user_id);

##DROP TABLE post_status;

CREATE TABLE post_status
(
status_id bigint NOT NULL AUTO_INCREMENT,
post_id bigint NOT NULL,
rate tinyint NOT NULL,
view_count int DEFAULT 0,
comments_count int DEFAULT 0,
CONSTRAINT pk_post_status_status_id PRIMARY KEY (status_id)
);
#ALTER TABLE post_status 
#ADD CONSTRAINT fk_post_status_post_post_id FOREIGN KEY (post_id) REFERENCES post(post_id);


#DROP TABLE qa;

CREATE TABLE qa
(
qa_id int NOT NULL AUTO_INCREMENT,
user_id varchar(40) NOT NULL,
question_title varchar(100) NOT NULL,
q_content text NOT NULL,
a_content text,
server_id varchar(40),
CONSTRAINT pk_qa_qa_id PRIMARY KEY (qa_id)
);
#ALTER TABLE qa 
#ADD CONSTRAINT fk_qa_user_user_id FOREIGN KEY (user_id) REFERENCES user(user_id);
#ALTER TABLE qa 
#ADD CONSTRAINT fk_qa_user_server_id FOREIGN KEY (server_id) REFERENCES user(user_id);


#DROP TABLE rate;

CREATE TABLE rate
(
rate_id bigint NOT NULL AUTO_INCREMENT,
user_id varchar(40) NOT NULL,
post_id bigint NOT NULL,
rate tinyint NOT NULL,
CONSTRAINT pk_rate_rate_id PRIMARY KEY (rate_id)
);
#ALTER TABLE rate
#ADD CONSTRAINT fk_rate_user_user_id FOREIGN KEY (user_id) REFERENCES user(user_id);
#ALTER TABLE rate
#ADD CONSTRAINT fk_rate_post_post_id FOREIGN KEY (post_id) REFERENCES post(post_id);


#DROP TABLE comment;

CREATE TABLE comment
(
comment_id bigint NOT NULL AUTO_INCREMENT,
user_id varchar(40) NOT NULL,
post_id bigint NOT NULL,
comment tinytext NOT NULL,
create_date datetime NOT NULL,
delete_date datetime,
status char(2),
CONSTRAINT pk_comment_comment_id PRIMARY KEY (comment_id)
);
#ALTER TABLE comment
#ADD CONSTRAINT fk_comment_user_user_id FOREIGN KEY (user_id) REFERENCES user(user_id);
#ALTER TABLE comment
#ADD CONSTRAINT fk_comment_post_post_id FOREIGN KEY (post_id) REFERENCES post(post_id);

#DROP TABLE news;

CREATE TABLE news
(
news_id bigint NOT NULL AUTO_INCREMENT,
admin_id varchar(40) NOT NULL,
news_title varchar(255) NOT NULL,
content longtext NOT NULL,
pic_url varchar(900),
created_date datetime NOT NULL,
deleted_date datetime,
CONSTRAINT pk_news_news_id PRIMARY KEY (news_id)
);
#ALTER TABLE news
#ADD CONSTRAINT fk_news_user_user_id FOREIGN KEY (admin_id) REFERENCES user(user_id)

CREATE VIEW petease.post_view AS 
SELECT p.post_id AS p_post_id,
       p.topic AS p_topic,
       u.user_id AS u_user_id,
       u.password AS u_password,
       u.auth_type AS u_auth_type,
       u.gender AS u_gender,
       u.DOB AS u_birthday,
       u.first_name AS u_first_name,
       u.last_name AS u_last_name,
       u.prefer_name AS u_prefer_name,
       u.address AS u_address,
       u.city AS u_city,
       u.state AS u_state,
       u.prefer_pets AS u_prefer_pets,
       u.feeding_pets AS u_feeding_pets,
       u.pet_name AS u_pet_name,
       u.career AS u_career,
       p.content AS p_content,
       p.pic_url AS p_pic_url,
       p.created_date AS p_created_date,
       p.reported AS p_reported,
       p.date_reported AS p_reported_date,
       a.user_id AS a_user_id,
       a.password AS a_password,
       a.auth_type AS a_auth_type,
       a.gender AS a_gender,
       a.DOB AS a_birthday,
       a.first_name AS a_first_name,
       a.last_name AS a_last_name,
       a.prefer_name AS a_prefer_name,
       a.address AS a_address,
       a.city AS a_city,
       a.state AS a_state,
       a.prefer_pets AS a_prefer_pets,
       a.feeding_pets AS a_feeding_pets,
       a.pet_name AS a_pet_name,
       a.career AS a_career,
       p.status AS p_status,
       p.deleted_date AS p_deleted_date,
       ps.rate AS ps_rate,
       ps.view_count AS ps_view_count,
       ps.comments_count AS ps_comments_count
FROM petease.post p INNER JOIN petease.user u ON p.user_id=u.user_id
            LEFT JOIN petease.user a ON p.comment_admin=u.user_id
            INNER JOIN petease.post_status ps ON p.post_id=ps.post_id;
            
CREATE VIEW petease.comment_view AS
SELECT c.comment_id AS comment_id,
       c.post_id AS post_id,
       c.user_id AS user_id,
       u.prefer_name AS prefer_name,
       c.comment AS comment,
       c.create_date AS create_date,
       c.delete_date AS delete_date,
       c.status AS status
FROM comment c INNER JOIN user u ON c.user_id=u.user_id;
