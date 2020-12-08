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
import java.util.Calendar;
import java.util.Date;

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
    ArrayList<FoodItem> expItems;
    ExpirationLists expirationLists;
    private ListView listView;
    private ArrayAdapter adapter;
    public static final String EXP_LIST_DISPLAY = "DISPLAY_EXP"; //Used to create the intent

    /**
     * Deserialize the object coming from the intent.
     *
     * <p>
     * Populate the list view and handles click on any item in it.
     * </p>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_expiration_lists);
        this.listView = (ListView) findViewById(R.id.expirationListView);
        this.setTitle("Expired Items");

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String listContainer = intent.getStringExtra(MainActivity.EXP_JSON_DATA);

        //Prepare JSON CommonLists data
        Gson gson = new Gson();
        this.expirationLists = gson.fromJson(listContainer, ExpirationLists.class);

        //Set List View
        ArrayList<String> lists = expirationLists.expNames;
        this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lists);
        listView.setAdapter(adapter);

        //Put every FoodItem in its correct list
        //sortFoodItems();

        //Set event listener on list click
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onDisplayExpirationList(position);
            }
        });

    }

    /** Sorts the Food Items that cointain and expiration date*/
    private void sortFoodItems(){
        //Create dates
        DateManipulator dm = new DateManipulator();
        Date today = new Date();
        Date nextWeek = dm.addDays(today, 5);
        Date next2Weeks = dm.addDays(today, 15);
        Date nextMonth = dm.addDays(today, 30);
        Date next3Months = dm.addDays(today, 90);
        Date next6Months = dm.addDays(today, 180);
        Date thisYear = dm.addYears(today, 1);
        for (FoodItem fi:expItems) {
            if(fi.expiration.before(today)) {
                List list = expirationLists.expList.get(0);
                list.addItem(fi.name);
            }
            else if(fi.expiration.before(nextWeek)){
                List list = expirationLists.expList.get(1);
                list.addItem(fi.name);
            }
            else if(fi.expiration.before(next2Weeks)){
                List list = expirationLists.expList.get(2);
                list.addItem(fi.name);
            }
            else if(fi.expiration.before(nextMonth)){
                List list = expirationLists.expList.get(3);
                list.addItem(fi.name);
            }
            else if(fi.expiration.before(next3Months)){
                List list = expirationLists.expList.get(4);
                list.addItem(fi.name);
            }
            else if(fi.expiration.before(next6Months)){
                List list = expirationLists.expList.get(5);
                list.addItem(fi.name);
            }
            else if(fi.expiration.before(thisYear)){
                List list = expirationLists.expList.get(6);
                list.addItem(fi.name);
            }
        }
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
        Intent intent = new Intent(this,DisplayExpirationList.class);

        //Take called list
        List list = this.expirationLists.expList.get(position);

        //Serialize the list
        Gson gson = new Gson();
        String json = gson.toJson(list);

        //Add list to the intend
        intent.putExtra(EXP_LIST_DISPLAY,json);
        startActivity(intent);
    }
    }

