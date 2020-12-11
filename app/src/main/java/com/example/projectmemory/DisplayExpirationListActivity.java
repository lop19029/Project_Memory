package com.example.projectmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Handle display of {@link com.example.projectmemory.FoodItem}s with an expiration date
 *
 * <p>
 *     Receive a specific list from {@link DisplayExpirationListContainerActivity} and display all the
 *     {@link com.example.projectmemory.FoodItem}s from inside it.
 *     All the items are saved using SharedPreferences.
 * </p>
 *
 * @author Alex Lopez
 */
public class DisplayExpirationListActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    private ListView listView;
    List list;

    /**
     * Populate the list view
     *
     * <p>
     *     Deserialize the object coming from {@link DisplayExpirationListContainerActivity#onDisplayExpirationList} and display
     *     each {@link FoodItem} of it.
     * </p>
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_expiration_list);
        this.listView = (ListView) findViewById(R.id.expirationItemsListView);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String listInfo = intent.getStringExtra(DisplayExpirationListContainerActivity.EXP_LIST_DISPLAY);

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
}