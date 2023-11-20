create table member
(
    MEMBER_ID          bigint auto_increment comment '유저 ID'
        primary key,
    LOGIN_ID         varchar(255) not null comment '유저 로그인 아이디',
    oauth_login_type varchar(255) not null,
    NAME             varchar(255) not null comment '유저이름'
);