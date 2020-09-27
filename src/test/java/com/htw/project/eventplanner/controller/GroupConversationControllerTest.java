package com.htw.project.eventplanner.controller;

import com.htw.project.eventplanner.business.GroupConversationBusiness;
import com.htw.project.eventplanner.model.Event;
import com.htw.project.eventplanner.model.GroupConversation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class GroupConversationControllerTest extends ControllerTest {

    public static final String BASE_URL = "/gc";

    @Autowired
    private GroupConversationBusiness groupConversationBusiness;

    @Autowired
    protected JacksonTester<GroupConversation> jsonParser;

    @Test
    public void testSuccessGetGC() throws Exception {
        // given

        // when
        MockHttpServletResponse response = getResponse(
                get(BASE_URL + "/1")
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String json = response.getContentAsString();
        assertThat(json).isNotNull();

        GroupConversation object = jsonParser.parseObject(json);
        assertThat(object.getId()).isEqualTo(1);
        assertThat(object.getTitle()).isEqualTo("Grillen im Park");

        Event objectEvent = object.getEvent();
        assertThat(objectEvent).isNotNull();
        assertThat(objectEvent.getId()).isEqualTo(1);
    }

    @Test
    public void testFailGetGC() throws Exception {
        // given

        // when
        MockHttpServletResponse response = getResponse(
                get(BASE_URL + "/9999")
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testSuccessCreateGC() throws Exception {
        // given
        String title = "New Group Conversation";

        GroupConversation gc = new GroupConversation();
        gc.setTitle(title);

        // when
        MockHttpServletResponse response = getResponse(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParser.write(gc).getJson())
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        String json = response.getContentAsString();
        assertThat(json).isNotNull();

        GroupConversation object = jsonParser.parseObject(json);
        assertThat(object.getId()).isEqualTo(2);
        assertThat(object.getTitle()).isEqualTo(title);
        assertThat(object.getEvent()).isNull();
        assertThat(object.getParticipants()).isEmpty();

    }

}