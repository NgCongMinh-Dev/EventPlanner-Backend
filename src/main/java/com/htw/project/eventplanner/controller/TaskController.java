package com.htw.project.eventplanner.controller;

import com.htw.project.eventplanner.business.TaskBusiness;
import com.htw.project.eventplanner.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskBusiness business;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Task> createTask(@RequestParam("event") Long eventId, @RequestBody Task t) {
        Task task = business.saveTask(eventId, t);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        Optional<Task> task = business.getTaskById(id);
        if (task.isEmpty() || id != task.get().getId()) {
            // error
        }

        return ResponseEntity.ok(task.get());
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        Optional<Task> dbTask = business.getTaskById(id);
        if (dbTask.isEmpty() || id != task.getId()) {
            // error
        }

        Task updatedTask = business.updateTask(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") Long id) {
        Optional<Task> dbTask = business.getTaskById(id);
        if (dbTask.isEmpty()) {
            // error
        }

        business.deleteTask(dbTask.get());
        return new ResponseEntity(HttpStatus.OK);
    }

}
