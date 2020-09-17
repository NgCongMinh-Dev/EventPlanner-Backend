package com.htw.project.eventplanner.controller;

import com.htw.project.eventplanner.business.TaskBusiness;
import com.htw.project.eventplanner.model.Task;
import com.htw.project.eventplanner.model.exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskBusiness business;

    @Autowired
    public TaskController(TaskBusiness business) {
        this.business = business;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Task> createTask(@RequestParam("event") Long eventId, @RequestBody Task t) throws InvalidIdException {
        Task task = business.saveTask(eventId, t);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) throws InvalidIdException {
        Task task = business.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task) throws InvalidIdException {
        Task dbTask = business.getTaskById(id);
        if (id != dbTask.getId()) {
            throw new InvalidIdException("Invalid task id.");
        }

        Task updatedTask = business.updateTask(dbTask, task);
        return ResponseEntity.ok(updatedTask);
    }

    @PatchMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity updateTaskStatus(@PathVariable("id") Long id, @RequestParam Task.Status status) throws InvalidIdException {
        Task task = business.getTaskById(id);
        if (id != task.getId()) {
            throw new InvalidIdException("Invalid task id.");
        }
        task.setStatus(status);

        Task updatedTask = business.saveTask(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") Long id) throws InvalidIdException {
        Task task = business.getTaskById(id);
        business.deleteTask(task);
        return new ResponseEntity(HttpStatus.OK);
    }

}
