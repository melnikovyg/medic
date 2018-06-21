package com.github.itavgur.controller;

import com.github.itavgur.config.SpringContext;
import com.github.itavgur.config.SpringDb;
import com.github.itavgur.config.SpringMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringContext.class, SpringDb.class, SpringMvc.class}, loader = AnnotationConfigWebContextLoader.class)
@Transactional
@ActiveProfiles("dev")
abstract public class BaseControllerTest {

    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webAppContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
}
