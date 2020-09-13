package com.htw.project.eventplanner.controller;

import com.htw.project.eventplanner.business.UserBusiness;
import com.htw.project.eventplanner.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserBusiness business;

    @PostMapping(
            path = "/join",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addUser(@RequestParam("groupChat") Long gcId, @NonNull @RequestBody User user) {
        business.joinGroupConversation(gcId, user);

        return new ResponseEntity(HttpStatus.OK);
    }

}
