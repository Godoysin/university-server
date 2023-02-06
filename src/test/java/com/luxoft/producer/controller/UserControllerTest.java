package com.luxoft.producer.controller;

import com.luxoft.producer.security.constants.SecurityConstants;
import com.luxoft.producer.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class UserControllerTest {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUserNoTokenTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/university/users/new-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("unneeded"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void createUserNotNullIdTest() throws Exception {
        String content = getResourceFromTestResource("json/user_role.json");

        JSONObject jsonObject = new JSONObject(content);
        JSONObject jsonNameObject = jsonObject.getJSONObject("user");
        jsonNameObject.put("id", 1L);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/university/users/new-user")
                        .header(SecurityConstants.JWT_HEADER, jwtUtils.generateAccessToken("test"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isBadRequest());
    }

    private String getResourceFromTestResource(String resource) throws IOException {
        String testPath = "src/test/resources/";
        return Files.readString(Paths.get(testPath + resource));
    }

}
