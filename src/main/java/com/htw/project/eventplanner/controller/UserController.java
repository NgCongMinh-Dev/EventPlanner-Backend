package com.htw.project.eventplanner.controller;

import com.htw.project.eventplanner.business.UserBusiness;
import com.htw.project.eventplanner.model.Task;
import com.htw.project.eventplanner.model.User;
import com.htw.project.eventplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserBusiness business;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> createTask(@RequestBody User u) {
        User user = business.saveUser(u);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> dbUser = business.getUserById(id);
        if (dbUser.isEmpty() || id != user.getId()) {
            // error
        }

        User updatedUser = business.saveUser(dbUser.get());
        return ResponseEntity.ok(updatedUser);
    }

}
