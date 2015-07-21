package com.hooper.registrationscreen.interactors;

import com.hooper.registrationscreen.activities.IMainListener;
import com.hooper.registrationscreen.activities.MainActivity;
import com.hooper.registrationscreen.sqlite.DatabaseHelper;

/**
 * Created by dpranavsai on 27-06-2015.
 */
public class MainInteractor {
    private final IMainListener listener;
    MainActivity mainActivity;

    public MainInteractor(IMainListener listener, MainActivity mainActivity)
    {
        this.mainActivity=mainActivity;
        this.listener=listener;
    }

    public String getEmpList() {
        DatabaseHelper myDatabaseHelper=new DatabaseHelper(mainActivity);
        return  myDatabaseHelper.listAll();
    }

    public void RegisterClicked() {
        listener.onRegisterClick();
    }
}
