package com.teonvioncollins.ToDoList.services;

import com.teonvioncollins.ToDoList.models.ListModel;
import com.teonvioncollins.ToDoList.repositories.ItemRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepo itemRepo;

    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public void addTask(String taskName) {
        itemRepo.save(new ListModel(taskName));
    }

    public List<ListModel> getAllTasks() {
        return itemRepo.findAll();
    }

    public void deleteTask(Long id) {
        itemRepo.deleteById(id);
    }
}
