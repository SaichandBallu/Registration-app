package com.hooper.registrationscreen.presenters;

import com.hooper.registrationscreen.activities.IRegisterListener;
import com.hooper.registrationscreen.activities.RegisterActivity;
import com.hooper.registrationscreen.interactors.RegisterInteractor;

/**
 * Created by dpranavsai on 26-06-2015.
 */
public class RegisterPresenter implements IRegisterListener
{
    RegisterActivity registerActivity;
    RegisterInteractor registerInteractor;

    public RegisterPresenter(RegisterActivity registerActivity)
    {
        this.registerActivity=registerActivity;
        registerInteractor=new RegisterInteractor(this,registerActivity);
    }

    public boolean verifyAndAdd(String phone, String name, String date, String designation, String imagePath)
    {
        return registerInteractor.verifyFields(phone, name, date, designation, imagePath);
    }

    @Override
    public int NameError() {

        return registerActivity.NameError();
    }

    @Override
    public int MobileNumberError() {

        return registerActivity.MobileNumberError();
    }

    @Override
    public int ImageSelectionError() {

        return registerActivity.ImageSelectionError();
    }

    @Override
    public int DesignationError() {

        return registerActivity.DesignationError();
    }

    @Override
    public void onSuccess(String str) {
        registerActivity.navigateToDateAttendanceActivity(str);

    }
}
