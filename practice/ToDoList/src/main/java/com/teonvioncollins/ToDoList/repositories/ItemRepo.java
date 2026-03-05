package com.teonvioncollins.ToDoList.repositories;

import com.teonvioncollins.ToDoList.models.ListModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<ListModel, Long> {

}
