package com.example.projectmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

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
    public static ListContainer OtherLists;
    public ExpirationListContainer ExpirationListContainer;
    public static final String EXP_JSON_DATA = "EXP_JSON_DATA";
    public static final String COMMON_JSON_DATA = "COMMON_JSON_DATA";
    public static final String APP_PREFS = "APPLICATION_PREFERENCES";
    /**
     * Create the main page and load the lists
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Recall.it");
        setContentView(R.layout.activity_main);
        loadLists();

       FloatingActionButton fab = findViewById(R.id.mainFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateListActivity(view);
            }
        });
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
     * Calls {@link CreateListActivity}
     *
     * <p>
     *     on click, the FAB in {@link com.example.projectmemory.R.layout#activity_main} calls this
     *     method.
     * </p>
     * @param view
     */
    public void onCreateListActivity(View view) {
        Intent intent = new Intent(this, CreateListActivity.class);
        startActivity(intent);
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
        String json1 = gson.toJson(OtherLists);
        editor.putString(COMMON_JSON_DATA, json1);
        editor.apply();

        //Prepare ExpirationLists JSON
        String json2 = gson.toJson(ExpirationListContainer);
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
        ExpirationListContainer savedExpirationListContainer = gson.fromJson(expirationListContainers, ExpirationListContainer.class);

        //Check for null
        if (savedCommonLists == null){
            OtherLists = new ListContainer();
        }
        else{
            this.OtherLists = savedCommonLists;
        }

        if (savedExpirationListContainer == null){
            ExpirationListContainer = new ExpirationListContainer();
        }
        else{
            this.ExpirationListContainer = savedExpirationListContainer;
        }
    }

    /**
     * Displays the {@link com.example.projectmemory.List}s from {@link MainActivity#OtherLists}
     *
     * <p>
     *     Serialize {@link MainActivity#OtherLists} to load it to the intend, so it can be accessed
     *     from {@link DisplayOtherListContainerActivity}.
     * </p>
     * @param view
     */
    public void onDisplayCommonLists(View view){
        Intent intent = new Intent(this, DisplayOtherListContainerActivity.class);

        //Prepare JSON CommonLists data
        Gson gson = new Gson();
        String json = gson.toJson(OtherLists);

        //Create intend
        intent.putExtra(COMMON_JSON_DATA,json);
        startActivity(intent);

    }

    public void onDisplayExpirationLists(View view){
        Intent intent = new Intent(this, DisplayExpirationListContainerActivity.class);

        //Prepare JSON ExpirationLists data
        Gson gson = new Gson();
        String json = gson.toJson(ExpirationListContainer);

        //Create intend
        intent.putExtra(EXP_JSON_DATA, json);
        startActivity(intent);
    }
}

