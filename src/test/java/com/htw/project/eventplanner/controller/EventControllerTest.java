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

public class EventControllerTest extends ControllerTest {

    public static final String BASE_URL = "/events";

    @Autowired
    private GroupConversationBusiness groupConversationBusiness;

    @Autowired
    protected JacksonTester<Event> jsonParser;

    @Test
    public void testSuccessCreateEvent() throws Exception {
        // given
        // create new group conversation for new event
        GroupConversation gc = new GroupConversation();
        gc.setTitle("Test Group Conversation");
        groupConversationBusiness.save(gc);

        String newEventName = "New Event";

        Event newEvent = new Event();
        newEvent.setName(newEventName);

        // when
        MockHttpServletResponse response = getResponse(
                post(BASE_URL + "/new")
                        .queryParam("groupChat", gc.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonParser.write(newEvent).getJson())
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        String json = response.getContentAsString();
        assertThat(json).isNotNull();

        Event event = jsonParser.parseObject(json);
        assertThat(event.getId()).isEqualTo(2);
        assertThat(event.getName()).isEqualTo(newEventName);
        assertThat(event.getTasks().size()).isEqualTo(newEvent.getTasks().size());
    }

    @Test
    public void testFailCreateEventForNonExistingGC() throws Exception {
        // given
        String newEventName = "New Event";

        Event newEvent = new Event();
        newEvent.setName(newEventName);

        // when
        MockHttpServletResponse response = getResponse(
                post(BASE_URL + "/new")
                        .queryParam("groupChat", "9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonParser.write(newEvent).getJson())
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testSuccessGetEvent() throws Exception {
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

        Event event = jsonParser.parseObject(json);
        assertThat(event.getId()).isEqualTo(1);
        assertThat(event.getName()).isEqualTo("Grillen im Park");
        assertThat(event.getTasks().size()).isEqualTo(8);
    }

    @Test
    public void testFailGetNonExistingEvent() throws Exception {
        // given

        // when
        MockHttpServletResponse response = getResponse(
                get(BASE_URL + "/9999")
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

}