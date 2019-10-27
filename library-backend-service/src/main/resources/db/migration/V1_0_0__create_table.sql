create TABLE BOOK (
      isbn VARCHAR(13) PRIMARY KEY,
      title VARCHAR
  );

create TABLE USERR (
      user_id VARCHAR(7)PRIMARY KEY,
      email VARCHAR NOT NULL,
      simei VARCHAR,
      namae VARCHAR
  );

create TABLE LENDING_RECORD (
      isbn VARCHAR(13) references BOOK(isbn),
      user_id VARCHAR(7) references USERR(user_id),
      PRIMARY KEY(isbn,user_id)
  );