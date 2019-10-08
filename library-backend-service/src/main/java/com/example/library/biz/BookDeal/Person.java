package com.example.library.biz.BookDeal;

import lombok.Getter;

public class Person {
    @Getter
    private String personId;

    /**
     * @param personId
     */
    public Person(String personId) {
        this.personId = personId;
    }
}
