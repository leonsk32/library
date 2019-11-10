package com.example.library.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * 貸出帳の検索結果オブジェクト
 */
@ApiModel(description = "貸出帳の検索結果オブジェクト")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-11-11T00:34:29.268640+09:00[Asia/Tokyo]")

public class LendingRecordDto   {
  @JsonProperty("isbn")
  private String isbn;

  @JsonProperty("userId")
  private String userId;

  @JsonProperty("namae")
  private String namae;

  @JsonProperty("simei")
  private String simei;

  public LendingRecordDto isbn(String isbn) {
    this.isbn = isbn;
    return this;
  }

  /**
   * Get isbn
   * @return isbn
  */
  @ApiModelProperty(value = "")


  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public LendingRecordDto userId(String userId) {
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

  public LendingRecordDto namae(String namae) {
    this.namae = namae;
    return this;
  }

  /**
   * Get namae
   * @return namae
  */
  @ApiModelProperty(value = "")


  public String getNamae() {
    return namae;
  }

  public void setNamae(String namae) {
    this.namae = namae;
  }

  public LendingRecordDto simei(String simei) {
    this.simei = simei;
    return this;
  }

  /**
   * Get simei
   * @return simei
  */
  @ApiModelProperty(value = "")


  public String getSimei() {
    return simei;
  }

  public void setSimei(String simei) {
    this.simei = simei;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LendingRecordDto lendingRecordDto = (LendingRecordDto) o;
    return Objects.equals(this.isbn, lendingRecordDto.isbn) &&
        Objects.equals(this.userId, lendingRecordDto.userId) &&
        Objects.equals(this.namae, lendingRecordDto.namae) &&
        Objects.equals(this.simei, lendingRecordDto.simei);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isbn, userId, namae, simei);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LendingRecordDto {\n");
    
    sb.append("    isbn: ").append(toIndentedString(isbn)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    namae: ").append(toIndentedString(namae)).append("\n");
    sb.append("    simei: ").append(toIndentedString(simei)).append("\n");
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

