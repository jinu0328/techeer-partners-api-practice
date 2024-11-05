package com.example.techeer_partners_api_practice.domain.task.service;

import com.example.techeer_partners_api_practice.domain.task.dto.TaskRequestDto;
import com.example.techeer_partners_api_practice.domain.task.dto.TaskResponseDto;
import com.example.techeer_partners_api_practice.domain.task.entity.Task;
import com.example.techeer_partners_api_practice.domain.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // 생성자 주입
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 할 일 생성
    public void createTask(TaskRequestDto dto) {
        Task task = new Task(dto.getTitle());
        taskRepository.save(task);
    }

    // 할 일 수정
    public void updateTask(Long id, TaskRequestDto dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다. id=" + id));
        task.update(dto.getTitle(), dto.isDone());
        taskRepository.save(task);
    }

    // 할 일 삭제
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다: " + id));
        taskRepository.delete(task);
    }

    // 완료된 일 조회
    public List<TaskResponseDto> getCompletedTasks() {
        List<Task> tasks = taskRepository.findByIsDone(true);
        List<TaskResponseDto> responseDtos = new ArrayList<>();
        for (Task task : tasks) {
            responseDtos.add(task.toDto());
        }
        return responseDtos;
    }

    // 미완료 일 조회
    public List<TaskResponseDto> getIncompleteTasks() {
        List<Task> tasks = taskRepository.findByIsDone(false);
        List<TaskResponseDto> responseDtos = new ArrayList<>();
        for (Task task : tasks) {
            responseDtos.add(task.toDto());
        }
        return responseDtos;
    }
}
