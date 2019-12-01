package com.example.library.restapi;

import com.example.library.app_service.UserService;
import com.example.library.domain.ranking.Ranking;
import com.example.library.domain.ranking.RankingList;
import com.example.library.restapi.dto.RankingDto;
import com.example.library.restapi.dto.RankingsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class RankingApiImpl implements RankingApi {
    private final UserService service;

    @CrossOrigin
    @Override
    public ResponseEntity<RankingsDto> rankingBooksGet() {
        RankingList rankings = service.searchLentRanking();
        return new ResponseEntity<>(convert(rankings), OK);
    }

    private RankingsDto convert(RankingList rankings) {
        RankingsDto rankingsDto = new RankingsDto();
        for (Ranking ranking : rankings.getRankingList()) {
            RankingDto rankingDto = new RankingDto();
            rankingDto.setUserId(ranking.getUserId());
            rankingDto.setName(ranking.getName());
            rankingDto.setNum(ranking.getNum());
            rankingsDto.addRankingsItem(rankingDto);
        }
        return rankingsDto;
    }
}
