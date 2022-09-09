package com.unittest.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // to invoke the spring context
@AutoConfigureMockMvc //to invoke mockmvc
class AcceptanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void welcome() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/acceptance-integration")
                                .accept(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Welcome Stranger"));

    }
}