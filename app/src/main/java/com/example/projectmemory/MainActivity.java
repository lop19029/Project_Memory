package com.example.projectmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ListContainer> ListContainers;
    public static final String JSON_DATA = "JSON_DATA";
    public static final String APP_PREFS = "APPLICATION_PREFERENCES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLists();
    }
    @Override
    protected void onStop() {
        // call the superclass method first
        super.onStop();

        //Update and save all current lists in SharedPreferences
        saveLists();
    }

    public void saveLists(){
        //Use SharedPreferences to store all the lists
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(ListContainer lc : ListContainers){
            //Prepare JSON data
            lc.parseLists();
            //Save each list in JSON_LIST
            editor.putString(JSON_DATA, lc.JSON_LISTS);
        }
        editor.apply();
    }

    public void loadLists(){
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        //Load shared preferences
        String listContainers = sharedPreferences.getString(JSON_DATA, null);

        //Deserialize
        Gson gson = new Gson();
        ListContainer savedContainers = gson.fromJson(listContainers, ListContainer.class);
        ListContainers.add(savedContainers);

    }
}