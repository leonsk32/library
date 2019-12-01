package com.example.library.restapi;

import com.example.library.app_service.UserService;
import com.example.library.domain.ranking.Ranking;
import com.example.library.restapi.dto.RankingDto;
import com.example.library.restapi.dto.RankingsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class RankingApiImpl implements RankingApi {
    private final UserService service;

    @Override
    public ResponseEntity<RankingsDto> rankingBooksGet() {
        List<Ranking> rankings = service.searchLentRanking();
        return new ResponseEntity<>(convert(rankings), NOT_IMPLEMENTED);
    }

    private RankingsDto convert(List<Ranking> rankings) {
        RankingsDto rankingsDto = new RankingsDto();
        for (Ranking ranking : rankings) {
            RankingDto rankingDto = new RankingDto();
            rankingDto.setUserId(ranking.getUserId());
            rankingDto.setName(ranking.getName());
            rankingDto.setNum(ranking.getNum());
            rankingsDto.addRankingsItem(rankingDto);
        }
        return rankingsDto;
    }
}
