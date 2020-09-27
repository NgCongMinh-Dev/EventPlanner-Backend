package com.htw.project.eventplanner.controller;

import com.htw.project.eventplanner.business.UserBusiness;
import com.htw.project.eventplanner.model.User;
import com.htw.project.eventplanner.model.exception.InvalidArgumentException;
import com.htw.project.eventplanner.model.exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserBusiness business;

    @Autowired
    public UserController(UserBusiness userBusiness) {
        this.business = userBusiness;
    }

    @PostMapping(
            path = "/join",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity join(@RequestParam("groupChat") Long gcId, @NonNull @RequestBody User user) throws InvalidIdException, InvalidArgumentException {
        User dbUser = business.joinGroupConversation(gcId, user);
        return ResponseEntity.ok(dbUser);
    }

}
