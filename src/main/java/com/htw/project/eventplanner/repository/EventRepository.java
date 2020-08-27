package com.htw.project.eventplanner.repository;

import com.htw.project.eventplanner.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
