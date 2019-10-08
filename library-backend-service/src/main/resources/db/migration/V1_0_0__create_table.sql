CREATE TABLE person(
    person_id VARCHAR(7) NOT NULL,
    PRIMARY KEY (person_id)
);

CREATE TABLE book(
    isbn10 VARCHAR(10) NOT NULL,
    PRIMARY KEY (isbn10)
);

CREATE TABLE book_deal(
    id SERIAL NOT NULL,
    person_id VARCHAR(7) NOT NULL REFERENCES person,
    isbn10 VARCHAR(10) NOT NULL REFERENCES book,
    PRIMARY KEY (id)
);



