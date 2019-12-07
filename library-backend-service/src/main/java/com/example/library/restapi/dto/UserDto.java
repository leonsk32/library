package com.example.library.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * UserDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-12-02T23:00:40.669106400+09:00[Asia/Tokyo]")

public class UserDto {
    @NotNull
    @JsonProperty("userId")
    private String userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("familyName")
    private String familyName;

    @JsonProperty("givenName")
    private String givenName;

    public UserDto userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Get userId
     *
     * @return userId
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserDto email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Get email
     *
     * @return email
     */
    @ApiModelProperty(value = "")


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDto familyName(String familyName) {
        this.familyName = familyName;
        return this;
    }

    /**
     * Get familyName
     *
     * @return familyName
     */
    @ApiModelProperty(value = "")


    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public UserDto givenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    /**
     * Get givenName
     *
     * @return givenName
     */
    @ApiModelProperty(value = "")


    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(this.userId, userDto.userId) &&
                Objects.equals(this.email, userDto.email) &&
                Objects.equals(this.familyName, userDto.familyName) &&
                Objects.equals(this.givenName, userDto.givenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, familyName, givenName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserDto {\n");

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    familyName: ").append(toIndentedString(familyName)).append("\n");
        sb.append("    givenName: ").append(toIndentedString(givenName)).append("\n");
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

