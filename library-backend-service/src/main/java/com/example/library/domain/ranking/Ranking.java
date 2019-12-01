package com.example.library.domain.ranking;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Ranking {
    private String userId;
    private String name;
    private int num;

}
