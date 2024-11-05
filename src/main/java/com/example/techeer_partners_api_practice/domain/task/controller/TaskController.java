package com.example.techeer_partners_api_practice.domain.task.controller;

import com.example.techeer_partners_api_practice.domain.task.dto.TaskRequestDto;
import com.example.techeer_partners_api_practice.domain.task.dto.TaskResponseDto;
import com.example.techeer_partners_api_practice.domain.task.service.TaskService;
import com.example.techeer_partners_api_practice.global.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    // 생성자 주입
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 할 일 생성
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createTask(@RequestBody TaskRequestDto dto) {
        taskService.createTask(dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "할 일이 생성되었습니다."));
    }

    // 할 일 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto dto) {
        taskService.updateTask(id, dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "할 일이 수정되었습니다."));
    }

    // 할 일 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "할 일이 삭제되었습니다."));
    }

    // 할 일 전체 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> getAllTasks() {
        List<TaskResponseDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(new ApiResponse<>("success", "모든 일이 조회되었습니다.", tasks));
    }

    // 완료된 일 조회
    @GetMapping("/completed")
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> getCompletedTasks() {
        List<TaskResponseDto> tasks = taskService.getCompletedTasks();
        return ResponseEntity.ok(new ApiResponse<>("success", "완료 된 일이 조회되었습니다.", tasks));
    }

    // 미완료 일 조회
    @GetMapping("/incomplete")
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> getIncompleteTasks() {
        List<TaskResponseDto> tasks = taskService.getIncompleteTasks();
        return ResponseEntity.ok(new ApiResponse<>("success", "미완료 된 일이 조회되었습니다.", tasks));
    }
}
