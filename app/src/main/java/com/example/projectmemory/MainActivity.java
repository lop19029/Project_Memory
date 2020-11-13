package com.example.projectmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListContainer CommonLists;
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
    public void createCommonList(View view){
        //Get list name
        EditText list = (EditText) findViewById(R.id.CommonListName);
        String listName = list.getText().toString();
        CommonLists.createList(listName);
        Toast.makeText(this, "List added to your Common lists", Toast.LENGTH_SHORT).show();

    }
    public void saveLists(){
        //Use SharedPreferences to store all the lists
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Prepare JSON data
        Gson gson = new Gson();
        String json = gson.toJson(CommonLists);
        editor.putString(JSON_DATA, json);
        editor.apply();
    }

    public void loadLists(){
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        //Load shared preferences
        String listContainers = sharedPreferences.getString(JSON_DATA, null);

        //Deserialize
        Gson gson = new Gson();
        ListContainer savedCommonLists = gson.fromJson(listContainers, ListContainer.class);

        //Check for null
        if (savedCommonLists == null){
            CommonLists = new ListContainer();
        }
        else{
            this.CommonLists = savedCommonLists;
        }
    }
}