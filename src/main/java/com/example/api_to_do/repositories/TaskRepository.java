package com.example.api_to_do.repositories;

import com.example.api_to_do.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
