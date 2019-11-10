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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-11-11T00:31:00.882741700+09:00[Asia/Tokyo]")

public class LendingRecordsDto   {
  @JsonProperty("lendingRecordsDto")
  @Valid
  private List<LendingRecordDto> lendingRecordsDto = null;

  public LendingRecordsDto lendingRecordsDto(List<LendingRecordDto> lendingRecordsDto) {
    this.lendingRecordsDto = lendingRecordsDto;
    return this;
  }

  public LendingRecordsDto addLendingRecordsDtoItem(LendingRecordDto lendingRecordsDtoItem) {
    if (this.lendingRecordsDto == null) {
      this.lendingRecordsDto = new ArrayList<>();
    }
    this.lendingRecordsDto.add(lendingRecordsDtoItem);
    return this;
  }

  /**
   * Get lendingRecordsDto
   * @return lendingRecordsDto
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<LendingRecordDto> getLendingRecordsDto() {
    return lendingRecordsDto;
  }

  public void setLendingRecordsDto(List<LendingRecordDto> lendingRecordsDto) {
    this.lendingRecordsDto = lendingRecordsDto;
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
    return Objects.equals(this.lendingRecordsDto, lendingRecordsDto.lendingRecordsDto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lendingRecordsDto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LendingRecordsDto {\n");
    
    sb.append("    lendingRecordsDto: ").append(toIndentedString(lendingRecordsDto)).append("\n");
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

