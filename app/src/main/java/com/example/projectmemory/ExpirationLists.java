package com.example.projectmemory;

import java.util.ArrayList;

public class ExpirationLists extends ListContainer {
    ArrayList<List> lists;
    ArrayList<String> names;

    public ExpirationLists(){
        this.lists = new ArrayList<>();
        this.names = new ArrayList<>();
        populateLists();
    }
    private void populateLists(){
        //Create lists
        List expired = new List("Expired");
        List nextWeek = new List("Next week");
        List next2Weeks = new List("Next 2 weeks");
        List nextMonth = new List("Next month");
        List next3Months = new List("Next 3 months");
        List next6Months = new List("Next 6 months");
        List thisYear = new List("This year");
        
        //Add lists to lists array
        lists.add(expired);
        lists.add(nextWeek);
        lists.add(next2Weeks);
        lists.add(nextMonth);
        lists.add(next3Months);
        lists.add(next6Months);
        lists.add(thisYear);
        
        //Add lists names to the names array
        for (List list:this.lists) {
            names.add(list.name);
        }
    }
}
