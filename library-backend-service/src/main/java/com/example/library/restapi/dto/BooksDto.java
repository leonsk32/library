package com.example.library.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 本の検索結果オブジェクト
 */
@ApiModel(description = "本の検索結果オブジェクト")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-12-04T00:14:22.876272900+09:00[Asia/Tokyo]")

public class BooksDto   {
  @JsonProperty("isbns")
  @Valid
  private List<String> isbns = null;

  public BooksDto isbns(List<String> isbns) {
    this.isbns = isbns;
    return this;
  }

  public BooksDto addIsbnsItem(String isbnsItem) {
    if (this.isbns == null) {
      this.isbns = new ArrayList<>();
    }
    this.isbns.add(isbnsItem);
    return this;
  }

  /**
   * Get isbns
   * @return isbns
  */
  @ApiModelProperty(value = "")


  public List<String> getIsbns() {
    return isbns;
  }

  public void setIsbns(List<String> isbns) {
    this.isbns = isbns;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BooksDto booksDto = (BooksDto) o;
    return Objects.equals(this.isbns, booksDto.isbns);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isbns);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BooksDto {\n");
    
    sb.append("    isbns: ").append(toIndentedString(isbns)).append("\n");
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

