package com.htw.project.eventplanner.business;

import com.htw.project.eventplanner.model.GroupConversation;
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

}
