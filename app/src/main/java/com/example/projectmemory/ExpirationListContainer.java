package com.example.projectmemory;

import java.util.ArrayList;

public class ExpirationListContainer extends ListContainer {
    ArrayList<List> expList;
    ArrayList<String> expNames;

    public ExpirationListContainer(){
        this.expList = new ArrayList<>();
        this.expNames = new ArrayList<>();
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
        expList.add(expired);
        expList.add(nextWeek);
        expList.add(next2Weeks);
        expList.add(nextMonth);
        expList.add(next3Months);
        expList.add(next6Months);
        expList.add(thisYear);
        
        //Add lists names to the names array
        for (List list:this.expList) {
            expNames.add(list.name);
        }
    }

}
