package com.hooper.registrationscreen.interactors;

/**
 * Created by dpranavsai on 27-06-2015.
 */
public class ListDesignationInteractor {
    private static ListDesignationInteractor ourInstance = new ListDesignationInteractor();

    public static ListDesignationInteractor getInstance() {
        return ourInstance;
    }

    private ListDesignationInteractor() {
    }
}
