package com.coderio.to_do_service.services.impl;

import com.coderio.to_do_service.dto.TaskDTO;
import com.coderio.to_do_service.enums.PriorityEnum;
import com.coderio.to_do_service.enums.TaskStatusEnum;
import com.coderio.to_do_service.exceptions.TaskNotFoundException;
import com.coderio.to_do_service.mappers.TaskMapper;
import com.coderio.to_do_service.model.Task;
import com.coderio.to_do_service.repository.TaskRepository;
import com.coderio.to_do_service.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskMapper::toTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return TaskMapper.toTaskDTO(task);
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task newTask = TaskMapper.toTask(taskDTO);
        Task savedTask = taskRepository.save(newTask);
        return TaskMapper.toTaskDTO(savedTask);
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(taskDTO.getId()));
        task.setDescription(taskDTO.getDescription());
        task.setTitle(taskDTO.getTitle());
        task.setPriority(taskDTO.getPriority());
        task.setStatus(taskDTO.getStatus());
        task = taskRepository.save(task);
        return TaskMapper.toTaskDTO(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
    }

    @Override
    public List<TaskDTO> getTasksByPriorityAndStatus(PriorityEnum priority, TaskStatusEnum status) {
        Task taskExample = new Task();
        taskExample.setPriority(priority);
        taskExample.setStatus(status);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues();

        Example<Task> example = Example.of(taskExample, matcher);

        return taskRepository.findAll(example).stream()
                .map(TaskMapper::toTaskDTO)
                .collect(Collectors.toList());
    }
}
