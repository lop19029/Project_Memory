package com.example.projectmemory;

import android.widget.Toast;

import java.util.ArrayList;

public class List {
    String name;
    ArrayList<ListItem> items;
    ArrayList<String> itemsNames;

    public List(String name){
        this.name = name;
        this.items = new ArrayList<>();
        this.itemsNames = new ArrayList<>();
    }
    public void addItem(String itemName){
        ListItem item = new ListItem(itemName);
        this.items.add(item);
        if(this.itemsNames == null){
            this.itemsNames = new ArrayList<>();
        }
        this.itemsNames.add(itemName);
    }
}
