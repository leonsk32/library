package com.example.library.domain.ranking;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class RankingList {
    private List<Ranking> rankingList;

    public RankingList() {
        this.rankingList = new ArrayList<Ranking>();
    }

    public RankingList(List<Ranking> rankingList) {
        this.rankingList = rankingList;
    }

    public void add(Ranking ranking) {
        this.rankingList.add(ranking);
    }
}
