package com.example.projectmemory;

import java.util.ArrayList;

public class ShoppingList {
    String name;
    ArrayList<FoodItem> items;
    public static ArrayList<FoodItem> expirableItems;
    ArrayList<String> itemsNames;

    public ShoppingList(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.expirableItems = new ArrayList<>();
        if (this.itemsNames == null) { //Avoid NullPointerException
            itemsNames = new ArrayList<>();
        }
    }

    //Adds a new item to the list
    public void addItem(String itemName){
        FoodItem item = new FoodItem();
        item.setName(itemName);
        this.items.add(item);
        if(this.itemsNames == null){ //Avoid NullPointerException
            this.itemsNames = new ArrayList<>();
        }
        this.itemsNames.add(itemName);
    }

    public void deleteItem(int position){
        this.items.remove(position);
        this.itemsNames.remove(position);
    }

    public void editItemName(int position){
        String name = itemsNames.get(position);
        itemsNames.set(position, String.format("%s %s", name,"- Done"));
        items.get(position).name = String.format("%s %s", name,"- Done");
    }

    }


