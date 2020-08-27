package com.htw.project.eventplanner.business;

import com.htw.project.eventplanner.model.Task;
import com.htw.project.eventplanner.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskBusiness {

    @Autowired
    private TaskRepository repository;

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public void deleteTask(Task task) {
        repository.delete(task);
    }

    public Optional<Task> getTaskById(Long id){
        return repository.findById(id);
    }

}
