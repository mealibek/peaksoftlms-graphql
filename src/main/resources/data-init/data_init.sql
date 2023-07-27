insert into users(created_at, id, modified_at, first_name, gender, last_name)
values (now(), 1, now(), 'Admin', 'MALE', 'Adminov'),
       (now(), 2, now(), 'Instructor1', 'MALE', 'Instructorov1'),
       (now(), 3, now(), 'Instructor2', 'MALE', 'Instructorov2'),
       (now(), 4, now(), 'Nuradil', 'MALE', 'Djoldoshov'),
       (now(), 5, now(), 'Ilim', 'MALE', 'Shabdanov'),
       (now(), 6, now(), 'Alibek', 'MALE', 'Altynbekov'),
       (now(), 7, now(), 'Iskhak', 'MALE', 'Abdukhamitov');

insert into files(id, file_type, url)
values (1, 'IMAGE', ' file image url 1'),
       (2, 'IMAGE', ' file image url 2'),
       (3, 'FILE', ' file pdf url 1'),
       (4, 'FILE', ' file pdf url 2'),
       (5, 'VIDEO', ' file video url 1'),
       (6, 'VIDEO', ' file video url 2'),
       (7, 'IMAGE', ' file image url 3'),
       (8, 'IMAGE', ' file image url 4'),
       (9, 'FILE', ' file pdf url 3'),
       (10, 'FILE', ' file pdf url 4'),
       (11, 'IMAGE', ' image course 11'),
       (12, 'IMAGE', ' image course 12'),
       (13, 'VIDEO', ' file video url 13'),
       (14, 'VIDEO', ' file video url 14');


insert into courses(finish_date, start_date, created_at, id, modified_at, description, name, file_id)
values ('2023-10-10', now(), now(), 1, now(), 'Java course', 'Java', 11),
       ('2023-10-10', now(), now(), 2, now(), 'JavaScript course', 'JS', 12);

insert into groups(finish_date, start_date, course_id, created_at, file_id, id, modified_at, description, name)
values ('2023-12-10', now(), 1, now(), 1, 1, now(), 'java 8 best', 'java-8'),
       ('2023-12-10', now(), 1, now(), 2, 2, now(), 'java 9 best', 'java-9'),
       ('2023-12-10', now(), 2, now(), 7, 3, now(), 'javaScript 8 best', 'java-9'),
       ('2023-12-10', now(), 2, now(), 8, 4, now(), 'javaScript 9 best', 'java-9');


insert into accounts(course_id, created_at, id, modified_at, student_group_id, user_id, email, password, phone_number,
                     reset_password_token, role, study_format)
values (NULL, now(), 1, now(), NULL, 1, 'admin@gmail.com',
        '$2a$12$tkAfUKQ6HFGD8E.gFtI0C./lxCOrE5p2qFZoSwWbv5psYiO0a3i4W', '+996321654987', NULL, 'ADMIN',
        NULL),                                        --> Admin123
       (1, now(), 2, now(), NULL, 2, 'aijamal@gmail.com',
        '$2a$12$n1qSRtu489GIvBeKV/A/XekXrLbR4M6G92ganL5ofthn7lHe3wWhS', '+996123456789', NULL, 'INSTRUCTOR',
        'ONLINE'),                                        --> Aijamal123
       (2, now(), 3, now(), NULL, 3, 'mukhammed2@gmail.com',
        '$2a$12$/333uTN7T7GYKwfnf3XVgOfl8Y65QLPAD42PqBwg0UXcyANHuuUJ.', '+996321456789', NULL, 'INSTRUCTOR',
        'OFFLINE'),                                        --> Mukhammed123
       (1, now(), 4, now(), 1, 4, 'nurik@gmail.com', '$2a$12$N/EEH3DmLu.S7t0SXM95Ce9pO7c3FDSwt.HWobetrfOSmvwK8cMT2',
        '+996543876678', NULL, 'STUDENT', 'OFFLINE'), --> Nurik123
       (1, now(), 5, now(), 2, 5, 'ilim@gmail.com', '$2a$12$F4i1HCVS3ibQwJCqRiXoQehT9CMZRkjzD.wQrvIeJiDXizpufIBLa',
        '+996783876678', NULL, 'STUDENT', 'ONLINE'),  --> Ilimbek123
       (2, now(), 6, now(), 3, 6, 'alibek@gmail.com', '$2a$12$uRuAd/XGzEAC2EFGMzge3OB8ezl8VW4FY42Uqa8f3XLgyxO725tD6',
        '+996783876678', NULL, 'STUDENT', 'OFFLINE'), --> Alibek123
       (2, now(), 7, now(), 4, 7, 'iskhak@gmail.com', '$2a$12$yMlZGEqKNlHGkmjJMdcP.OYm53LaEd08g6/Wpn4GR2RjVEu0K1y7q',
        '+996883876678', NULL, 'STUDENT', 'ONLINE'); --> Iskhak123

insert into instructors_groups(groups_id, instructors_id)
values (1, 2),
       (2, 2),
       (3, 3),
       (4, 3);

insert into tests(created_at, id, modified_at, name)
values (now(), 1, now(), 'spring core test'),
       (now(), 2, now(), 'stream api test'),
       (now(), 3, now(), 'react and js'),
       (now(), 4, now(), 'html and css');


insert into questions(id, test_id, name, option_type)
values (1, 1, 'What is Spring', 'SINGLE'),
       (2, 1, 'What is Docker', 'MULTIPLE'),
       (3, 2, 'What is OOP', 'SINGLE'),
       (4, 2, 'What is Stream API', 'MULTIPLE'),
       (5, 3, 'What is React', 'SINGLE'),
       (6, 3, 'What is JS', 'MULTIPLE'),
       (7, 4, 'What is html', 'SINGLE'),
       (8, 4, 'What is css', 'MULTIPLE');

insert into options(is_correct, id, question_id, name)
values (false, 1, 1, 'library'),
       (true, 2, 1, 'frame work'),
       (true, 3, 2, 'KIT'),
       (true, 4, 2, 'Container'),
       (false, 5, 3, 'i don"t now'),
       (true, 6, 3, 'Poradigma'),
       (true, 7, 4, 'Stream'),
       (true, 8, 4, 'Filter'),
       (false, 9, 5, 'I dont now'),
       (true, 10, 5, 'React'),
       (true, 11, 6, 'JS'),
       (true, 12, 6, 'Language'),
       (false, 13, 7, 'I dont now '),
       (true, 14, 7, 'html'),
       (true, 15, 8, 'css'),
       (true, 16, 8, 'CSS');

insert into lessons(created_at, group_id, id, modified_at, test_id, name)
values (now(), 1, 1, now(), 1, 'spring core'),
       (now(), 2, 2, now(), 2, 'stream api'),
       (now(), 3, 3, now(), 3, 'react and js'),
       (now(), 4, 4, now(), 4, 'html and css');

insert into presentations(id, lesson_id, description, formatppt, name)
values (1, 1, 'spring core presentation', 'url', 'presentation 1'),
       (2, 2, 'stream api presentation', 'url', 'presentation 2'),
       (3, 3, 'react and js presentation', 'url', 'presentation 3'),
       (4, 4, 'html and css presentation', 'url', 'presentation 4');

insert into tasks(dead_line, created_at, id, lesson_id, modified_at, description, name)
values ('2023-12-10', now(),  1, 1, now(), 'spring task', 'spring'),
       ('2023-12-10', now(),  2, 2, now(), 'stream task', 'stream'),
       ('2023-12-10', now(), 3, 3, now(), 'react task', 'react'),
       ('2023-12-10', now(), 4, 4, now(), 'html task', 'html');


insert into results(count_correct, count_in_correct, account_id, id, test_id)
values (2, 0, 4, 1, 1),
       (2, 0, 5, 3, 2),
       (2, 0, 6, 4, 3),
       (2, 0, 7, 5, 4);

insert into video_lessons(file_id, id, lesson_id, description, name)
values (5, 1, 1, 'description 1', 'video lesson 1'),
       (6, 2, 2, 'description 1', 'video lesson 2'),
       (13, 3, 3, 'description 1', 'video lesson 3'),
       (14, 4, 4, 'description 2', 'video lesson 4');

