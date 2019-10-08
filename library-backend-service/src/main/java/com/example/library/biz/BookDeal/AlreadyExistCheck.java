package com.example.library.biz.BookDeal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ドメインサービスのつもり
 */
@Component
@RequiredArgsConstructor
public class AlreadyExistCheck {
    private final PersonRepository personRepository;

    public boolean isAlreadyExist(String personId) {
        return personRepository.find(personId) != null;
    }
}
