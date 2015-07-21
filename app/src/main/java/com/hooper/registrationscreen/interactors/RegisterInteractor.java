package com.hooper.registrationscreen.interactors;

import com.hooper.registrationscreen.activities.IRegisterListener;
import com.hooper.registrationscreen.activities.RegisterActivity;
import com.hooper.registrationscreen.entities.Employee;
import com.hooper.registrationscreen.sqlite.DatabaseHelper;

/**
 * Created by dpranavsai on 26-06-2015.
 */
public class RegisterInteractor
{
    IRegisterListener registerPresenter;
    RegisterActivity registerActivity;
    DatabaseHelper myDatabaseHelper;

    public RegisterInteractor(IRegisterListener registerPresenter,RegisterActivity registerActivity)
    {
        this.registerActivity=registerActivity;
        this.registerPresenter=registerPresenter;
        myDatabaseHelper=new DatabaseHelper(registerActivity);
    }

    //verifying the fields

    public boolean verifyFields(String phone, String name, String date, String designation, String imagePath)
    {
          //checking profile picture
          boolean check = true;
          if (imagePath == null) {
              registerPresenter.ImageSelectionError();
              check = false;
          }
          //checking name
          else if (name.length() == 0) {
              registerPresenter.NameError();
              check = false;
          }
          //checking mobile number
          else if (phone.length() == 0) {
              registerPresenter.MobileNumberError();
              check = false;
          }
          //checking designation
          else if (designation.equals("--Designation--")) {
              registerPresenter.DesignationError();
              check = false;
          }
          //adding member to table and displaying all registered members
          if (check) {
              Long phoneNumber = Long.parseLong(phone);
              add(phoneNumber, name, date, designation, imagePath);
              String list = getAllEmployeesList();
              registerPresenter.onSuccess(list);
          }
          return check;
    }

    //adding employee to database

    public void add(Long mobile,String name,String date,String designation,String imagePath)
    {
        //creating employee class with user entered data

        Employee emp = new Employee(mobile,name,date,designation,imagePath);

        //adding employee
        myDatabaseHelper.addEmployee(emp);
    }

    public String getAllEmployeesList() {
        //attempt to retrieve data from Employees table
             return myDatabaseHelper.listAll();
    }
}
