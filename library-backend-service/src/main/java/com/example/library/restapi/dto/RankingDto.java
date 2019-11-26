package com.example.library.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ランキングを表現する。idは呼び出し元の使い方により、userId等に読み替えられるようにする。
 */
@ApiModel(description = "ランキングを表現する。idは呼び出し元の使い方により、userId等に読み替えられるようにする。")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-11-27T00:15:25.337251400+09:00[Asia/Tokyo]")

public class RankingDto   {
  @JsonProperty("userId")
  private String userId;

  @JsonProperty("name")
  private String name;

  @JsonProperty("num")
  private Integer num;

  public RankingDto userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  @ApiModelProperty(value = "")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public RankingDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RankingDto num(Integer num) {
    this.num = num;
    return this;
  }

  /**
   * Get num
   * @return num
  */
  @ApiModelProperty(value = "")


  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
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
    return Objects.equals(this.userId, rankingDto.userId) &&
        Objects.equals(this.name, rankingDto.name) &&
        Objects.equals(this.num, rankingDto.num);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, name, num);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RankingDto {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

