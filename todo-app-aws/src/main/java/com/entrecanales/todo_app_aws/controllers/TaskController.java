package com.entrecanales.todo_app_aws.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.entrecanales.todo_app_aws.entities.Task;
import com.entrecanales.todo_app_aws.entities.request.NewTaskRequest;
import com.entrecanales.todo_app_aws.entities.request.UpdateTaskRequest;
import com.entrecanales.todo_app_aws.services.TaskService;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(service.findAllTasks());
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getTask(@PathVariable int id) {
        Task task;
        try {
            task = service.getTaskById(id);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<Task> addTask(@RequestBody NewTaskRequest request) {
        //TODO: process POST request
        if (request == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var task = service.addTask(request.toTask());
        return new ResponseEntity<Task>(task, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> putTask(@PathVariable int id, @RequestBody UpdateTaskRequest request) {
        //TODO: process PUT request
        Task task;
        try {
            task = service.updateTask(id, request.toTask());
        }
        catch (NoSuchElementException ex) {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(task);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable int id) {
        try {
            service.deleteTaskById(id);
        }
        catch (NoSuchElementException ex) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    

}
