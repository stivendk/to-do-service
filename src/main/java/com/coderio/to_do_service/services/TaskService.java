package com.coderio.to_do_service.services;

import com.coderio.to_do_service.dto.TaskDTO;
import com.coderio.to_do_service.enums.PriorityEnum;
import com.coderio.to_do_service.enums.TaskStatusEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    List<TaskDTO> getAllTasks();
    TaskDTO getTaskById(Long id);
    TaskDTO createTask(TaskDTO ticketDTO);
    TaskDTO updateTask(Long id, TaskDTO ticketDTO);
    void deleteTask(Long id);
    List<TaskDTO> getTasksByPriorityAndStatus(PriorityEnum priority, TaskStatusEnum status);
}
