package com.htw.project.eventplanner.business;

import com.htw.project.eventplanner.model.Event;
import com.htw.project.eventplanner.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventBusiness {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEvents() {
        return StreamSupport.stream(eventRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

}
