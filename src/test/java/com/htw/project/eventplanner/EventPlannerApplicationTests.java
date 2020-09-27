package com.htw.project.eventplanner;

import com.htw.project.eventplanner.controller.EventController;
import com.htw.project.eventplanner.controller.GroupConversationController;
import com.htw.project.eventplanner.controller.TaskController;
import com.htw.project.eventplanner.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EventPlannerApplicationTests {

    @Autowired
    private EventController eventController;

    @Autowired
    private TaskController taskController;

    @Autowired
    private GroupConversationController groupConversationController;

    @Autowired
    private UserController userController;

    @Test
    void contextLoads() {
        // controller sanity check
        assertThat(eventController).isNotNull();
        assertThat(taskController).isNotNull();
        assertThat(groupConversationController).isNotNull();
        assertThat(userController).isNotNull();
    }

}
