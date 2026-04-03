package com.teonvioncollins.ToDoList.services;

import com.teonvioncollins.ToDoList.models.ListModel;
import com.teonvioncollins.ToDoList.models.Priority;
import com.teonvioncollins.ToDoList.models.Status;
import com.teonvioncollins.ToDoList.repositories.ItemRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ItemService {

    public final ItemRepo itemRepo;


    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public void addTask(String taskName, LocalDate dueDate, Priority priority) {
        itemRepo.save(new ListModel(taskName, dueDate, priority));
    }

    public List<ListModel> getAllTasks() {
       return itemRepo.findAll();
    }

    public ListModel getTaskById(Long id) {
        return itemRepo.findById(id).orElseThrow();
    }

    public void updateTask(Long id, String taskName, LocalDate dueDate, Priority priority) {
        ListModel task = itemRepo.findById(id).orElseThrow();

        task.setTaskName(taskName);
        task.setDueDate(dueDate);
        task.setPriority(priority);

        itemRepo.save(task);
    }

    public void completeTask(Long id) {
        ListModel listModel = itemRepo.findById(id).orElseThrow();
        listModel.setStatus(Status.COMPLETE);
        itemRepo.save(listModel);
    }

    public void deleteTask(Long id) {
        itemRepo.deleteById(id);
    }
}
