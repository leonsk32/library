package com.example.library.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ランキングのリスト
 */
@ApiModel(description = "ランキングのリスト")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-11-23T22:03:00.345413900+09:00[Asia/Tokyo]")

public class RankingsDto   {
  @JsonProperty("rankings")
  @Valid
  private List<RankingDto> rankings = null;

  public RankingsDto rankings(List<RankingDto> rankings) {
    this.rankings = rankings;
    return this;
  }

  public RankingsDto addRankingsItem(RankingDto rankingsItem) {
    if (this.rankings == null) {
      this.rankings = new ArrayList<>();
    }
    this.rankings.add(rankingsItem);
    return this;
  }

  /**
   * Get rankings
   * @return rankings
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<RankingDto> getRankings() {
    return rankings;
  }

  public void setRankings(List<RankingDto> rankings) {
    this.rankings = rankings;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RankingsDto rankingsDto = (RankingsDto) o;
    return Objects.equals(this.rankings, rankingsDto.rankings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rankings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RankingsDto {\n");
    
    sb.append("    rankings: ").append(toIndentedString(rankings)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

