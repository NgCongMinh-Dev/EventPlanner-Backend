insert into event (id, name) values (1, 'Grillen im Park');

insert into group_conversation (id, title, event_id) values (1, 'Grillen im Park', 1);

insert into user (id, username, first_name, last_name, role) values (1, 'admin', 'Max', 'Mustermann', 0);
insert into user (id, username, first_name, last_name, role) values (2, 'Minh', 'Minh', 'Nguyen', 1);
insert into user (id, username, first_name, last_name, role) values (3, 'Linh', 'Linh', 'Nguyenova', 1);
insert into user (id, username, first_name, last_name, role) values (4, 'Hung', 'Hung', 'Nguyen', 1);

insert into gc_participant (gc_id, user_id) values (1, 1);
insert into gc_participant (gc_id, user_id) values (1, 2);
insert into gc_participant (gc_id, user_id) values (1, 3);
insert into gc_participant (gc_id, user_id) values (1, 4);

insert into task (id, description, due_date, status, title, event_id) values (1, 'Steak, Wurst', '2020-09-24 00:00:00.000000', 0, 'Fleisch kaufen', 1);
insert into task (id, description, due_date, status, title, event_id) values (2, 'Wassermelone, Trauben', '2020-09-20 00:00:00.000000', 0, 'Obst kaufen', 1);
insert into task (id, description, due_date, status, title, event_id) values (3, 'Messer, Gabel', '2020-09-22 00:00:00.000000', 1, 'Besteck holen', 1);
insert into task (id, description, due_date, status, title, event_id) values (4, '', '2020-09-26 00:00:00.000000', 1, 'Decke mitbringen', 1);
insert into task (id, description, due_date, status, title, event_id) values (5, 'Wasser, Softdrinks', '2020-09-20 00:00:00.000000', 0, 'Getraenke kaufen', 1);

insert into task_assignee (task_id, user_id) values (1, 1);
insert into task_assignee (task_id, user_id) values (1, 2);
insert into task_assignee (task_id, user_id) values (2, 3);
insert into task_assignee (task_id, user_id) values (2, 4);
insert into task_assignee (task_id, user_id) values (3, 1);
insert into task_assignee (task_id, user_id) values (4, 2);
insert into task_assignee (task_id, user_id) values (5, 3);
insert into task_assignee (task_id, user_id) values (1, 4);