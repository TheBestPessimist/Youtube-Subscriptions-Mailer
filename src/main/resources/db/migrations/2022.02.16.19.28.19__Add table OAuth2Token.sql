create table OAuth2Token
(
    OAuth2Token_ID   INTEGER not null
        constraint OAuth2Token_pk primary key,
    accessToken      text,
    tokenType        text,
    expiresInSeconds int,
    refreshToken     text    not null,
    scope            text,
    user_id          INTEGER not null
        constraint OAuth2Token_user_user_id_fk references user
) strict;

create unique index OAuth2Token_OAuth2Token_ID_uindex on OAuth2Token (OAuth2Token_ID);
