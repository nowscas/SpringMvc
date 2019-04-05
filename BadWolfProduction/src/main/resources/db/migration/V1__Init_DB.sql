create sequence hibernate_sequence start 1 increment 1;

create table audio_track (
    id int8 not null,
    filename varchar(255),
    new_track boolean not null,
    track_description varchar(255),
    track_singer varchar(255),
    primary key (id)
);

create table faq_post (
    id int8 not null,
    answer varchar(2048),
    question varchar(2048),
    primary key (id)
);

create table main_page_post (
    id int8 not null,
    filename varchar(255),
    post_body varchar(2048),
    post_header varchar(255),
    youtube_link varchar(255),
    user_id int8,
    primary key (id)
);

create table price_post (
    id int8 not null,
    filename varchar(255),
    genre varchar(255),
    price varchar(255),
    primary key (id)
);

    create table stock (
    id int8 not null,
    stock_body varchar(2048),
    stock_header varchar(255),
    primary key (id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
    id int8 not null,
    active boolean not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);

alter table if exists main_page_post
    add constraint main_page_post_fk
    foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_fk
    foreign key (user_id) references usr;