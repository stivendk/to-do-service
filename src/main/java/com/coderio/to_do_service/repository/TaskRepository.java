package com.coderio.to_do_service.repository;

import com.coderio.to_do_service.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
