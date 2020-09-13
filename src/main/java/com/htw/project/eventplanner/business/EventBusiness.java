package com.htw.project.eventplanner.business;

import com.htw.project.eventplanner.model.Event;
import com.htw.project.eventplanner.model.GroupConversation;
import com.htw.project.eventplanner.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventBusiness {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GroupConversationBusiness gcBusiness;

    public Event getEvent(Long id) {
        Optional<Event> optional = eventRepository.findById(id);
        return optional.orElse(null);
    }

    public Event createEvent(Long gcId, Event event) {
        GroupConversation gc = gcBusiness.getById(gcId);
        if (gc == null) {
            // TODO error
        }
        Event dbEvent = eventRepository.save(event);

        gc.setEvent(dbEvent);
        gcBusiness.save(gc);

        return dbEvent;
    }

}
