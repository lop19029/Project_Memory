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

        //Use singleton to modify CommonLists in MainActivity
        MainActivity.CommonLists.createList(listName);

        //MainActivity.CommonLists.createList(listName);
        Toast.makeText(this, listName + " added to your Other Lists", Toast.LENGTH_SHORT).show();
    }
}