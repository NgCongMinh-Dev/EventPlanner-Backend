package com.htw.project.eventplanner.business;

import com.htw.project.eventplanner.model.Event;
import com.htw.project.eventplanner.model.GroupConversation;
import com.htw.project.eventplanner.model.exception.InvalidIdException;
import com.htw.project.eventplanner.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventBusiness {

    private EventRepository eventRepository;

    private GroupConversationBusiness gcBusiness;

    @Autowired
    public EventBusiness(EventRepository eventRepository, GroupConversationBusiness gcBusiness) {
        this.eventRepository = eventRepository;
        this.gcBusiness = gcBusiness;
    }

    public Event getEvent(Long id) throws InvalidIdException {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new InvalidIdException("Invalid event id."));
    }

    public Event createEvent(Long gcId, Event event) throws InvalidIdException {
        GroupConversation gc = gcBusiness.getById(gcId);
        Event dbEvent = eventRepository.save(event);

        gc.setEvent(dbEvent);
        gcBusiness.save(gc);

        return dbEvent;
    }

}
