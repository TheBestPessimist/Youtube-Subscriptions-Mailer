create table main.youtube_channel
(
    youtube_channel_id integer not null
        constraint youtube_channel_pk primary key autoincrement,
    channel_id         TEXT    not null,
    title              text    not null
) strict;

create unique index youtube_channel_channel_id_uindex on youtube_channel (channel_id);
