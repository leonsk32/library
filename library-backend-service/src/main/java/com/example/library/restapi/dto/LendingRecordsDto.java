package com.example.library.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 貸出帳のオブジェクト
 */
@ApiModel(description = "貸出帳のオブジェクト")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-11-11T00:41:00.353393300+09:00[Asia/Tokyo]")

public class LendingRecordsDto   {
  @JsonProperty("lendingRecords")
  @Valid
  private List<LendingRecordDto> lendingRecords = null;

  public LendingRecordsDto lendingRecords(List<LendingRecordDto> lendingRecords) {
    this.lendingRecords = lendingRecords;
    return this;
  }

  public LendingRecordsDto addLendingRecordsItem(LendingRecordDto lendingRecordsItem) {
    if (this.lendingRecords == null) {
      this.lendingRecords = new ArrayList<>();
    }
    this.lendingRecords.add(lendingRecordsItem);
    return this;
  }

  /**
   * Get lendingRecords
   * @return lendingRecords
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<LendingRecordDto> getLendingRecords() {
    return lendingRecords;
  }

  public void setLendingRecords(List<LendingRecordDto> lendingRecords) {
    this.lendingRecords = lendingRecords;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LendingRecordsDto lendingRecordsDto = (LendingRecordsDto) o;
    return Objects.equals(this.lendingRecords, lendingRecordsDto.lendingRecords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lendingRecords);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LendingRecordsDto {\n");
    
    sb.append("    lendingRecords: ").append(toIndentedString(lendingRecords)).append("\n");
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

