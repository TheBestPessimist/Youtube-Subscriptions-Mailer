CREATE TABLE dummy_table
(
    id   INT
        CONSTRAINT table_name_pk
            PRIMARY KEY,
    name text
);

INSERT INTO dummy_table (id, name) VALUES (1, 'TheBestPessimist');
INSERT INTO dummy_table (id, name) VALUES (2, 'Cristi');
INSERT INTO dummy_table (id, name) VALUES (3, 'Viorel');
