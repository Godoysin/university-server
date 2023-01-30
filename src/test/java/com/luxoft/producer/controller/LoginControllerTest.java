package com.luxoft.producer.controller;

import com.luxoft.producer.security.jwt.JwtUtils;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class LoginControllerTest {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginTest_ErrorPostWithNoParams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/university/login"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginTest_ErrorPostWithBadCredentials() throws Exception {
        MockPart username = new MockPart("username", "error".getBytes());
        MockPart password = new MockPart("password", "error".getBytes());

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/university/login")
                        .part(username, password))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void loginTest_SuccessfulPost() throws Exception {
        MockPart username = new MockPart("username", "test".getBytes());
        MockPart password = new MockPart("password", "test".getBytes());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/university/login")
                        .part(username, password))
                .andExpect(status().isOk())
                .andReturn();

        Cookie[] cookies = mvcResult.getResponse().getCookies();
        assertEquals(0, cookies.length);

        jwtUtils.validateAccessToken(mvcResult.getRequest());
    }

}
