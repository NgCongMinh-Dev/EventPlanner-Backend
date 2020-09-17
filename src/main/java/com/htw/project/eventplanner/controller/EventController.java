package com.htw.project.eventplanner.controller;

import com.htw.project.eventplanner.business.EventBusiness;
import com.htw.project.eventplanner.model.Event;
import com.htw.project.eventplanner.model.exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventBusiness business;

    @Autowired
    public EventController(EventBusiness business) {
        this.business = business;
    }

    @PostMapping(
            path = "/new",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Event> createEvent(@RequestParam("groupChat") Long gcId, @RequestBody Event event) throws InvalidIdException {
        Event dbEvent = business.createEvent(gcId, event);
        return new ResponseEntity<>(dbEvent, HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Event> getEvents(@PathVariable("id") Long id) throws InvalidIdException {
        Event event = business.getEvent(id);
        return ResponseEntity.ok(event);
    }

}
