package com.example.library.infra;

import com.example.library.biz.BookDeal.Person;
import com.example.library.biz.BookDeal.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class PersonRepositoryImpl implements PersonRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Override
    public void regist(Person person) {
            jdbcTemplate.execute("insert into person (person_id) values ("+ person.getPersonId()+");" );
    }

    @Override
    public Person find(String personId) {
        List<Map<String, Object>> personRecord = jdbcTemplate.queryForList("select * from person where person_id = \'"+ personId + "\'");
        return personRecord.size() == 0 ? null : new Person((String)personRecord.get(0).get("person_id"));
    }
}
