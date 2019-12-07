package com.example.library.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 棚卸のためのDto。本のISBNと現在棚卸を終わった数、棚卸対象の数、どのバージョンの棚卸結果か、あとメモ。
 */
@ApiModel(description = "棚卸のためのDto。本のISBNと現在棚卸を終わった数、棚卸対象の数、どのバージョンの棚卸結果か、あとメモ。")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-12-08T00:26:11.023326300+09:00[Asia/Tokyo]")

public class InventoryDto   {
  @JsonProperty("isbn")
  private String isbn;

  @JsonProperty("num")
  private Integer num;

  @JsonProperty("maxNum")
  private Integer maxNum;

  @JsonProperty("version")
  private Integer version;

  @JsonProperty("memo")
  private String memo;

  public InventoryDto isbn(String isbn) {
    this.isbn = isbn;
    return this;
  }

  /**
   * Get isbn
   * @return isbn
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public InventoryDto num(Integer num) {
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

  public InventoryDto maxNum(Integer maxNum) {
    this.maxNum = maxNum;
    return this;
  }

  /**
   * Get maxNum
   * @return maxNum
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getMaxNum() {
    return maxNum;
  }

  public void setMaxNum(Integer maxNum) {
    this.maxNum = maxNum;
  }

  public InventoryDto version(Integer version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * @return version
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public InventoryDto memo(String memo) {
    this.memo = memo;
    return this;
  }

  /**
   * Get memo
   * @return memo
  */
  @ApiModelProperty(value = "")


  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InventoryDto inventoryDto = (InventoryDto) o;
    return Objects.equals(this.isbn, inventoryDto.isbn) &&
        Objects.equals(this.num, inventoryDto.num) &&
        Objects.equals(this.maxNum, inventoryDto.maxNum) &&
        Objects.equals(this.version, inventoryDto.version) &&
        Objects.equals(this.memo, inventoryDto.memo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isbn, num, maxNum, version, memo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InventoryDto {\n");
    
    sb.append("    isbn: ").append(toIndentedString(isbn)).append("\n");
    sb.append("    num: ").append(toIndentedString(num)).append("\n");
    sb.append("    maxNum: ").append(toIndentedString(maxNum)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    memo: ").append(toIndentedString(memo)).append("\n");
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

