package com.example.library.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ランキングを表現する。idは呼び出し元の使い方により、userId等に読み替えられるようにする。
 */
@ApiModel(description = "ランキングを表現する。idは呼び出し元の使い方により、userId等に読み替えられるようにする。")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-11-23T22:03:00.345413900+09:00[Asia/Tokyo]")

public class RankingDto   {
  @JsonProperty("rank")
  private String rank;

  @JsonProperty("id")
  private String id;

  @JsonProperty("num")
  private String num;

  public RankingDto rank(String rank) {
    this.rank = rank;
    return this;
  }

  /**
   * Get rank
   * @return rank
  */
  @ApiModelProperty(value = "")


  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public RankingDto id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RankingDto num(String num) {
    this.num = num;
    return this;
  }

  /**
   * Get num
   * @return num
  */
  @ApiModelProperty(value = "")


  public String getNum() {
    return num;
  }

  public void setNum(String num) {
    this.num = num;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RankingDto rankingDto = (RankingDto) o;
    return Objects.equals(this.rank, rankingDto.rank) &&
        Objects.equals(this.id, rankingDto.id) &&
        Objects.equals(this.num, rankingDto.num);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rank, id, num);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RankingDto {\n");
    
    sb.append("    rank: ").append(toIndentedString(rank)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    num: ").append(toIndentedString(num)).append("\n");
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

