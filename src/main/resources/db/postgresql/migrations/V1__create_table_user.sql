CREATE TABLE usr
(
    id           BIGINT       NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name   VARCHAR(50)  NOT NULL,
    last_name    VARCHAR(50)  NOT NULL,
    username     VARCHAR(50)  NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    password     VARCHAR(100) NOT NULL,
    birthday     DATE,
    created_date TIMESTAMP    NOT NULL,
    updated_date TIMESTAMP    NOT NULL
);
