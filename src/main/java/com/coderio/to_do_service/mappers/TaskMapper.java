package com.coderio.to_do_service.mappers;

import com.coderio.to_do_service.dto.TaskDTO;
import com.coderio.to_do_service.model.Task;

public class TaskMapper {

    public static TaskDTO toTaskDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.getStatus(),
                task.getCreateAt(),
                task.getUpdateAt()
        );
    }

    public static Task toTask(TaskDTO taskDTO) {
        return Task.builder()
                .id(taskDTO.getId())
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .priority(taskDTO.getPriority())
                .status(taskDTO.getStatus())
                .build();
    }
}
