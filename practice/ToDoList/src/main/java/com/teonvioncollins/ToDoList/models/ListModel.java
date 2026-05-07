package com.teonvioncollins.ToDoList.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "todo_db")
public class ListModel {

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;

    private LocalDate dueDate;

    public ListModel() {
    }

    public ListModel(Long id, String taskName, LocalDate dueDate, Priority priority, Status status) {
        this.id = id;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    public ListModel(String taskName, LocalDate dueDate, Priority priority) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = Status.IN_PROGRESS;
        /* options user set initially, so no status in parameter */
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
