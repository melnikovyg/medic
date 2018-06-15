package com.github.itavgur.controller;

import com.github.itavgur.TestUtils;
import com.github.itavgur.controller.users.UserAdminRestController;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.github.itavgur.TestDataTemplate.USER_ADMIN;
import static com.github.itavgur.TestDataTemplate.USER_REGISTRATOR;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserAdminRestControllerTest extends BaseControllerTest {

    public final static String REST_URL = UserAdminRestController.REST_URL + "/";

    @Test
    public void getAll() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get(REST_URL);
        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtils.contentJson(USER_ADMIN, USER_REGISTRATOR));
    }

    @Test
    public void getById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get(REST_URL + USER_ADMIN.getId());
        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtils.contentJson(USER_ADMIN));
    }
}