USE master
GO

IF DB_ID('toeic_practice') IS NOT NULL
	DROP DATABASE toeic_practice
GO
CREATE DATABASE toeic_practice
GO
USE toeic_practice
GO

CREATE TABLE tbl_role
(
	id int IDENTITY(1,1) PRIMARY KEY,
	name varchar(30) NOT NULL CONSTRAINT AK_role_name UNIQUE
)

INSERT INTO tbl_role(name)
VALUES
	('ROLE_ADMIN'),
	('ROLE_STUDENT')
GO

CREATE TABLE tbl_user
(
	id int IDENTITY(1,1) PRIMARY KEY,
	email varchar(50) NOT NULL CONSTRAINT AK_unique_user_email UNIQUE,
	name varchar(50) NULL,
	password varchar(60) NOT NULL,
	role_id int NOT NULL CONSTRAINT FK_user_role FOREIGN KEY REFERENCES tbl_role(id),
	created_at smalldatetime NOT NULL CONSTRAINT df_user_created_at DEFAULT getdate(),
	updated_at smalldatetime NULL
)

INSERT INTO tbl_user(email, password, role_id)
VALUES
	('admin@toeic.com','12345678',1),
	('student@toeic.com','12345678',2)
GO

CREATE TABLE tbl_topic
(
	id smallint IDENTITY(1,1) PRIMARY KEY,
	name varchar(30) NOT NULL CONSTRAINT AK_topic_name UNIQUE,
	description varchar(300) NULL
)

INSERT INTO tbl_topic(name)
VALUES 
	('reading'),
	('listening'),
	('vocabulary'),
	('general')
GO

CREATE TABLE tbl_level
(
	id smallint IDENTITY(1,1) PRIMARY KEY,
	name varchar(30) NOT NULL CONSTRAINT AK_quiz_level_name UNIQUE,
	description varchar(300) NULL
)

INSERT INTO tbl_level(name)
VALUES
	('beginner'),
	('intermediate'),
	('advanced')
GO

CREATE TABLE tbl_quiz
(
	id int IDENTITY(1,1) PRIMARY KEY,
	admin_id int NULL CONSTRAINT FK_quiz_created_by FOREIGN KEY REFERENCES tbl_user(id),
	topic_id smallint NULL CONSTRAINT FK_quiz_in_topic FOREIGN KEY REFERENCES tbl_topic(id),
	title varchar(75) NOT NULL CONSTRAINT AK_quiz_title UNIQUE,
	content varchar(1000) NULL,
	slug varchar(100) NULL
)

INSERT INTO tbl_quiz(admin_id, topic_id, title, slug)
VALUES
	(1, 1, 'quiz reading demo 1', 'quiz-reading-demo-1'),
	(1, 1, 'quiz reading demo 2', 'quiz-reading-demo-2'),
	(1, 1, 'quiz reading demo 3', 'quiz-reading-demo-3'),
	(1, 2, 'quiz listening demo 1', 'quiz-listening-demo-1'),
	(1, 2, 'quiz listening demo 2', 'quiz-listening-demo-2')

CREATE TABLE tbl_wishlist
(
	user_id int NOT NULL CONSTRAINT FK_user_has_favor_quiz FOREIGN KEY REFERENCES tbl_user(id),
	quiz_id int NOT NULL CONSTRAINT FK_favor_quiz FOREIGN KEY REFERENCES tbl_quiz(id),
	CONSTRAINT PK_wishlist PRIMARY KEY (user_id, quiz_id)
)

INSERT INTO tbl_wishlist(user_id, quiz_id)
VALUES
	(2, 1),
	(2, 3),
	(2, 4)

CREATE TABLE tbl_question_type
(
	id int IDENTITY(1,1) PRIMARY KEY,
	name varchar(30) NOT NULL CONSTRAINT AK_question_type_name UNIQUE
)

INSERT INTO tbl_question_type(name)
VALUES
	('single'),
	('multiple'),
	('free answer'),
	('essay'),
	('sort')

CREATE TABLE tbl_quiz_part
(
	id int IDENTITY(1,1) PRIMARY KEY,
	content varchar(150) NULL,
	quiz_id int NULL CONSTRAINT FK_quiz_id FOREIGN KEY REFERENCES tbl_quiz(id)
)

INSERT INTO tbl_quiz_part (content, quiz_id)
VALUES
	('Part 1', 1), ('Part 2', 1), ('Part 3', 1), 
	('Part 1', 2), ('Part 2', 2), ('Part 3', 2),
	('Part 1', 3), ('Part 2', 3), ('Part 3', 3),
	('Part 1', 4), ('Part 2', 4), ('Part 3', 4),
	('Part 1', 5), ('Part 2', 5), ('Part 3', 5)

CREATE TABLE tbl_part_section
(
	id int IDENTITY(1,1) PRIMARY KEY,
	content varchar(4000) NULL,
	part_id int NULL CONSTRAINT FK_part_id FOREIGN KEY REFERENCES tbl_quiz_part(id)
)

INSERT INTO tbl_part_section(content, part_id)
VALUES
	(NULL, 1), (NULL, 2), (NULL, 3),
	(NULL, 4), (NULL, 5), (NULL, 6),
	(NULL, 7), (NULL, 8), (NULL, 9),
	(NULL, 10), (NULL, 11), (NULL, 12),
	(NULL, 13), (NULL, 14), (NULL, 15)
GO

CREATE TABLE tbl_question
(
	id int IDENTITY(1,1) PRIMARY KEY,
	quiz_id int NULL CONSTRAINT FK_in_quiz FOREIGN KEY REFERENCES tbl_quiz(id),
	active bit NOT NULL CONSTRAINT df_question_active DEFAULT (1),
	score smallint NOT NULL,
	created_at smalldatetime NOT NULL CONSTRAINT df_question_created_at DEFAULT getdate(),
	updated_at smalldatetime NULL,
	title varchar(75) NULL,
	content varchar(1000) NULL,
	section_id int NULL CONSTRAINT FK_question_section FOREIGN KEY REFERENCES tbl_part_section(id)
)

INSERT INTO tbl_question(quiz_id, score, title, content, section_id)
VALUES
	(1, 1, 'question 1', 'the content of question 1', 1),
	(1, 1, 'question 2', 'the content of question 2', 1),
	(1, 1, 'question 3', 'the content of question 3', 2),
	(1, 1, 'question 4', 'the content of question 4', 2),
	(1, 1, 'question 5', 'the content of question 5', 3),
	(2, 1, 'quiz 2 - question 1', 'the content of question 1', 4),
	(2, 1, 'quiz 2 - question 2', 'the content of question 2', 4),
	(2, 1, 'quiz 2 - question 3', 'the content of question 3', 5),
	(2, 1, 'quiz 2 - question 4', 'the content of question 4', 5),
	(2, 1, 'quiz 2 - question 5', 'the content of question 5', 6),
	(3, 1, 'quiz 3 - question 1', 'the content of question 1', 7),
	(3, 1, 'quiz 3 - question 2', 'the content of question 2', 7),
	(3, 1, 'quiz 3 - question 3', 'the content of question 3', 8),
	(3, 1, 'quiz 3 - question 4', 'the content of question 4', 9),
	(3, 1, 'quiz 3 - question 5', 'the content of question 5', 9),
	(4, 2, 'quiz 4 - question 1', 'the content of question 1', 10),
	(4, 2, 'quiz 4 - question 2', 'the content of question 2', 11),
	(4, 2, 'quiz 4 - question 3', 'the content of question 3', 11),
	(4, 2, 'quiz 4 - question 4', 'the content of question 4', 12),
	(4, 2, 'quiz 4 - question 5', 'the content of question 5', 12),
	(5, 3, 'quiz 5 - question 1', 'the content of question 1', 13),
	(5, 3, 'quiz 5 - question 2', 'the content of question 2', 13),
	(5, 3, 'quiz 5 - question 3', 'the content of question 3', 14),
	(5, 3, 'quiz 5 - question 4', 'the content of question 4', 15),
	(5, 3, 'quiz 5 - question 5', 'the content of question 5', 15)
GO

CREATE TABLE tbl_answer
(
	id int IDENTITY(1,1) PRIMARY KEY,
	question_id int NULL CONSTRAINT FK_in_question FOREIGN KEY REFERENCES tbl_question(id),
	active bit NOT NULL CONSTRAINT df_answer_is_active DEFAULT (1),
	correct bit NOT NULL CONSTRAINT df_answer_is_incorrect DEFAULT(0),
	created_at smalldatetime NOT NULL CONSTRAINT df_answer_created_at DEFAULT getdate(),
	updated_at smalldatetime NULL,
	content varchar(300) NULL
)

INSERT INTO tbl_answer(question_id, correct, content)
VALUES
	(1, 1, 'quiz1 q1 a1'), (1, 0, 'quiz1 q1 a2'), (1, 0, 'quiz1 q1 a3'), (1, 0, 'quiz1 q1 a4'),
	(2, 0, 'quiz1 q2 a1'), (2, 1, 'quiz1 q2 a2'), (2, 0, 'quiz1 q2 a3'), (2, 0, 'quiz1 q2 a4'),
	(3, 0, 'quiz1 q3 a1'), (3, 1, 'quiz1 q3 a2'), (3, 0, 'quiz1 q3 a3'), (3, 0, 'quiz1 q3 a4'),
	(4, 0, 'quiz1 q4 a1'), (4, 0, 'quiz1 q4 a2'), (4, 1, 'quiz1 q4 a3'), (4, 0, 'quiz1 q4 a4'),
	(5, 1, 'quiz1 q5 a1'), (5, 0, 'quiz1 q5 a2'), (5, 0, 'quiz1 q5 a3'), (5, 0, 'quiz1 q5 a4'),
	(6, 0, 'quiz2 q1 a1'), (6, 0, 'quiz2 q1 a2'), (6, 1, 'quiz2 q1 a3'), (6, 0, 'quiz2 q1 a4'),
	(7, 1, 'quiz2 q2 a1'), (7, 0, 'quiz2 q2 a2'), (7, 0, 'quiz2 q2 a3'), (7, 0, 'quiz2 q2 a4'),
	(8, 0, 'quiz2 q3 a1'), (8, 0, 'quiz2 q3 a2'), (8, 1, 'quiz2 q3 a3'), (8, 0, 'quiz2 q3 a4'),
	(9, 1, 'quiz2 q4 a1'), (9, 0, 'quiz2 q4 a2'), (9, 0, 'quiz2 q4 a3'), (9, 0, 'quiz2 q4 a4'),
	(10, 1, 'quiz2 q5 a1'), (10, 0, 'quiz2 q5 a2'), (10, 0, 'quiz2 q5 a3'), (10, 0, 'quiz2 q5 a4'),
	(11, 0, 'quiz3 q1 a1'), (11, 1, 'quiz3 q1 a2'), (11, 0, 'quiz3 q1 a3'), (11, 0, 'quiz3 q1 a4'),
	(12, 0, 'quiz3 q2 a1'), (12, 1, 'quiz3 q2 a2'), (12, 0, 'quiz3 q2 a3'), (12, 0, 'quiz3 q2 a4'),
	(13, 0, 'quiz3 q3 a1'), (13, 0, 'quiz3 q3 a2'), (13, 1, 'quiz3 q3 a3'), (13, 0, 'quiz3 q3 a4'),
	(14, 1, 'quiz3 q4 a1'), (14, 0, 'quiz3 q4 a2'), (14, 0, 'quiz1 q4 a3'), (14, 0, 'quiz1 q4 a4'),
	(15, 0, 'quiz3 q5 a1'), (15, 0, 'quiz3 q5 a2'), (15, 1, 'quiz3 q5 a3'), (15, 0, 'quiz3 q5 a4'),
	(16, 0, 'quiz4 q1 a1'), (16, 1, 'quiz4 q1 a2'), (16, 0, 'quiz4 q1 a3'), (16, 0, 'quiz4 q1 a4'),
	(17, 0, 'quiz4 q2 a1'), (17, 1, 'quiz4 q2 a2'), (17, 0, 'quiz4 q2 a3'), (17, 0, 'quiz4 q2 a4'),
	(18, 0, 'quiz4 q3 a1'), (18, 0, 'quiz4 q3 a2'), (18, 1, 'quiz4 q3 a3'), (18, 0, 'quiz4 q3 a4'),
	(19, 1, 'quiz4 q4 a1'), (19, 0, 'quiz4 q4 a2'), (19, 0, 'quiz4 q4 a3'), (19, 0, 'quiz4 q4 a4'),
	(20, 0, 'quiz4 q5 a1'), (20, 0, 'quiz4 q5 a2'), (20, 1, 'quiz4 q5 a3'), (20, 0, 'quiz4 q5 a4'),
	(21, 0, 'quiz5 q1 a1'), (21, 1, 'quiz5 q1 a2'), (21, 0, 'quiz5 q1 a3'), (21, 0, 'quiz5 q1 a4'),
	(22, 0, 'quiz5 q2 a1'), (21, 1, 'quiz5 q2 a2'), (22, 0, 'quiz5 q2 a3'), (22, 0, 'quiz5 q2 a4'),
	(23, 0, 'quiz5 q3 a1'), (23, 0, 'quiz5 q3 a2'), (23, 1, 'quiz5 q3 a3'), (23, 0, 'quiz5 q3 a4'),
	(24, 1, 'quiz5 q4 a1'), (24, 0, 'quiz5 q4 a2'), (24, 0, 'quiz5 q4 a3'), (24, 0, 'quiz5 q4 a4'),
	(25, 0, 'quiz5 q5 a1'), (25, 0, 'quiz5 q5 a2'), (25, 1, 'quiz5 q5 a3'), (15, 0, 'quiz3 q5 a4')
GO

CREATE TABLE tbl_quiz_taken
(
	id int IDENTITY(1,1) PRIMARY KEY,
	user_id int NOT NULL CONSTRAINT FK_quiz_taken_by FOREIGN KEY REFERENCES tbl_user(id),
	quiz_id int NOT NULL CONSTRAINT FK_taken_quiz FOREIGN KEY REFERENCES tbl_quiz(id),
	scored smallint NOT NULL CONSTRAINT df_min_score DEFAULT (0),
	created_at smalldatetime NOT NULL CONSTRAINT df_quiz_taken_at DEFAULT getdate()
)

INSERT INTO tbl_quiz_taken(user_id, quiz_id)
VALUES
	(2, 2),
	(2, 5)

CREATE TABLE tbl_answer_taken
(
	id int IDENTITY(1,1) PRIMARY KEY,
	quiz_taken_id int NULL CONSTRAINT FK_quiz_taken_id FOREIGN KEY REFERENCES tbl_quiz_taken(id),
	question_id int NOT NULL CONSTRAINT FK_answer_of FOREIGN KEY REFERENCES tbl_question(id),
	answer_id int NULL CONSTRAINT FK_answer_taken FOREIGN KEY REFERENCES tbl_answer(id),
	created_at smalldatetime NOT NULL CONSTRAINT df_answer_taken_at DEFAULT getdate()
)

INSERT INTO tbl_answer_taken(quiz_taken_id, question_id, answer_id)
VALUES
	(1, 6, 23),
	(1, 7, 25),
	(1, 8, 30),
	(1, 9, 33),
	(1, 10, 39),
	(2, 21, 81),
	(2, 22, 86),
	(2, 23, 90),
	(2, 24, 96),
	(2, 25, 99)