-- DROP TABLES FOR CLEANUP
drop table if exists gc_participant;
drop table if exists task_assignee;
drop table if exists task;
drop table if exists group_conversation;
drop table if exists user;
drop table if exists event;

-- CREATE TABLES
create table event
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
);

create table gc_participant
(
    gc_id   bigint not null,
    user_id bigint not null
);
create table group_conversation
(
    id       bigint not null auto_increment,
    title    varchar(255),
    event_id bigint,
    primary key (id)
);
create table task
(
    id          bigint not null auto_increment,
    description varchar(255),
    due_date    datetime(6),
    status      integer,
    title       varchar(255),
    event_id    bigint,
    primary key (id)
);
create table task_assignee
(
    task_id bigint not null,
    user_id bigint not null
);
create table user
(
    id         bigint not null auto_increment,
    first_name varchar(255),
    last_name  varchar(255),
    role       integer,
    username   varchar(255),
    primary key (id)
);

--alter table user drop index UK_sb8bbouer5wak8vyiiy4pf2bx;
--alter table user add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);
--alter table gc_participant add constraint FKhw59ew6fqemleor2r3h8j13n2 foreign key (user_id) references user (id);
--alter table gc_participant add constraint FKlq68n6qsdoyi3w8p00lp0d7e2 foreign key (gc_id) references group_conversation (id);
--alter table group_conversation add constraint FKpvg5j5jsif3kkeegweqy3l8r0 foreign key (event_id) references event (id);
--alter table task add constraint FKql5q2fsknlt9fb8db3j6gly51 foreign key (event_id) references event (id);
--alter table task_assignee add constraint FK8r6t7dv152576fsx6f3407xah foreign key (user_id) references user (id);
--alter table task_assignee add constraint FK5g8rf427mef2ndks3e4w6nhp5 foreign key (task_id) references task (id);

-- ADD VALUES
insert into event (id, name)
values (1, 'Grillen im Park');

insert into group_conversation (id, title, event_id)
values (1, 'Grillen im Park', 1);

insert into user (id, username, first_name, last_name, role)
values (1, 'admin', 'Max', 'Mustermann', 0);
insert into user (id, username, first_name, last_name, role)
values (2, 'Minh', 'Minh', 'Nguyen', 1);
insert into user (id, username, first_name, last_name, role)
values (3, 'Linh', 'Linh', 'Nguyenova', 1);
insert into user (id, username, first_name, last_name, role)
values (4, 'Hung', 'Hung', 'Nguyen', 1);

insert into gc_participant (gc_id, user_id)
values (1, 1);
insert into gc_participant (gc_id, user_id)
values (1, 2);
insert into gc_participant (gc_id, user_id)
values (1, 3);
insert into gc_participant (gc_id, user_id)
values (1, 4);

insert into task (id, description, due_date, status, title, event_id)
values (1, 'Steak, Wurst', '2020-09-24 00:00:00.000000', 0, 'Fleisch kaufen', 1);
insert into task (id, description, due_date, status, title, event_id)
values (2, 'Wassermelone, Trauben', '2020-09-20 00:00:00.000000', 0, 'Obst kaufen', 1);
insert into task (id, description, due_date, status, title, event_id)
values (3, 'Messer, Gabel', '2020-09-22 00:00:00.000000', 1, 'Besteck holen', 1);
insert into task (id, description, due_date, status, title, event_id)
values (4, '', '2020-09-26 00:00:00.000000', 1, 'Decke mitbringen', 1);
insert into task (id, description, due_date, status, title, event_id)
values (5, 'Wasser, Softdrinks', '2020-09-20 00:00:00.000000', 0, 'Getraenke kaufen', 1);
insert into task (id, description, due_date, status, title, event_id)
values (6, '', '2020-09-24 00:00:00.000000', 0, 'Eis kaufen', 1);
insert into task (id, description, due_date, status, title, event_id)
values (7, 'Fussball, Karten', '2020-09-24 00:00:00.000000', 0, 'Spiele mitbringen', 1);
insert into task (id, description, due_date, status, title, event_id)
values (8, 'Platz im Park finden', '2020-09-24 00:00:00.000000', 0, 'Platz organisieren', 1);

insert into task_assignee (task_id, user_id)
values (1, 1);
insert into task_assignee (task_id, user_id)
values (1, 2);
insert into task_assignee (task_id, user_id)
values (2, 3);
insert into task_assignee (task_id, user_id)
values (2, 4);
insert into task_assignee (task_id, user_id)
values (3, 1);
insert into task_assignee (task_id, user_id)
values (4, 2);
insert into task_assignee (task_id, user_id)
values (5, 3);
insert into task_assignee (task_id, user_id)
values (1, 4);