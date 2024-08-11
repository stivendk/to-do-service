package com.coderio.to_do_service.controller;


import com.coderio.to_do_service.dto.ResponseDTO;
import com.coderio.to_do_service.dto.TaskDTO;
import com.coderio.to_do_service.enums.PriorityEnum;
import com.coderio.to_do_service.enums.TaskStatusEnum;
import com.coderio.to_do_service.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    public void testGetAllTasks() {
        TaskDTO taskDTO = new TaskDTO();
        when(taskService.getAllTasks()).thenReturn(List.of(taskDTO));

        List<TaskDTO> tasks = taskController.getAllTasks();
        assertEquals(1, tasks.size());
    }

    @Test
    public void testGetTaskById() {
        TaskDTO taskDTO = new TaskDTO();
        when(taskService.getTaskById(1L)).thenReturn(taskDTO);

        ResponseEntity<ResponseDTO<TaskDTO>> response = taskController.getTaskById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taskDTO, response.getBody().getData());
    }

    @Test
    public void testCreateTask() {
        TaskDTO taskDTO = new TaskDTO();
        when(taskService.createTask(taskDTO)).thenReturn(taskDTO);

        ResponseEntity<ResponseDTO<TaskDTO>> response = taskController.createTask(taskDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(taskDTO, response.getBody().getData());
    }

    @Test
    public void testUpdateTask() {
        TaskDTO taskDTO = new TaskDTO();
        when(taskService.updateTask(1L, taskDTO)).thenReturn(taskDTO);

        ResponseEntity<ResponseDTO<TaskDTO>> response = taskController.updateTask(1L, taskDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taskDTO, response.getBody().getData());
    }

    @Test
    public void testDeleteTask() {
        ResponseEntity<ResponseDTO<Void>> response = taskController.deleteTask(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFilterTask() {
        TaskDTO taskDTO = new TaskDTO();
        when(taskService.getTasksByPriorityAndStatus(PriorityEnum.HIGH, TaskStatusEnum.TO_DO)).thenReturn(Arrays.asList(taskDTO));

        List<TaskDTO> tasks = taskController.filterTask(PriorityEnum.HIGH, TaskStatusEnum.TO_DO);
        assertEquals(1, tasks.size());
    }
}
