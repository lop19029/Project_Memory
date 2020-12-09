package com.example.projectmemory;

/**
 * Let {@link CreateListActivity} access and edit {@link ListContainer}s in {@link MainActivity}
 */
public final class CreateListSingleton {
    private static final CreateListSingleton SELF = new CreateListSingleton();

    private ListContainer commonLists = new ListContainer();
    private ExpirationLists expirationLists = new ExpirationLists();

    private CreateListSingleton() {
        //Avoid that anyone create this Singleton
    }

    public static CreateListSingleton getInstance() {
        return SELF;
    }

    public ListContainer getCommonLists(){
        return commonLists;
    }

    public ExpirationLists getExpirationLists(){
        return expirationLists;
    }
}
