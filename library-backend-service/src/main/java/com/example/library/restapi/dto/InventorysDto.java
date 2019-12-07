package com.example.library.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 棚卸のオブジェクト
 */
@ApiModel(description = "棚卸のオブジェクト")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-12-08T00:26:11.023326300+09:00[Asia/Tokyo]")

public class InventorysDto   {
  @JsonProperty("lendingRecords")
  @Valid
  private List<InventoryDto> lendingRecords = null;

  public InventorysDto lendingRecords(List<InventoryDto> lendingRecords) {
    this.lendingRecords = lendingRecords;
    return this;
  }

  public InventorysDto addLendingRecordsItem(InventoryDto lendingRecordsItem) {
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

  public List<InventoryDto> getLendingRecords() {
    return lendingRecords;
  }

  public void setLendingRecords(List<InventoryDto> lendingRecords) {
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
    InventorysDto inventorysDto = (InventorysDto) o;
    return Objects.equals(this.lendingRecords, inventorysDto.lendingRecords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lendingRecords);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InventorysDto {\n");
    
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

