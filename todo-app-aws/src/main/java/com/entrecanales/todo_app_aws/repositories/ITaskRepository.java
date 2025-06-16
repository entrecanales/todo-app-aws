package com.entrecanales.todo_app_aws.repositories;

import org.springframework.stereotype.Repository;

import com.entrecanales.todo_app_aws.entities.Task;

import org.springframework.data.jpa.repository.JpaRepository;;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Integer> {
    
}
