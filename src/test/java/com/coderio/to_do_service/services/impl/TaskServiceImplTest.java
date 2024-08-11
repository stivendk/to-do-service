package com.coderio.to_do_service.services.impl;

import com.coderio.to_do_service.dto.TaskDTO;
import com.coderio.to_do_service.enums.PriorityEnum;
import com.coderio.to_do_service.enums.TaskStatusEnum;
import com.coderio.to_do_service.exceptions.TaskNotFoundException;
import com.coderio.to_do_service.model.Task;
import com.coderio.to_do_service.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void testGetAllTasks() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<TaskDTO> tasks = taskService.getAllTasks();
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
    }

    @Test
    public void testGetTaskById_Found() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskDTO taskDTO = taskService.getTaskById(1L);
        assertNotNull(taskDTO);
    }

    @Test
    public void testGetTaskById_NotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(1L));
    }

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        TaskDTO taskDTO = new TaskDTO();
        TaskDTO createdTask = taskService.createTask(taskDTO);

        assertNotNull(createdTask);
        assertEquals(1L, createdTask.getId());
    }

    @Test
    public void testUpdateTask_Found() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1L);
        taskDTO.setTitle("Updated title");
        TaskDTO updatedTask = taskService.updateTask(1L, taskDTO);

        assertNotNull(updatedTask);
        assertEquals("Updated title", updatedTask.getTitle());
    }

    @Test
    public void testUpdateTask_NotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1L);
        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(1L, taskDTO));
    }

    @Test
    public void testDeleteTask_Found() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    public void testDeleteTask_NotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(1L));
    }

    @Test
    public void testGetTasksByPriorityAndStatus() {
        Task task = new Task();
        task.setPriority(PriorityEnum.HIGH);
        task.setStatus(TaskStatusEnum.TO_DO);
        when(taskRepository.findAll(any(Example.class))).thenReturn(List.of(task));

        List<TaskDTO> tasks = taskService.getTasksByPriorityAndStatus(PriorityEnum.HIGH, TaskStatusEnum.TO_DO);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
    }
}
