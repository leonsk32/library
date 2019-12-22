package com.example.library.domain.ranking;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Value
public class RankingList {
    private List<Ranking> rankings;

    public RankingList() {
        this.rankings = new ArrayList<>();
    }

    public RankingList(List<Ranking> rankings) {
        this.rankings = rankings;
    }

    /**
     * ランキングを追加する。
     * 既に追加されているランキングがある場合は、数値を加算する。
     * (SQL側で行ってもいいが、どう集約したいか、という業務知識はどちらかというとJavaに寄せたいため、このようにした。)
     *
     * @param ranking ランキング
     */
    public void add(Ranking ranking) {
        Optional<Ranking> first = rankings.stream().filter(e -> Objects.equals(e.getUserId(), ranking.getUserId())).findFirst();

        if (first.isPresent()) {
            int num = first.get().getNum();
            first.get().setNum(num + 1);
        } else {
            this.rankings.add(ranking);
        }
    }
}
