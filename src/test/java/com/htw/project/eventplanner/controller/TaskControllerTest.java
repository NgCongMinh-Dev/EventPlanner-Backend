package com.htw.project.eventplanner.controller;

import com.htw.project.eventplanner.business.TaskBusiness;
import com.htw.project.eventplanner.model.Event;
import com.htw.project.eventplanner.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class TaskControllerTest extends ControllerTest {

    public static final String BASE_URL = "/tasks";

    @Autowired
    private TaskBusiness taskBusiness;

    @Autowired
    protected JacksonTester<Task> jsonParser;

    private Date getDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
        return formatter.parse(date);
    }

    @Test
    public void testSuccessCreateTask() throws Exception {
        // given
        String title = "Title";
        String description = "Description";
        Date date = getDate("01.01.2020");

        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setDescription(description);
        newTask.setStatus(Task.Status.PENDING);
        newTask.setDueDate(date);

        // when
        MockHttpServletResponse response = getResponse(
                post(BASE_URL)
                        .queryParam("event", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParser.write(newTask).getJson())
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        String json = response.getContentAsString();
        assertThat(json).isNotNull();

        Task object = jsonParser.parseObject(json);
        assertThat(object.getId()).isEqualTo(9);
        assertThat(object.getTitle()).isEqualTo(title);
        assertThat(object.getDescription()).isEqualTo(description);
        assertThat(object.getStatus()).isEqualTo(Task.Status.PENDING);
        // there is a problem with the time zone of the web server
        assertThat(object.getDueDate()).isNotNull();

        Event objectEvent = object.getEvent();
        assertThat(objectEvent).isNull();
    }

    @Test
    public void testFailCreateTaskNonExistingEvent() throws Exception {
        // given
        String title = "Title";
        String description = "Description";
        Date date = getDate("01.01.2020");

        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setDescription(description);
        newTask.setStatus(Task.Status.PENDING);
        newTask.setDueDate(date);

        // when
        MockHttpServletResponse response = getResponse(
                post(BASE_URL)
                        .queryParam("event", "9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParser.write(newTask).getJson())
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testSuccessGetTask() throws Exception {
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

        Task object = jsonParser.parseObject(json);
        assertThat(object.getId()).isEqualTo(1);
        assertThat(object.getTitle()).isEqualTo("Fleisch kaufen");
        assertThat(object.getDescription()).isEqualTo("Steak, Wurst");
        assertThat(object.getStatus()).isEqualTo(Task.Status.PENDING);
        assertThat(object.getDueDate()).isNotNull();

        Event objectEvent = object.getEvent();
        assertThat(objectEvent).isNull();
    }

    @Test
    public void testFailGetNonExistingTask() throws Exception {
        // given

        // when
        MockHttpServletResponse response = getResponse(
                get(BASE_URL + "/99999")
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testSuccessUpdateTask() throws Exception {
        // given
        String title = "New Title";
        String description = "New Description";
        Date date = getDate("01.01.2020");

        Task updatedTask = taskBusiness.getTaskById(Long.valueOf(1));
        updatedTask.setTitle(title);
        updatedTask.setDescription(description);
        updatedTask.setStatus(Task.Status.FINISHED);
        updatedTask.setDueDate(date);

        // when
        MockHttpServletResponse response = getResponse(
                put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParser.write(updatedTask).getJson())
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String json = response.getContentAsString();
        assertThat(json).isNotNull();

        Task object = jsonParser.parseObject(json);
        assertThat(object.getId()).isEqualTo(1);
        assertThat(object.getTitle()).isEqualTo(title);
        assertThat(object.getDescription()).isEqualTo(description);
        assertThat(object.getStatus()).isEqualTo(Task.Status.FINISHED);
        // there is a problem with the time zone of the web server
        assertThat(object.getDueDate()).isNotNull();

        Event objectEvent = object.getEvent();
        assertThat(objectEvent).isNull();
    }

    @Test
    public void testFailUpdateNonExistingTask() throws Exception {
        // given
        Task task = taskBusiness.getTaskById(Long.valueOf(1));

        // when
        MockHttpServletResponse response = getResponse(
                put(BASE_URL + "/99999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParser.write(task).getJson())
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testSuccessUpdateTaskStatus() throws Exception {
        // given
        Task task = taskBusiness.getTaskById(Long.valueOf(1));

        // when
        MockHttpServletResponse response = getResponse(
                patch(BASE_URL + "/1")
                        .queryParam("status", Task.Status.FINISHED.toString())
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String json = response.getContentAsString();
        assertThat(json).isNotNull();

        Task object = jsonParser.parseObject(json);
        assertThat(object.getId()).isEqualTo(task.getId());
        assertThat(object.getTitle()).isEqualTo(task.getTitle());
        assertThat(object.getDescription()).isEqualTo(task.getDescription());
        assertThat(object.getStatus()).isEqualTo(Task.Status.FINISHED);
        // there is a problem with the time zone of the web server
        assertThat(object.getDueDate()).isNotNull();

        Event objectEvent = object.getEvent();
        assertThat(objectEvent).isNull();
    }

    @Test
    public void testFailUpdateStatusNonExistingTask() throws Exception {
        // given
        Task task = new Task();
        task.setStatus(Task.Status.FINISHED);

        // when
        MockHttpServletResponse response = getResponse(
                patch(BASE_URL + "/99999")
                        .queryParam("status", Task.Status.FINISHED.toString())
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testSuccessDeleteTask() throws Exception {
        // given

        // when
        MockHttpServletResponse response = getResponse(
                delete(BASE_URL + "/1")
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void testFailDeleteNonExistingTask() throws Exception {
        // given

        // when
        MockHttpServletResponse response = getResponse(
                delete(BASE_URL + "/99999")
        );

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}