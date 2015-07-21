package com.hooper.registrationscreen.activities;

/**
 * Created by Saichand Ballu on 13-07-2015.
 */
public interface IRegisterListener {
    public int NameError();
    public int MobileNumberError();
    public int ImageSelectionError();
    public int DesignationError();
    public void onSuccess(String str);
}
