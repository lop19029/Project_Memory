package com.example.projectmemory;
import java.util.ArrayList;
import com.google.gson.Gson;

public class ListContainer {
    //TODO: Find a way to use JSON_LIST as final
    public static String JSON_LISTS = "";
    ArrayList<List> lists;

    public void createList(String name){
        //Create a new List
        List list = new List(name);
        //Puts the list inside lists
        lists.add(list);
    }

    public void parseLists(){
        Gson gson = new Gson();
        for(List l : lists){
            String listData = gson.toJson(l);
            JSON_LISTS+=listData;
        }
    }
}
