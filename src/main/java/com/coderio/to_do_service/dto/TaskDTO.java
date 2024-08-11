package com.coderio.to_do_service.dto;

import com.coderio.to_do_service.enums.PriorityEnum;
import com.coderio.to_do_service.enums.TaskStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private PriorityEnum priority;
    private TaskStatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}