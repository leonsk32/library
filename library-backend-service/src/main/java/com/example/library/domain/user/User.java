package com.example.library.domain.user;

import com.example.library.exception.BusinessException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public class User {
    @Getter
    private final String userId;
    @Getter
    private String email;
    @Getter
    private String familyName;
    @Getter
    private String givenName;

    public User(String userId, String email, String familyName, String givenName) {
        if (userId.length() > 7) {
            throw new BusinessException("userIdは７桁");
        }
        this.userId = userId;
        this.email = email;
        this.familyName = familyName;
        this.givenName = givenName;
    }

    public User(String userId, String email) {
        if (userId.length() > 7) {
            throw new BusinessException("userIdは７桁");
        }
        this.userId = userId;
        this.email = email;
    }

    public User(String userId) {
        if (userId.length() > 7) {
            throw new BusinessException("userIdは７桁");
        }
        this.userId = userId;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof User)) throw new BusinessException();
        User otherUser = (User) other;
        return this.userId.equals(otherUser.userId);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getFullName() {
        return StringUtils.trimToEmpty(this.familyName) + StringUtils.trimToEmpty(this.givenName);
    }
}
