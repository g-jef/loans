DROP TABLE IF EXISTS loan;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS library_member;

CREATE TABLE library_member (
    member_ref VARCHAR(10)  PRIMARY KEY,
    full_name  VARCHAR(60)  NOT NULL
);

CREATE TABLE book (
    id     INT           PRIMARY KEY,
    isbn   VARCHAR(13)   NOT NULL,
    title  VARCHAR(120)  NOT NULL,
    author VARCHAR(60)   NOT NULL
);

CREATE TABLE loan (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    book_id     INT         NOT NULL,
    member_ref  VARCHAR(10) NOT NULL,
    issued_on   DATE        NOT NULL,
    returned_on DATE
);
