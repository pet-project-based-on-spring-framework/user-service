CREATE TABLE usr
(
    id                       BIGINT       NOT NULL AUTO_INCREMENT,
    first_name               VARCHAR(50)  NOT NULL,
    last_name                VARCHAR(50)  NOT NULL,
    username                 VARCHAR(50)  NOT NULL,
    email                    VARCHAR(100) NOT NULL,
    password                 VARCHAR(100) NOT NULL,
    birthday                 DATE,
    created_date             TIMESTAMP    NOT NULL,
    updated_date             TIMESTAMP    NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT UK_user_email UNIQUE (email)
);
