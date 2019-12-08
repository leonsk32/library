package com.example.library.domain.lending;

import com.example.library.infra.dto.ReturnEvent;

import java.util.List;

public interface ReturnEventRepository {
    List<ReturnEvent> findAll();

    List<ReturnEvent> find(String isbn, String userId);
}
