package com.htw.project.eventplanner.business;

import com.htw.project.eventplanner.model.GroupConversation;
import com.htw.project.eventplanner.model.User;
import com.htw.project.eventplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBusiness {

    private UserRepository repository;

    private GroupConversationBusiness gcBusiness;

    @Autowired
    public UserBusiness(UserRepository repository, GroupConversationBusiness gcBusiness) {
        this.repository = repository;
        this.gcBusiness = gcBusiness;
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    public void joinGroupConversation(Long gcId, User user) {
        GroupConversation gc = gcBusiness.getById(gcId);
        if (gc == null) {
            // TODO error
        }

        User filteredUser = gc.getParticipants().stream().
                filter(u -> user.getUsername().equalsIgnoreCase(u.getUsername()))
                .findAny().orElse(null);

        if (filteredUser == null) {
            User dbUser = saveUser(user);
            gc.getParticipants().add(dbUser);
            gcBusiness.save(gc);
        }
    }

}
