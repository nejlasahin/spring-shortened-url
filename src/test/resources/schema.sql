create table users
(
    id bigint auto_increment primary key,
    password varchar(255) null,
    username varchar(255) null,
    constraint UK_USERNAME
        unique (username)
);

create table urls
(
    id bigint auto_increment primary key,
    origin_url varchar(255) null,
    short_url  varchar(255) null,
    user_id    bigint not null,
    constraint FK_USER_ID
        foreign key (user_id) references users (id)
            on delete cascade
);
