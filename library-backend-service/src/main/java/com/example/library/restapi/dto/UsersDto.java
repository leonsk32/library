package com.example.library.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ユーザのリスト
 */
@ApiModel(description = "ユーザのリスト")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-12-02T23:00:40.669106400+09:00[Asia/Tokyo]")

public class UsersDto   {
  @JsonProperty("users")
  @Valid
  private List<UserDto> users = new ArrayList<>();

  public UsersDto users(List<UserDto> users) {
    this.users = users;
    return this;
  }

  public UsersDto addUsersItem(UserDto usersItem) {
    this.users.add(usersItem);
    return this;
  }

  /**
   * Get users
   * @return users
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<UserDto> getUsers() {
    return users;
  }

  public void setUsers(List<UserDto> users) {
    this.users = users;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsersDto usersDto = (UsersDto) o;
    return Objects.equals(this.users, usersDto.users);
  }

  @Override
  public int hashCode() {
    return Objects.hash(users);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsersDto {\n");
    
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
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

