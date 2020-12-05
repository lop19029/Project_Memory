package com.example.projectmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateListActivity extends AppCompatActivity {

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

        //TODO: Make this add the new list to the commonLists container in Main Activity
        //some info about it on https://stackoverflow.com/questions/32466044/edit-array-from-another-activity
        //MainActivity.CommonLists.createList(listName);
        Toast.makeText(this, "List added to your Common lists", Toast.LENGTH_SHORT).show();
    }
}