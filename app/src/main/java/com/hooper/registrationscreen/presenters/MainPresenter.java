package com.hooper.registrationscreen.presenters;

import com.hooper.registrationscreen.activities.IMainListener;
import com.hooper.registrationscreen.activities.MainActivity;
import com.hooper.registrationscreen.interactors.MainInteractor;

/**
 * Created by dpranavsai on 27-06-2015.
 */
public class MainPresenter implements IMainListener
{
    private final MainActivity mainActivity;
    private final MainInteractor mainInteractor;

    public MainPresenter(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
        mainInteractor = new MainInteractor(this, mainActivity);
    }

    public String Onclick_HR()
    {
        return mainInteractor.getEmpList();
    }

    public void Onclick_Register() {
        mainInteractor.RegisterClicked();
    }

    @Override
    public void onRegisterClick() {
        mainActivity.navigateToRegisterActivity();
    }
}
