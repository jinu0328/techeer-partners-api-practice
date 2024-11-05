package com.example.techeer_partners_api_practice.domain.task.entity;

import com.example.techeer_partners_api_practice.domain.task.dto.TaskResponseDto;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Access(AccessType.FIELD)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private boolean isDone = false;

    public Task() {
    }

    public Task(String title) {
        this.title = title;
    }

    public TaskResponseDto toDto() {
        return new TaskResponseDto(id, title, isDone);
    }

    public void update(String title, Boolean isDone) {
        if (title != null) {
            this.title = title;
        }
        if (isDone != null) {
            this.isDone = isDone;
        }
    }

}