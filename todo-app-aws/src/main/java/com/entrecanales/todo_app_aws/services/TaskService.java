package com.entrecanales.todo_app_aws.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
        return repository.getReferenceById(id);
    }

    public List<Task> findAllTasks() {
        return repository.findAll();
    }

    public Task addTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(int id, Task task) {
        var originalTask = repository.getReferenceById(id);
        
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

        LocalDate localDate = LocalDate.now(); // Example LocalDate
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        originalTask.setUpdatedAt(date);

        return repository.save(originalTask);
    }

    public void deleteTaskById(int id) {
        var task = repository.getReferenceById(id);

        repository.delete(task);
    }
}
