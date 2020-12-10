package com.example.projectmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Handle display and creation of {@link com.example.projectmemory.ListItem}s
 *
 * <p>
 *     Receive a specific list from {@link DisplayCommonLists} and display all the
 *     {@link com.example.projectmemory.ListItem}s from inside it.
 *     Also let the user enter new items to the list or delete them. All the items are saved using
 *     SharedPreferences.
 * </p>
 *
 * @author Alex Lopez
 */

//TODO: Save new items using SharedPreferences and display them

public class DisplayList extends AppCompatActivity {
    private ArrayAdapter adapter;
    private SwipeMenuListView listView;
    List list;
    public static final String LIST_JASON = "LIST_JASON";
    public static final String APP_PREFS = "APPLICATION_PREFERENCES";
    /**
     * Populate the list view
     *
     * <p>
     *     Deserialize the object coming from {@link DisplayCommonLists#onDisplayList} and display
     *     each {@link ListItem} of it.
     * </p>
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);
        this.listView = (SwipeMenuListView) findViewById(R.id.listListView);


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String listInfo = intent.getStringExtra(DisplayCommonLists.LIST);

        //Prepare JSON CommonLists data
        Gson gson = new Gson();
        this.list = gson.fromJson(listInfo,List.class);
        loadItems();
        //Set the title of the list
        this.setTitle(this.list.name);

        //Set list view
        if(this.list.itemsNames == null) { //Avoid null pointer exception
            this.list.itemsNames = new ArrayList<>();
        }
        ArrayList<String> items = list.itemsNames;
        this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        //Handle delete item by swiping
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
                        onDeleteItem(position);

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }

    /**
     * Save items
     */
    @Override
    protected void onStop() {
        super.onStop();
        saveItems();
    }

    /**
     * Use SharedPreferences to save all the items inside the List
     */
    public void saveItems(){
        //Use SharedPreferences to store all the lists
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Initialize JSON
        Gson gson = new Gson();

        //Prepare List JSON
        String json1 = gson.toJson(list);
        editor.putString(list.name, json1);
        editor.apply();
    }

    /**
     * Use SharedPreferences to load all the items inside a List
     */
    public void loadItems(){
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);

        //Load shared preferences
        String commonList = sharedPreferences.getString(this.list.name, null);

        //Deserialize
        Gson gson = new Gson();
        List savedItems = gson.fromJson(commonList, List.class);


        //Check for null
        if (savedItems == null){
            //Do nothing
        }
        else{
            this.list = savedItems;
        }
    }

    /**
     * Add new {@link ListItem}s to this list
     */
    public void onAddNewItem(View view){
        EditText item = (EditText) findViewById(R.id.addNewItem); //Get item name
        String itemName = item.getText().toString();
        this.list.addItem(itemName);
        Toast.makeText(this, String.format("%s added to the list", itemName), Toast.LENGTH_SHORT).show();
    }

    /**
     * Deletes an Item from this List
     * @param position
     */
    public void onDeleteItem(int position){
        //TODO: Avoid the deleted items from loading with SharedPreferences
        String itemName = this.list.itemsNames.get(position);
        this.list.deleteItem(position);
        finish();
        startActivity(getIntent());
        Toast.makeText(this, itemName + " deleted", Toast.LENGTH_SHORT).show();
    }
}
