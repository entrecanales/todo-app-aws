package com.entrecanales.todo_app_aws.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.entrecanales.todo_app_aws.entities.Task;
import com.entrecanales.todo_app_aws.repositories.ITaskRepository;

@Service
public class TaskService {

    private ITaskRepository repository;
    
    public TaskService(ITaskRepository r) {
        repository = r;
    }

    public Task getTaskById(int id) {
        var task = repository.findById(id).orElseThrow();
        Hibernate.initialize(task);
        return task;
    }

    public List<Task> findAllTasks() {
        return repository.findAll();
    }

    public Task addTask(Task task) {
        task.setCreatedAt(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return repository.save(task);
    }

    public Task updateTask(int id, Task task) {
        var originalTask = repository.findById(id).orElseThrow();
        
        //TODO: This can be improved for sure...
        if (task.getTitle() != null && !task.getTitle().trim().isEmpty()) {
            originalTask.setTitle(task.getTitle());
        }

        if (task.getDescription() != null && !task.getDescription().trim().isEmpty()) {
            originalTask.setDescription(task.getDescription());
        }

        if (task.getStatus() != null) {
            originalTask.setStatus(task.getStatus());
        }

        if (task.getDueDate() != null) {
            originalTask.setDueDate(task.getDueDate());
        }
        
        originalTask.setUpdatedAt(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        return repository.save(originalTask);
    }

    public void deleteTaskById(int id) {
        var task = repository.findById(id).orElseThrow();

        repository.delete(task);
    }
}
