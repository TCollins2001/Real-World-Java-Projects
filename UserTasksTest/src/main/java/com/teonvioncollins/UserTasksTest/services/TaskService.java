package com.teonvioncollins.UserTasksTest.services;

import com.teonvioncollins.UserTasksTest.models.TaskModel;
import com.teonvioncollins.UserTasksTest.repos.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskModel createTask(String title, String description) {
        TaskModel task = new TaskModel(title, description);
        return taskRepository.save(task);
    }

    public List<TaskModel> getAllTasks() {
        return taskRepository.findAll();
    }

    public TaskModel getSingleTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task Not Found"));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
