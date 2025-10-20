package com.practice.rk2.service;

import com.practice.rk2.dto.TaskRequestDto;
import com.practice.rk2.model.Stat;
import com.practice.rk2.model.Task;
import com.practice.rk2.model.User;
import com.practice.rk2.repository.TaskRepository;
import com.practice.rk2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepo;
    private final UserRepository userRepo;
    private final UserService userService;

    public TaskService(TaskRepository taskRepo, UserRepository userRepo, UserService userService) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    public void createTask(Long userId, TaskRequestDto dto) {
        User user = userRepo.findById(userId).orElse(null);
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setStat(dto.getStat());
        task.setXpReward(dto.getXpReward());
        task.setUser(user);
        taskRepo.save(task);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public List<Task> getTasksByUser(Long userId) {
        return taskRepo.findAllByUserId(userId);
    }

    public String taskCompleted(String username, Long taskId) {
        User user = userRepo.findByUsername(username);
        Task task = taskRepo.findById(taskId).orElse(null);
        if (task.getStat() == Stat.INTELLIGENCE) {
            int total = user.getIntelligenceXP() + task.getXpReward();
            if (total >= 100) {
                user.setIntelligence(user.getIntelligence() + 1);
                user.setIntelligenceXP(total - 100);
            } else {
                user.setIntelligenceXP(total);
            }
        } else {
            int total = user.getFitnessXP() + task.getXpReward();
            if (total >= 100) {
                user.setFitness(user.getFitness() + 1);
                user.setFitnessXP(total - 100);
            } else {
                user.setFitnessXP(total);
            }
        }
        userService.updateRank(user);
        userRepo.save(user);
        return "Task completed! \n" + userService.displayStats(username);
    }

    public List<Task> findTaskByUserId(Long userId) {
        return taskRepo.findAllByUserId(userId);
    }
}

