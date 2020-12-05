package com.example.projectmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Displays the {@link com.example.projectmemory.List}s from {@link MainActivity#ExpirationLists}
 *
 * <p>
 *     Deserialize the JSON object coming from {@link MainActivity#onDisplayExpirationLists} and load
 *     each list name into the {@link DisplayExpirationListContainer#listView} using the {@link DisplayExpirationListContainer#adapter}
 *     Also handles user interaction with the list view using and event handler, and calling
 *     {@link DisplayExpirationListContainer#onDisplayExpirationList} when the user clicks on a list name.
 * </p>
 *
 * @author Alex Lopez
 */
public class DisplayExpirationListContainer extends AppCompatActivity {
    ExpirationLists expirationLists;
    private ListView listView;
    private ArrayAdapter adapter;
    public static final String EXP_LIST_DISPLAY = "DISPLAY_EXP"; //Used to create the intent

    /**
     * Deserialize the object coming from the intent.
     *
     * <p>
     *     Populate the list view and handles click on any item in it.
     * </p>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_expiration_lists);
        this.listView = (ListView) findViewById(R.id.expirationListView);
        this.setTitle("Expiration List");

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String listContainer = intent.getStringExtra(MainActivity.EXP_JSON_DATA);

        //Prepare JSON CommonLists data
        Gson gson = new Gson();
        this.expirationLists = gson.fromJson(listContainer, ExpirationLists.class);

        //Set List View
        ArrayList<String> lists = expirationLists.names;
        this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lists);
        listView.setAdapter(adapter);

        //Set event listener on list click
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onDisplayExpirationList(position);
            }
        });
    }
    /**
     * Display the items inside a list
     *
     * <p>
     *     Use the position of the clicked row of the ListView to find and display the correct list
     *     from {@link DisplayExpirationListContainer#expirationLists}
     * </p>
     * @param position
     */
    public void onDisplayExpirationList(int position){
        //Initialize intent
        Intent intent = new Intent(this,DisplayList.class);

        //Take called list
        List list = this.expirationLists.lists.get(position);

        //Serialize the list
        Gson gson = new Gson();
        String json = gson.toJson(list);

        //Add list to the intend
        intent.putExtra(EXP_LIST_DISPLAY,json);
        startActivity(intent);
    }
    }
