package com.teonvioncollins.ToDoList.repositories;

import com.teonvioncollins.ToDoList.models.ListModel;
import com.teonvioncollins.ToDoList.models.Priority;
import com.teonvioncollins.ToDoList.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<ListModel, Long> {

    List<ListModel> findByPriorityAndStatus(Priority priority, Status status);

    List<ListModel> findByPriority(Priority priority);

    List<ListModel> findByStatus(Status status);
}
