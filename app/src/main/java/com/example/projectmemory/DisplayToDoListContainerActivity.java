package com.example.projectmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Displays the {@link com.example.projectmemory.List}s from {@link MainActivity#ToDoLists}
 *
 * <p>
 *     Deserialize the JSON object coming from {@link MainActivity#onDisplayToDoLists} and load
 *     each list name into the {@link DisplayToDoListContainerActivity#listView} using the
 *     {@link DisplayToDoListContainerActivity#adapter}
 *     Also handles user interaction with the list view using and event handler, and calling
 *     {@link DisplayToDoListContainerActivity#onDisplayToDoList} when the user clicks on a list name.
 * </p>
 *
 * @author Alex Lopez
 */

public class DisplayToDoListContainerActivity extends AppCompatActivity {
    public static final String TO_DO_LIST = "TO_DO_LIST"; //Used to create the intent
    private ArrayAdapter adapter;
    private SwipeMenuListView listView;
    ListContainer toDoLists;

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
        setContentView(R.layout.activity_display_to_do_list_container);
        this.listView = (SwipeMenuListView) findViewById(R.id.toDoListsListView);
        this.setTitle("To Do Lists");

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String listContainer = intent.getStringExtra(MainActivity.TO_DO_JSON_DATA);

        //Prepare JSON CommonLists data
        Gson gson = new Gson();
        this.toDoLists = gson.fromJson(listContainer, ListContainer.class);

        //Set List View
        if(this.toDoLists.names == null){
            this.toDoLists.names = new ArrayList<>();
        }
        ArrayList<String> lists = toDoLists.names;
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lists);
        listView.setAdapter(adapter);

        //Handle delete Lists by Swiping
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xEF,
                        0x23, 0x3C)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_baseline_delete_24);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        onDeleteToDoList(position);

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        //Set event listener on list click
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onDisplayToDoList(position);
            }
        });

    }
    /**
     * Display the items inside a list
     *
     * <p>
     *     Use the position of the clicked row of the ListView to find and display the correct list
     *     from {@link DisplayToDoListContainerActivity#toDoLists}
     * </p>
     * @param position
     */
    public void onDisplayToDoList(int position){

    }
    /**
     * Permanently deletes a {@link List}
     * @param position
     */
    public void onDeleteToDoList(int position){
        //delete list
        String listName = toDoLists.names.get(position);
        MainActivity.ToDoLists.deleteList(position);
        toDoLists.deleteList(position);

        //Close the activity to reload
        finish();

        //Notify the user
        Toast.makeText(this, listName + " was permanently deleted", Toast.LENGTH_SHORT).show();
    }
}