create table oauth2token
(
    oauth2token_id   INTEGER not null
        constraint oauth2token_pk primary key,
    accessToken      text,
    tokenType        text,
    expiresInSeconds int,
    refreshToken     text    not null,
    scope            text,
    user_id          INTEGER not null
        constraint oauth2token_user_user_id_fk references user (user_id)
) strict;

create unique index oauth2token_oauth2token_id_uindex on oauth2token (oauth2token_id);
create unique index oauth2token_user_id_uindex on oauth2token (user_id);
