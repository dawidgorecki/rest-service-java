create table tasks
(
    t_id          int auto_increment
        primary key,
    t_deadline    date         not null,
    t_description varchar(255) not null,
    t_is_done     bit          not null,
    t_name        varchar(255) not null
);