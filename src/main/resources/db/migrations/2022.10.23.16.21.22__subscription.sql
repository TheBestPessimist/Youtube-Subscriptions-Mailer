create table subscription
(
    user_id            integer not null
        constraint subscription_user_user_id_fk references user on delete cascade,
    youtube_channel_id integer not null
        constraint subscription_youtube_channel_youtube_channel_id_fk references youtube_channel on delete cascade,
    constraint subscription_pk primary key (user_id, youtube_channel_id)
) strict;
