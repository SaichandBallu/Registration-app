package com.hooper.registrationscreen.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hooper.registrationscreen.entities.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String TABLE_EMP = "Employees";

    public static final String COL_ID = "row_id";

    public static final String COL_MOBILE="mobile";

    public static final String COL_NAME = "name";

    public static final String COL_DATE = "joining_date";

    public static final String COL_DESIGNATION = "designation";

    public static final String COL_IMAGE_URI = "imageURI";

    private static final String DATABASE_NAME = "employeeManager.db";

    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase sqLiteDatabase;

    private String exceptionMessage=null;

    //create table statement for employees table

    private static final String CREATE_TABLE_EMP="CREATE TABLE " + TABLE_EMP + " ("

            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"

            + COL_MOBILE + " INTEGER UNIQUE NOT NULL,"

            + COL_NAME + " TEXT NOT NULL,"

            + COL_DATE + " TEXT NOT NULL,"

            + COL_DESIGNATION + " TEXT NOT NULL,"

            + COL_IMAGE_URI + " TEXT NOT NULL"

            + ");";

    private static final String SELECT_ALL_TABLE_EMP=" select * from " + TABLE_EMP ;

    public static final String ERR_NO_REC_FOUND=" No records found.";

    //Constructor

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_EMP);
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMP + ";");

        onCreate(db);

    }

    // Adding new Employee

    public String addEmployee(Employee emp) throws SQLException {
        open();

        //creating content values class with employee data to inert into database

        ContentValues values = new ContentValues();
        values.put(COL_NAME, emp.getName());
        values.put(COL_MOBILE,emp.getMobile());
        values.put(COL_DESIGNATION, emp.getDesignation());
        values.put(COL_DATE, emp.getJoiningDate());
        values.put(COL_IMAGE_URI, emp.getImagePath());

        // Inserting Row
        try {
            sqLiteDatabase.insert(TABLE_EMP, null, values);
        }
        catch(SQLiteConstraintException e)
        {
            exceptionMessage+= Arrays.toString(e.getStackTrace()) +"\n"+e.getCause()+"\n"+e.getMessage();
            return exceptionMessage;
        }
        return "done";
    }

    //open or create existing writable database

    public void open() throws SQLException
    {
        sqLiteDatabase = this.getWritableDatabase();
    }

    //send all employees list back in a string form
    public String listAll() {
        open();

        Cursor c=sqLiteDatabase.rawQuery(SELECT_ALL_TABLE_EMP, null);

        if(c.getCount()==0)
        {
            return ERR_NO_REC_FOUND;
        }

        //Adding Cursor response to String

        String str="";
        c.moveToFirst();
        do
        {
            //reading row-wise data from cursor

            str+=c.getString(0)+"\t"+ c.getString(1)+"\t"+ c.getString(2)+"\t"+c.getString(3)+"\t"+c.getString(4)+"\t"+c.getString(5)+"\n";

        }while(c.moveToNext());

        sqLiteDatabase.close();

        //return Query response in String format
        return str;
    }

    // Getting All Employees

    public List<Employee> getEmployeesList() {
        List<Employee> EmpList = new ArrayList<Employee>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EMP;

        open();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do {
                Employee e = new Employee();
                e.setID(Integer.parseInt(cursor.getString(0)));
                e.setMobile(Long.parseLong(cursor.getString(1)));
                e.setName(cursor.getString(2));
                e.setJoiningDate(cursor.getString(2));
                e.setDesignation(cursor.getString(3));
                e.setImageURI(cursor.getString(4));

                // Adding contact to list
                EmpList.add(e);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();

        // return contact list
        return EmpList;
    }

    //inserting a row into table

    public long insert(String tableName, ContentValues values) {
        open();

        return sqLiteDatabase.insert(tableName, null, values);

    }

    //updating an existing row

    public int update(String tableName, long id, ContentValues values) {

        open();

        String selection = COL_ID + " = ?";

        String[] selectionArgs = {String.valueOf(id)};

        return sqLiteDatabase.update(tableName, values, selection, selectionArgs);

    }

    //deleting a row

    public int delete(String tableName, long id) {
        open();

        String selection = COL_ID + " = ?";

        String[] selectionArgs = {String.valueOf(id)};

        return sqLiteDatabase.delete(tableName, selection, selectionArgs);

    }

}
