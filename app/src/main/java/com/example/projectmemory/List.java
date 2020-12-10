package com.example.projectmemory;

import java.util.ArrayList;

import static com.example.projectmemory.ListType.Todo;

public class List {
    String name;
    private ListType defaultType;
    ArrayList<Item> items;
    ArrayList<String> itemsNames;

    public List(String name) {
        this.name = name;
        this.defaultType = Todo;
        this.items = new ArrayList<>();
        if (this.itemsNames == null) { //Avoid NullPointerException
            itemsNames = new ArrayList<>();
        }
    }

    //Adds a new item to the list
    public void addItem(String itemName){
        Item item = new Item();
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

    public List(String name, ListType type) {
        this.name = name;
        this.defaultType = type;
    }

    public List(List previous) {
        this.name = previous.name;
        this.defaultType = previous.defaultType;
        this.items = previous.items; //TODO is this supposed to be like this?
    }
    
    public void changeListType(ListType newType) {
        for (int i = 0; i < items.size(); i++) {
            Item temp = items.get(i).convert(newType);
            items.set(i, temp);
        }
        this.defaultType = newType;
    }
    
}
