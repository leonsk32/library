package com.example.library.domain;

import com.example.library.domain.SampleService;
import com.example.library.infra.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {
    private final SampleRepository repository;

    @Override
    public int execute() {
        return repository.get();
    }
}