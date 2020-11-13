package com.example.projectmemory;
import java.util.ArrayList;
import com.google.gson.Gson;

public class ListContainer {
    ArrayList<List> lists;

    public void createList(String name){
        //Create a new List
        List list = new List(name);
        //Puts the list inside lists
        lists.add(list);
    }
}
