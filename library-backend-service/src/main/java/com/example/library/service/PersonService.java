package com.example.library.service;

import com.example.library.biz.BookDeal.AlreadyExistCheck;
import com.example.library.biz.BookDeal.Person;
import com.example.library.biz.BookDeal.PersonRepository;
import com.example.library.restapi.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final AlreadyExistCheck alreadyExistCheck;
    private final PersonRepository personRepository;
    public void regist(PersonDto personDto) {
        // TODO すでに登録されているか、どうかをパーソンが知るべきかどうか
        if(!alreadyExistCheck.isAlreadyExist(personDto.getPersonId())) {
            Person person = new Person(personDto.getPersonId());
            personRepository.regist(person);
        }
    }
}
