package com.example.projectmemory;
import java.util.ArrayList;
import com.google.gson.Gson;

public class ListContainer {

    ArrayList<List> lists;
    ArrayList<String> names;
    public ListContainer(){
        this.lists = new ArrayList<>();
        this.names = new ArrayList<>();
    }
    public void createList(String name){
        //Create a new List
        List list = new List(name);
        //Puts the list inside lists
        lists.add(list);
        //Adds lists name to the list
        names.add(name);
    }
    public void deleteList(int position){
        this.lists.remove(position);
        this.names.remove(position);
    }
}
