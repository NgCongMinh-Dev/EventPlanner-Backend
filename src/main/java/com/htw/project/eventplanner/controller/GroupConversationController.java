package com.htw.project.eventplanner.controller;

import com.htw.project.eventplanner.business.GroupConversationBusiness;
import com.htw.project.eventplanner.model.Event;
import com.htw.project.eventplanner.model.GroupConversation;
import com.htw.project.eventplanner.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cg")
public class GroupConversationController {

    @Autowired
    private GroupConversationBusiness business;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GroupConversation> create(@RequestBody GroupConversation cg) {
        GroupConversation groupConversation = business.save(cg);
        return new ResponseEntity<>(groupConversation, HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GroupConversation> getGroupConversation(@PathVariable("id") Long id) {
        GroupConversation gc = business.getById(id);
        if (gc == null) {
            // TODO error
        }

        return new ResponseEntity(gc, HttpStatus.OK);
    }

    @PostMapping(
            path = "/{id}/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addUser(@PathVariable("id") Long id, @NonNull @RequestBody User user) {
        GroupConversation gc = business.getById(id);
        if (gc == null) {
            // TODO error
        }
        business.joinGroupConversation(gc, user);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(
            path = "/{id}/events",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getEvent(@PathVariable("id") Long id) {
        GroupConversation gc = business.getById(id);
        if (gc == null) {
            // TODO error
        }
        Event event = business.getEvent(gc);

        return new ResponseEntity(event, HttpStatus.OK);
    }


}
