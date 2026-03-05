package com.teonvioncollins.ToDoList.models;

import jakarta.persistence.*;

@Entity
@Table(name = "todo_db")
public class ListModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;

    public ListModel() {}

    public ListModel(Long id, String taskName) {
        this.id = id;
        this.taskName = taskName;
    }

    public ListModel(String taskName) {
        this.taskName = taskName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
