-- noinspection SqlAddNotNullColumnForFile

CREATE TABLE user
(
    user_id INTEGER NOT NULL
        CONSTRAINT user_pk
            PRIMARY KEY AUTOINCREMENT,
    email   text    NOT NULL
);


ALTER TABLE user
    ADD name text NOT NULL;

ALTER TABLE user
    ADD google_user_id text NOT NULL;

CREATE UNIQUE INDEX user_google_user_id_uindex
    ON user (google_user_id);
