package com.htw.project.eventplanner.business;

import com.htw.project.eventplanner.model.Event;
import com.htw.project.eventplanner.model.Task;
import com.htw.project.eventplanner.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskBusiness {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EventBusiness eventBusiness;

    public Task saveTask(Long eventId, Task task) {
        Event event = eventBusiness.getEvent(eventId);
        if (event == null) {
            // TODO error
        }

        task.setEvent(event);
        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

}
