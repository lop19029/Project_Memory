package com.example.projectmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
/**
 * Set up the main page and lists of the app
 *
 * <p>
 *     Controls list creation, and list classification.
 *     It loads all the {@link com.example.projectmemory.ListContainer}s, and lets the user display them.
 *     Save lists containers onStop using {@link MainActivity#saveLists()} and SharedPreferences.
 *     Load list containers onCreate using {@link MainActivity#loadLists()}.
 * </p>
 *  @author Alex Lopez
 * */
public class MainActivity extends AppCompatActivity {
    ListContainer CommonLists;
    ListContainer ExpirationLists;
    public static final String EXP_JSON_DATA = "EXP_JSON_DATA";
    public static final String COMMON_JSON_DATA = "COMMON_JSON_DATA";
    public static final String APP_PREFS = "APPLICATION_PREFERENCES";
    /**
     * Create the main page and load the lists
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLists();

    }
    /**
     * Save the lists using SharedPreferences
     */
    @Override
    protected void onStop() {
        // call the superclass method first
        super.onStop();

        //Update and save all current lists in SharedPreferences
        saveLists();
    }
    /**
     * When the user press the button, create a new common list
     *
     * <p>
     *     Takes the user input from the EditText in {@link com.example.projectmemory.R.layout#activity_main}
     *     and use it as a name to crate a new {@link com.example.projectmemory.List} and add it to
     *     {@link com.example.projectmemory.MainActivity#CommonLists}
     * </p>
     *
     * @param view
     * @return {@link com.example.projectmemory.List}
     * */
    public void onCreateCommonList(View view){
        //Get list name
        EditText list = (EditText) findViewById(R.id.userInput);
        String listName = list.getText().toString();
        CommonLists.createList(listName);
        Toast.makeText(this, "List added to your Common lists", Toast.LENGTH_SHORT).show();
    }

    /**
     * Save all the {@link com.example.projectmemory.ListContainer}s
     *
     * <p>
     *     Serialize all the {@link com.example.projectmemory.ListContainer} objects into JSON
     *     objects and then use shared preferences to save them all.
     * </p>*/
    public void saveLists(){
        //TODO: Save all the ListContainers here
        //Use SharedPreferences to store all the lists
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Initialize JSON
        Gson gson = new Gson();

        //Prepare CommonLists JSON
        String json1 = gson.toJson(CommonLists);
        editor.putString(COMMON_JSON_DATA, json1);
        editor.apply();

        //Prepare ExpirationLists JSON
        String json2 = gson.toJson(ExpirationLists);
        editor.putString(EXP_JSON_DATA, json2);
        editor.apply();

    }
    /**
     * Load all the {@link com.example.projectmemory.ListContainer}s
     * <p>
     *     Deserialize all the JSON objects from SharedPreferences
     *     and load them as {@link com.example.projectmemory.ListContainer}s
     * </p>*/
    public void loadLists(){
        //TODO: Load all the ListContainers here.
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);

        //Load shared preferences
        String commonListContainers = sharedPreferences.getString(COMMON_JSON_DATA, null);
        String expirationListContainers = sharedPreferences.getString(EXP_JSON_DATA, null);

        //Deserialize
        Gson gson = new Gson();
        ListContainer savedCommonLists = gson.fromJson(commonListContainers, ListContainer.class);
        ListContainer savedExpirationLists = gson.fromJson(expirationListContainers, ListContainer.class);

        //Check for null
        if (savedCommonLists == null){
            CommonLists = new ListContainer();
        }
        else{
            this.CommonLists = savedCommonLists;
        }

        if (savedExpirationLists == null){
            ExpirationLists = new ListContainer();
        }
        else{
            this.ExpirationLists = savedExpirationLists;
        }
    }

    /**
     * Displays the {@link com.example.projectmemory.List}s from {@link MainActivity#CommonLists}
     *
     * <p>
     *     Serialize {@link MainActivity#CommonLists} to load it to the intend, so it can be accessed
     *     from {@link DisplayCommonLists}.
     * </p>
     * @param view
     */
    public void onDisplayCommonLists(View view){
        Intent intent = new Intent(this,DisplayCommonLists.class);

        //Prepare JSON CommonLists data
        Gson gson = new Gson();
        String json = gson.toJson(CommonLists);

        //Create intend
        intent.putExtra(COMMON_JSON_DATA,json);
        startActivity(intent);

    }
}

