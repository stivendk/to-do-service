package com.coderio.to_do_service.controller;

import com.coderio.to_do_service.dto.ResponseDTO;
import com.coderio.to_do_service.dto.TaskDTO;
import com.coderio.to_do_service.enums.PriorityEnum;
import com.coderio.to_do_service.enums.TaskStatusEnum;
import com.coderio.to_do_service.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Get all tasks", description = "Retrieve a list of all tasks")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Operation(summary = "Get a task by ID", description = "Retrieve a specific task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<TaskDTO>> getTaskById(
            @Parameter(description = "ID of the task to be retrieved", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(new ResponseDTO<>(true, taskService.getTaskById(id), "Tarea encontrada"));
    }

    @Operation(summary = "Create a new task", description = "Create a new task with the provided details")
    @ApiResponse(responseCode = "201", description = "Task created successfully",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    @PostMapping
    public ResponseEntity<ResponseDTO<TaskDTO>> createTask(
            @Parameter(description = "Details of the new task", required = true) @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(true, taskService.createTask(taskDTO), "Tarea creada exitosamente"));
    }

    @Operation(summary = "Update an existing task", description = "Update an existing task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<TaskDTO>> updateTask(
            @Parameter(description = "ID of the task to be updated", required = true) @PathVariable Long id,
            @Parameter(description = "Updated task details", required = true) @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(new ResponseDTO<>(true, taskService.updateTask(id,taskDTO), "Tarea modificada exitosamente"));
    }

    @Operation(summary = "Delete a task by ID", description = "Delete a specific task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteTask(
            @Parameter(description = "ID of the task to be deleted", required = true) @PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(new ResponseDTO<>(true, null,"¡Producto eliminado exitosamente!"));
    }

    @Operation(summary = "Filter tasks", description = "Filter tasks by priority and/or status")
    @ApiResponse(responseCode = "200", description = "Filtered tasks retrieved successfully")
    @GetMapping("/filter")
    public List<TaskDTO> filterTask(
            @Parameter(description = "Priority of the tasks to filter", required = false) @RequestParam(required = false) PriorityEnum priority,
            @Parameter(description = "Status of the tasks to filter", required = false) @RequestParam(required = false) TaskStatusEnum status
            ) {
        return taskService.getTasksByPriorityAndStatus(priority, status);
    }
}
