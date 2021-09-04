package com.gmail.elbaglikov.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value= {"/spring/spring-db.xml", "/spring/spring-app.xml", "/spring/spring-mvc.xml"})
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getXMLUsers() throws Exception {
        MvcResult mvcResult = this.mvc.perform(get("/users").header("Accept","application/xml"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals("application/xml",
                mvcResult.getResponse().getContentType());
    }

    @Test
    public void getJsonUsers() throws Exception {
        MvcResult mvcResult = this.mvc.perform(get("/users").header("Accept","application/json"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }
}