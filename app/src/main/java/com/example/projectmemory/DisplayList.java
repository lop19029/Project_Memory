package com.example.projectmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
    private ListView listView;
    List list;

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
        this.listView = (ListView) findViewById(R.id.listListView);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String listInfo = intent.getStringExtra(DisplayCommonLists.LIST);

        //Prepare JSON CommonLists data
        Gson gson = new Gson();
        this.list = gson.fromJson(listInfo,List.class);

        //Set the title of the list
        this.setTitle(this.list.name);

        //Set list view
        if(this.list.itemsNames == null) { //Avoid null pointer exception
            this.list.itemsNames = new ArrayList<>();
        }
        ArrayList<String> items = list.itemsNames;
        this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
    }
    /**
     * Add new {@link ListItem}s to this list
     */
    public void onAddNewItem(View view){
        EditText item = (EditText) findViewById(R.id.addNewItem); //Get item name
        String itemName = item.getText().toString();
        this.list.addItem(itemName);
        Toast.makeText(this, String.format("%s added to the list", itemName), Toast.LENGTH_SHORT).show();
    };
}