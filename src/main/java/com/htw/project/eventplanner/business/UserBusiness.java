package com.htw.project.eventplanner.business;

import com.htw.project.eventplanner.model.GroupConversation;
import com.htw.project.eventplanner.model.User;
import com.htw.project.eventplanner.model.exception.InvalidArgumentException;
import com.htw.project.eventplanner.model.exception.InvalidIdException;
import com.htw.project.eventplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBusiness {

    private UserRepository repository;

    private GroupConversationBusiness gcBusiness;

    @Autowired
    public UserBusiness(UserRepository repository, GroupConversationBusiness gcBusiness) {
        this.repository = repository;
        this.gcBusiness = gcBusiness;
    }

    public User joinGroupConversation(Long gcId, User user) throws InvalidIdException, InvalidArgumentException {
        GroupConversation gc = gcBusiness.getById(gcId);

        return gc.getParticipants().stream().
                filter(u -> user.getUsername().equalsIgnoreCase(u.getUsername()))
                .findAny()
                .orElseThrow(() -> new InvalidArgumentException("Invalid username."));
    }

}
