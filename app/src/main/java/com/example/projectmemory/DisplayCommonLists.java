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
 * Displays the {@link com.example.projectmemory.List}s from {@link MainActivity#CommonLists}
 *
 * <p>
 *     Deserialize the JSON object coming from {@link MainActivity#onDisplayCommonLists} and load
 *     each list name into the {@link DisplayCommonLists#listView} using the {@link DisplayCommonLists#adapter}
 *     Also handles user interaction with the list view using and event handler, and calling
 *     {@link DisplayCommonLists#onDisplayList} when the user clicks on a list name.
 * </p>
 *
 * @author Alex Lopez
 */
public class DisplayCommonLists extends AppCompatActivity {
    public static final String LIST = "LIST_INFO"; //Used to create the intent
    private ArrayAdapter adapter;
    private ListView listView;
    ListContainer commonLists;

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
        setContentView(R.layout.activity_display_common_lists);
        this.listView = (ListView) findViewById(R.id.listView);
        this.setTitle("Common Lists");

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String listContainer = intent.getStringExtra(MainActivity.COMMON_JSON_DATA);

        //Prepare JSON CommonLists data
        Gson gson = new Gson();
        this.commonLists = gson.fromJson(listContainer, ListContainer.class);

        //Set List View
        ArrayList<String> lists = commonLists.names;
        this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lists);
        listView.setAdapter(adapter);

        //Set event listener on list click
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onDisplayList(position);
            }
        });
    }

    /**
     * Display the items inside a list
     *
     * <p>
     *     Use the position of the clicked row of the ListView to find and display the correct list
     *     from {@link DisplayCommonLists#commonLists}
     * </p>
     * @param position
     */
    public void onDisplayList(int position){
        //Initialize intent
        Intent intent = new Intent(this,DisplayList.class);

        //Take called list
        List list = this.commonLists.lists.get(position);

        //Serialize the list
        Gson gson = new Gson();
        String json = gson.toJson(list);

        //Add list to the intend
        intent.putExtra(LIST,json);
        startActivity(intent);
    }

}