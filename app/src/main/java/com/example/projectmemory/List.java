package com.example.projectmemory;

import java.util.ArrayList;

import static com.example.projectmemory.ListType.Todo;

public class List {
    String name;
    private ListType defaultType;
    ArrayList<Item> items;

    public List(String name) {
        this.name = name;
        this.defaultType = Todo;
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
