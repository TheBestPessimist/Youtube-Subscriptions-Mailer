create table OAuth2Token
(
    OAuth2Token_ID   INTEGER NOT NULL
        constraint OAuth2Token_pk
            primary key,
    accessToken      text,
    tokenType        text,
    expiresInSeconds integer,
    refreshToken     text             not null,
    scope            text,
    user_id          INTEGER NOT NULL
        constraint OAuth2Token_user_user_id_fk
            references user
) strict ;

create unique index OAuth2Token_OAuth2Token_ID_uindex
    on OAuth2Token (OAuth2Token_ID);
