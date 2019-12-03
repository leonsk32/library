package com.example.library.domain.ranking;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Ranking {
    private String userId;
    private String name;
    @Setter
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
