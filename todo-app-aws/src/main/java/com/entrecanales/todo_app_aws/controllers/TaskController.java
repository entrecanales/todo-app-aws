package com.entrecanales.todo_app_aws.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.entrecanales.todo_app_aws.entities.Task;
import com.entrecanales.todo_app_aws.services.TaskService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping(path = "api/v1/tasks")
public class TaskController {
    
    private TaskService service;

    public TaskController(TaskService s) {
        service = s;
    }

    @GetMapping("")
    public List<Task> getTasks() {
        return service.findAllTasks();
    }

    @GetMapping("{id}")
    public Task getTask(@PathVariable int id) {
        return service.getTaskById(id);
    }

    @PostMapping("")
    public Task addTask(@RequestBody Task task) {
        //TODO: process POST request
        
        return service.addTask(task);
    }

    @PutMapping("{id}")
    public Task putTask(@PathVariable int id, @RequestBody Task task) {
        //TODO: process PUT request
        
        return service.updateTask(id, task);
    }
    
    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable int id) {
        service.deleteTaskById(id);
    }
    
    

}
