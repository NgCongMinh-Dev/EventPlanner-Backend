package com.htw.project.eventplanner.business;

import com.htw.project.eventplanner.model.Event;
import com.htw.project.eventplanner.model.GroupConversation;
import com.htw.project.eventplanner.model.User;
import com.htw.project.eventplanner.repository.GroupConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GroupConversationBusiness {

    @Autowired
    private GroupConversationRepository repository;

    public GroupConversation save(GroupConversation gc) {
        if (gc.getParticipants() == null) {
            gc.setParticipants(new ArrayList<>());
        }

        return repository.save(gc);
    }

    public GroupConversation getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void joinGroupConversation(GroupConversation gc, User user) {
        User filteredUser = gc.getParticipants().stream().
                filter(u -> user.getUsername().equalsIgnoreCase(u.getUsername()))
                .findAny().orElse(null);

        if (filteredUser == null) {
            gc.getParticipants().add(user);
            save(gc);
        }
    }

    public Event getEvent(GroupConversation gc) {
        Event event = gc.getEvent();
        if (event == null) {
            event = new Event();
            gc.setEvent(event);
            save(gc);
        }
        return event;
    }

}
