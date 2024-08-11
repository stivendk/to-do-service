package com.coderio.to_do_service.model;

import com.coderio.to_do_service.enums.PriorityEnum;
import com.coderio.to_do_service.enums.TaskStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Debe ingresar un titulo")
    private String title;

    @NotNull(message = "Debe haber una descripción")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Debe ingresar una prioridad")
    private PriorityEnum priority;

    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = TaskStatusEnum.TO_DO;
        } else if (this.status == TaskStatusEnum.COMPLETED) {
            throw new ValidationException("No se puede crear una tarea con el estado COMPLETED.");
        }

    }

    @PreUpdate
    public void preUpdate() { this.updateAt = LocalDateTime.now(); }
}
