package com.teonvioncollins.listsapp.models;

import java.util.ArrayList;
import java.util.List;

public class UserList {

    private List<String> itemsList = new ArrayList<>();

   private String name;

   public List<String> getItemsList() {
       return itemsList;
   }

   public void setItemsList(List<String> itemsList) {
       this.itemsList = itemsList;
   }

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }
}
