package com.unittest.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unittest.demo.models.TestModel;
import com.unittest.demo.service.WelcomeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(IntegrationController.class)
/*
    webMvcTest tells spring boot to start the configurations
*/
class IntegrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /*
    mock the behaviour of the web service
    */
    @MockBean
    private WelcomeService welcomeService;

    /*
    * test get request with dependencies
    * */
    @Test
    void welcome() throws Exception {
        //set service value
        when(welcomeService.getWelcomeMessage("MARTIN")).thenReturn("Welcome MARTIN");

        //call the end point
        mockMvc.perform(
                MockMvcRequestBuilders.get("/welcome-integration?name=MARTIN")
                        .accept(MediaType.TEXT_PLAIN))
                        .andDo(print())
                        .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Welcome MARTIN"));

        //verify that the mock was actually called with MARTIN parameter
        verify(welcomeService).getWelcomeMessage("MARTIN");
    }

    @Test
    void testType() {
    }

    /*
    * test post request and compare result
    * */
    @Test
    void postMessage() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/post-integration")
                        .content(asJsonString(new TestModel("James Njau","Kenyan")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"name\":\"James Njau\",\"nationality\":\"Kenyan\"}"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}