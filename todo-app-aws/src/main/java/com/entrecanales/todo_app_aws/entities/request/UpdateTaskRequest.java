package com.entrecanales.todo_app_aws.entities.request;

import java.util.Date;

import com.entrecanales.todo_app_aws.entities.Task;
import com.entrecanales.todo_app_aws.entities.Task.Status;

public class UpdateTaskRequest {

    //region Attributes

    private String title;

    private String description;

    private Status status;

    private Date dueDate;

    //endregion

    //region Constructor

    public UpdateTaskRequest() { }

    public UpdateTaskRequest(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    //endregion

    //region Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    //endregion

    //region Public Methods

    public Task toTask() {
        var task = new Task();

        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStatus(status);

        return task;
    }

    //endregion
}
