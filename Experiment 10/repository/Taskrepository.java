package com.app.todoapp.repository;

import com.app.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
//CRUD operations
                                                  //Entity, Primary Key
public interface Taskrepository extends JpaRepository<Task,Long> {
}
