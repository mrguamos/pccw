package com.pccw.backend.modules.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.backend.modules.mail.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;
    @MockBean
    private MailService mailService;

    @Test
    public void shouldResponse2xx() throws Exception {
        var user = User.builder()
                .email("email@gmail.com")
                .username("username")
                .name(Name.builder()
                        .firstName("First Name")
                        .lastName("Last Name")
                        .middleName("Middle Name")
                        .build())
                .build();
        ResultActions response = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON));
        response.andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void shouldRespond400() throws Exception {
        ResultActions response = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(User.builder().build()))
                .contentType(MediaType.APPLICATION_JSON));
        response.andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldRespond422() throws Exception {
        var user = User.builder()
                .email("email@gmail.com")
                .username("username")
                .name(Name.builder()
                        .firstName("First Name")
                        .lastName("Last Name")
                        .middleName("Middle Name")
                        .build())
                .build();
        doThrow(new DataIntegrityViolationException("Duplicate Key")).when(userService).save(any());
        ResultActions response = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON));
        response.andDo(print())
                .andExpect(status().is4xxClientError());
    }

}
