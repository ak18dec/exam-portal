--CREATE TABLE roles(
--     id serial PRIMARY KEY,
--     role_name VARCHAR (255) UNIQUE NOT NULL
--);

--create table users (
--	id serial primary key,
--	username varchar(50) unique not null,
--	password varchar(50) unique not null,
--	first_name varchar(100) not null,
--	last_name varchar(255),
--	email varchar(255) unique not null,
--	phone varchar(255),
--	enabled boolean not null,
--	profile varchar(255),
--	created_by int not null,
--	created_on timestamp default current_timestamp,
--	modified_by int not null,
--	modified_on timestamp default current_timestamp
--)

--create table user_role (
--	id serial primary key,
--	user_id int not null,
--	role_id int not null
--)

--ALTER TABLE user_role
--ADD CONSTRAINT fk_user_role_users
--FOREIGN KEY (user_id) REFERENCES users(id);
--
--ALTER TABLE user_role
--ADD CONSTRAINT fk_user_role_roles
--FOREIGN KEY (role_id) REFERENCES roles(id);

--CREATE TABLE genres (
--    id serial primary key,
--    title varchar(100),
--    description varchar(255),
--    enabled boolean
--	  created_by int not null,
--	  created_on timestamp default current_timestamp,
--	  modified_by int not null,
--	  modified_on timestamp default current_timestamp
--)

--create table proficiency(
-- 	id serial primary key,
-- 	level varchar(50) unique not null
--);

--insert into proficiency(level)
--values ('Easy')
--
--insert into proficiency(level)
--values ('Medium')
--
--insert into proficiency(level)
--values ('Hard')


--CREATE TABLE subjects (
--    id serial primary key,
--    title varchar(100),
--    description varchar(255),
--    enabled boolean,
--    genre_id int not null,
--  	created_by int not null,
--  	created_on timestamp default current_timestamp,
--  	modified_by int not null,
--  	modified_on timestamp default current_timestamp
--)

--ALTER TABLE subjects
--ADD CONSTRAINT fk_subject_genre_id
--FOREIGN KEY (genre_id) REFERENCES genres(id);


--CREATE TABLE categories (
--    id serial primary key,
--    title varchar(100),
--    description varchar(255),
--    enabled boolean,
--    subject_id int not null,
--  	created_by int not null,
--  	created_on timestamp default current_timestamp,
--  	modified_by int not null,
--  	modified_on timestamp default current_timestamp
--)


--ALTER TABLE categories
--ADD CONSTRAINT fk_category_subject_id
--FOREIGN KEY (subject_id) REFERENCES subjects(id);


--CREATE TABLE topics (
--    id serial primary key,
--    title varchar(100),
--    description varchar(255),
--    enabled boolean,
--    category_id int not null,
--  	created_by int not null,
--  	created_on timestamp default current_timestamp,
--  	modified_by int not null,
--  	modified_on timestamp default current_timestamp
--)

--ALTER TABLE topics
--ADD CONSTRAINT fk_topic_category_id
--FOREIGN KEY (category_id) REFERENCES categories(id);


--CREATE TABLE questions (
--    id serial primary key,
--    title varchar(100),
--    description varchar(255),
--    enabled boolean,
--    proficiency_id int not null,
--    topic_id int not null,
--  	created_by int not null,
--  	created_on timestamp default current_timestamp,
--  	modified_by int not null,
--  	modified_on timestamp default current_timestamp
--)

--ALTER TABLE questions
--ADD CONSTRAINT fk_ques_prof_id
--FOREIGN KEY (proficiency_id) REFERENCES proficiency(id);


--ALTER TABLE questions
--ADD CONSTRAINT fk_ques_topic_id
--FOREIGN KEY (topic_id) REFERENCES topics(id);


--CREATE TABLE question_choices (
--    id serial primary key,
--    description varchar(255),
--    enabled boolean,
--    correct boolean,
--    ques_id int not null,
--  	created_by int not null,
--  	created_on timestamp default current_timestamp,
--  	modified_by int not null,
--  	modified_on timestamp default current_timestamp
--)

--ALTER TABLE question_choices
--ADD CONSTRAINT fk_choice_ques_id
--FOREIGN KEY (ques_id) REFERENCES questions(id);

--CREATE TABLE instructions (
--    id serial primary key,
--    content varchar(255),
--    enabled boolean,
--	created_by int not null,
--	created_on timestamp default current_timestamp,
--	modified_by int not null,
--	modified_on timestamp default current_timestamp
--)

--CREATE TABLE quiz (
--    id serial primary key,
--    title varchar(100) not null,
--    description varchar(255),
--    published boolean,
--    proficiency_id int not null,
--    max_marks int not null,
--    max_time int not null,
--    instruction_enabled boolean,
--  	created_by int not null,
--  	created_on timestamp default current_timestamp,
--  	modified_by int not null,
--  	modified_on timestamp default current_timestamp
--)

--create table quiz_ques (
--	id serial primary key,
--	quiz_id int not null,
--	question_id int not null
--)
--ALTER TABLE quiz_ques
--ADD CONSTRAINT fk_ques_quiz_id
--FOREIGN KEY (quiz_id) REFERENCES quiz(id);


--create table quiz_instruction (
--	id serial primary key,
--	quiz_id int not null,
--	instruction_id int not null
--)
--ALTER TABLE quiz_instruction
--ADD CONSTRAINT fk_instr_quiz_id
--FOREIGN KEY (quiz_id) REFERENCES quiz(id);


--create table user_attempted_quiz (
--	id serial primary key,
--	attempted_quiz_id int not null,
--	user_id int not null,
--	user_full_name varchar not null,
--	username varchar not null,
--	attempted_on timestamp,
--	proficiency varchar,
--	max_marks int,
--	max_time int,
--	user_time int,
--	score decimal
--)

--alter table user_attempted_quiz
--add constraint fk_usr_attempted_quiz_id
--foreign key (attempted_quiz_id) references attempted_quiz(id);


--create table attempted_quiz (
--	id serial primary key,
--	title varchar ,
--	description varchar
--)

--create table attempted_quiz_questions (
--	id serial primary key,
--	attempted_quiz_id int not null,
--	question varchar,
--	option_selected varchar
--)

--alter table attempted_quiz_questions
--add constraint fk_attempted_quiz_id
--foreign key (attempted_quiz_id) references attempted_quiz(id);

