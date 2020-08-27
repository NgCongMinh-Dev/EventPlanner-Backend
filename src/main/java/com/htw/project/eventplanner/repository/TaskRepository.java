package com.htw.project.eventplanner.repository;

import com.htw.project.eventplanner.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
