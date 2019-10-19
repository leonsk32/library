package com.example.library.domain;

import com.example.library.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    @DisplayName("同一性のテスト")
    @Test
    void test01() {
        User user1 = new User("id001","mizukami", "hiroto", "email");
        User user2 = new User("id001","mizukami", "hiroto", "email");
        assertThat(user1).isEqualTo(user2);
    }
}
