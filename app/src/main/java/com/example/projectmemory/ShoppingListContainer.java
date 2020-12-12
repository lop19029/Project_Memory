package com.example.projectmemory;

import java.util.ArrayList;

public class ShoppingListContainer extends ListContainer {
    ArrayList<ShoppingList> shopLists;
    ArrayList<String> shopListsNames;
    public ShoppingListContainer(){
        this.shopLists = new ArrayList<>();
        this.shopListsNames = new ArrayList<>();
    }
    public void createList(String name){
        //Create a new List
        ShoppingList list = new ShoppingList(name);
        //Puts the list inside lists
        shopLists.add(list);
        //Adds lists name to the list
        shopListsNames.add(name);
    }
    public void deleteList(int position){
        this.shopLists.remove(position);
        this.shopListsNames.remove(position);
    }
}
