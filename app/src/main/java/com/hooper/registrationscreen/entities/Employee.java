package com.hooper.registrationscreen.entities;

public class Employee
{

    //private variables
    int _id;
    String _name;
    long  _mobile;
    String _joining_date;
    String _designation;
    String _image_URI;

    // constructor
    public Employee(int id,long mobile, String name, String joiningDate,String designation,String imagePath){
        this._id = id;
        this._mobile=mobile;
        this._name = name;
        this._joining_date=joiningDate;
        this._designation = designation;
        this._image_URI=imagePath;
    }

    //Constructor
    public Employee(long mobile, String name, String joiningDate,String designation,String imagePath)
    {
        this._mobile=mobile;
        this._name = name;
        this._joining_date=joiningDate;
        this._designation = designation;
        this._image_URI=imagePath;
    }

    //Empty Constructor
    public Employee()
    {

    }

    // get ID
    public int getID(){
        return this._id;
    }

    // set ID
    public void setID(int id){
        this._id = id;
    }

    // get name
    public String getName(){
        return this._name;
    }

    // set name
    public void setName(String name){
        this._name = name;
    }

    // get joining date
    public String getJoiningDate(){
        return this._joining_date;
    }

    // set joining date
    public void setJoiningDate(String date){
        this._joining_date=date;
    }

    // get designation
    public String getDesignation(){
        return this._designation;
    }

    // set Designation
    public void setDesignation(String Designation){
        this._designation=Designation;
    }

    // get imagePath
    public String getImagePath(){
        return this._image_URI;
    }

    // set imagePath
    public void setImageURI(String imagePath){
        this._image_URI=imagePath;
    }

    //set mobile number
    public void setMobile(long mobile)
    {
        this._mobile=mobile;
    }

    //get mobile number
    public long getMobile()
    {
        return _mobile;
    }
}