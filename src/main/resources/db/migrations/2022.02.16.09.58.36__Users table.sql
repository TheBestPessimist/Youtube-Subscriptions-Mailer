create table user
(
    user_id        INTEGER not null
        constraint user_pk primary key autoincrement,
    email          text    not null,
    name           text    not null,
    google_user_id text    not null
) strict;

create unique index user_google_user_id_uindex on user (google_user_id);
