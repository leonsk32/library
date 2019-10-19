package com.example.library.domain.user;

public class User {
    private String userId;
    private String simei;
    private String namae;
    private String email;

    public User(String userId, String simei, String namae, String email) {

        this.userId = userId;
        this.simei = simei;
        this.namae = namae;
        this.email = email;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof User)) throw new RuntimeException();
        User otherUser = (User) other;
        return this.userId.equals(otherUser.userId);
    }
}
