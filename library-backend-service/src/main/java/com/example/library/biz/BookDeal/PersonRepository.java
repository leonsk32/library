package com.example.library.biz.BookDeal;

public interface PersonRepository {
    void regist(Person person);
    Person find(String personId);
}
