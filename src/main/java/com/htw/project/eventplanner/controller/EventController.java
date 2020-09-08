package com.htw.project.eventplanner.controller;

import com.htw.project.eventplanner.business.EventBusiness;
import com.htw.project.eventplanner.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventBusiness business;

    /*
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event dbEvent = business.createEvent(event);
        return new ResponseEntity<>(dbEvent, HttpStatus.CREATED);
    }
    */

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Event> getEvents(@PathVariable("id") Long id) {
        Event event = business.getEvent(id);
        return ResponseEntity.ok(event);
    }

}
