package com.example.library.domain.ranking;

import lombok.Value;

@Value
public class Ranking {
    private String userId;
    private String name;
    private int num;

    public Ranking(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.num = 1;
    }

    public Ranking(String userId, String name, int num) {
        this.userId = userId;
        this.name = name;
        this.num = num;
    }
}
