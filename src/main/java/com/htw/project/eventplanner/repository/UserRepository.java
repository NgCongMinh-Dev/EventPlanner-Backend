package com.htw.project.eventplanner.repository;

import com.htw.project.eventplanner.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
