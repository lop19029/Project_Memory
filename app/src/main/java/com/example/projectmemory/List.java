package com.example.projectmemory;

import android.widget.Toast;

import java.util.ArrayList;

public class List {
    String name;
    ArrayList<ListItem> items;
    ArrayList<String> names;

    public List(String name){
        this.name = name;
        this.items = new ArrayList<>();
        this.names = new ArrayList<>();
    }
    public void addItem(String itemName){
        ListItem item = new ListItem(itemName);
        this.items.add(item);
        this.names.add(itemName);

    }
}
