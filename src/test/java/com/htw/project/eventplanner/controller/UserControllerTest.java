package com.htw.project.eventplanner.controller;

import com.htw.project.eventplanner.business.UserBusiness;
import com.htw.project.eventplanner.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UserControllerTest extends ControllerTest {

    public static final String BASE_URL = "/users";

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    protected JacksonTester<User> jsonParser;

    @Test
    public void testSuccessJoin() throws Exception {
        // given
        User dbUser = userBusiness.getUser(1l);

        // when
        MockHttpServletResponse response = getResponse(
                post(BASE_URL + "/join")
                        .queryParam("groupChat", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonParser.write(dbUser).getJson())
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String json = response.getContentAsString();
        assertThat(json).isEqualTo(jsonParser.write(dbUser).getJson());
    }

    @Test
    public void testFailNonExistingGC() throws Exception {
        // given
        User dbUser = userBusiness.getUser(1l);

        // when
        MockHttpServletResponse response = getResponse(
                post(BASE_URL + "/join")
                        .queryParam("groupChat", "999999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonParser.write(dbUser).getJson())
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testFailNonExistingUsername() throws Exception {
        // given
        User invalidUser = new User();
        invalidUser.setUsername("Invalid Username");

        // when
        MockHttpServletResponse response = getResponse(
                post(BASE_URL + "/join")
                        .queryParam("groupChat", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonParser.write(invalidUser).getJson())
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}