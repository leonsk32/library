package com.example.library.biz.service;

import com.example.library.infra.SampleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class SampleServiceImplTests {

    private SampleService sampleService;

    @Mock
    private SampleRepository sampleRepository;

    @BeforeEach
    void setUp() {
        sampleService = new SampleServiceImpl(sampleRepository);
    }

    @Test
    void execute() {
        doReturn(999).when(sampleRepository).get();
        assertThat(sampleService.execute()).isEqualTo(999);
    }
}
