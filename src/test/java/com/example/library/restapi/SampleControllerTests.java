package com.example.library.restapi;

import com.example.library.biz.service.SampleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = SampleController.class)
class SampleControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private SampleService sampleService;

    @Test
    void sample() throws Exception {
        doReturn(999).when(sampleService).execute();

        mvc.perform(get("/v1/sample/hoge"))
                .andExpect(content().string("999"));
    }
}
