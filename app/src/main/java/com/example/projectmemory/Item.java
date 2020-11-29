package com.example.projectmemory;

public class Item {
    String name;
    boolean checked= false;
    boolean repeating= true; //TODO decide if repeating is default
    Alarm alarm;

    public void setName(String name) {
        this.name = name;
    }

    Item convert(ListType type) {
        Item converted;
        switch (type) {
            case Todo:
                converted = new Item();
                break;
            case Shopping:
                converted = new FoodItem();
                break;
            case Budget:
                converted = new BudgetItem();
                break;
            case Expandable:
                converted = new ExpandableItem();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        converted.copy(this);
        return converted;
    }

    protected void copy(Item i){
        this.name = i.name;
        this.checked = i.checked;
        this.repeating = i.repeating;
        this.alarm = i.alarm; //TODO is this an issue?
    }

}
