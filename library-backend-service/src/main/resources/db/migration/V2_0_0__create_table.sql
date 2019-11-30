create TABLE LENDING_EVENT (
      isbn VARCHAR(13) references BOOK(isbn),
      user_id VARCHAR(7) references USERR(user_id),
      lending_date timestamp
  );

create TABLE RETURN_EVENT (
      isbn VARCHAR(13) references BOOK(isbn),
      user_id VARCHAR(7) references USERR(user_id),
      return_date timestamp
  );

