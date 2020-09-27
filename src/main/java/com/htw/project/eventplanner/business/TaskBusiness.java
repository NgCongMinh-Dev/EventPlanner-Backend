package com.htw.project.eventplanner.business;

import com.htw.project.eventplanner.model.Event;
import com.htw.project.eventplanner.model.Task;
import com.htw.project.eventplanner.model.exception.InvalidIdException;
import com.htw.project.eventplanner.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskBusiness {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EventBusiness eventBusiness;

    public Task saveTask(Long eventId, Task task) throws InvalidIdException {
        Event event = eventBusiness.getEvent(eventId);
        task.setEvent(event);
        return taskRepository.save(task);
    }

    public Task updateTask(Task oldTask, Task newTask) {
        oldTask.setTitle(newTask.getTitle());
        oldTask.setDescription(newTask.getDescription());
        oldTask.setAssignees(newTask.getAssignees());
        oldTask.setDueDate(newTask.getDueDate());
        oldTask.setStatus(newTask.getStatus());

        return taskRepository.save(oldTask);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public Task getTaskById(Long id) throws InvalidIdException {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new InvalidIdException("Invalid task id."));
    }

}
