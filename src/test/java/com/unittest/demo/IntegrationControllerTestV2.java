package com.unittest.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unittest.demo.models.TestModel;
import com.unittest.demo.models.UserResource;
import com.unittest.demo.service.WelcomeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

/*
    webMvcTest tells spring boot to start the configurations
*/
class IntegrationControllerTestV2 {

    @Autowired
    private MockMvc mockMvc;

    /*
    mock the behaviour of the web service
    */
    @MockBean
    private WelcomeService welcomeService;


    @Autowired
    UserRepo userRepo;

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
        mockMvc.perform( post("/post-integration")
                        .content(asJsonString(new TestModel("James Njau","Kenyan")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"name\":\"James Njau\",\"nationality\":\"Kenyan\"}"));
    }

    @Test
    void  testAddUser() throws Exception{

        UserResource userResource = new UserResource(1L,"John Mwai","test@email.com");

        mockMvc.perform(post("http://localhost:8083/add-user")
                        .contentType("application/json")
//                        .param("sendWelcomeMail", "true")
                        .content(asJsonString(userResource)))
                .andExpect(status().isOk());

        UserResource userEntity = userRepo.getByEmail(userResource.getEmail());
        System.out.println(userEntity);
//
        assertThat(userEntity.getEmail()).isEqualTo("test@email.com");
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}