package com.htw.project.eventplanner.controller;

import com.htw.project.eventplanner.business.EventBusiness;
import com.htw.project.eventplanner.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventBusiness business;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event dbEvent = business.createEvent(event);
        return new ResponseEntity<>(dbEvent, HttpStatus.CREATED);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> events = business.getEvents();
        return ResponseEntity.ok(events);
    }

}
