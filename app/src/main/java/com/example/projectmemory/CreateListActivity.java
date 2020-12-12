package com.example.projectmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateListActivity extends AppCompatActivity {
    /**
     * Handles List creation and classification
     *
     * <p>
     *     Accepts user input to give a name to the new list. Creates a new List with that name.
     *     The List is saved in {@link MainActivity#OtherLists}, {@link MainActivity#ToDoLists} or
     *     ShoppingList depending on which button the user press to create the list.
     * </p>
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        this.setTitle("Create a new list");
    }
    /**
     * When the user press the button, create a new common list
     *
     * <p>
     *     Takes the user input from the EditText in {@link com.example.projectmemory.R.layout#activity_create_list}
     *     and use it as a name to crate a new {@link com.example.projectmemory.List} and add it to
     *     {@link com.example.projectmemory.MainActivity#OtherLists}
     * </p>
     *
     * @param view
     * @return {@link com.example.projectmemory.List}
     * */
    public void onCreateOtherList(View view) {
        //Get list name
        EditText list = (EditText) findViewById(R.id.userInput);
        String listName = list.getText().toString();

        //Create List
        MainActivity.OtherLists.createList(listName);

        //Notify the user
        Toast.makeText(this, listName + " added to your Other Lists", Toast.LENGTH_SHORT).show();
    }
    /**
     * When the user press the button, create a new To Do list
     *
     * <p>
     *     Takes the user input from the EditText in {@link com.example.projectmemory.R.layout#activity_create_list}
     *     and use it as a name to crate a new {@link com.example.projectmemory.List} and add it to
     *     {@link com.example.projectmemory.MainActivity#ToDoLists}
     * </p>
     *
     * @param view
     * @return {@link com.example.projectmemory.List}
     * */
    public void onCreateToDoList(View view) {
        //Get list name
        EditText list = (EditText) findViewById(R.id.userInput);
        String listName = list.getText().toString();

        //Create List
        MainActivity.ToDoLists.createList(listName);

        //Notify the user
        Toast.makeText(this, listName + " added to your To Do Lists", Toast.LENGTH_SHORT).show();
    }
}