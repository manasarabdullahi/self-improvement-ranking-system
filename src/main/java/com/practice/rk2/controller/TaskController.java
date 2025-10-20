package com.practice.rk2.controller;

import com.practice.rk2.dto.TaskRequestDto;
import com.practice.rk2.model.Task;
import com.practice.rk2.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/{userId}/create")
    public ResponseEntity<Void> createTask(@PathVariable Long userId, @RequestBody TaskRequestDto taskRequestDto) {
        taskService.createTask(userId, taskRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Task>> findTasksByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.findTaskByUserId(userId));
    }

    @PutMapping("/{username}/{taskId}/complete")
    public ResponseEntity<String> completeTask(@PathVariable String username, @PathVariable Long taskId) {
        String result = taskService.taskCompleted(username, taskId);
        return ResponseEntity.ok(result);
    }
}
