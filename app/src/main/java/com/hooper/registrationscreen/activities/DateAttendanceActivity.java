package com.hooper.registrationscreen.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;

import com.hooper.registrationscreen.R;
import com.hooper.registrationscreen.presenters.DateAttendancePresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by dpranavsai on 25-06-2015.
 */
public class DateAttendanceActivity extends Activity
{

    @InjectView(R.id.table_attendance)
    TableLayout tableLayout;

    private String registeredMembersList;
    DateAttendancePresenter dateAttendancePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_attendance);

        ButterKnife.inject(this);

        //retrieving data of all registered members from intent generator into a string

        registeredMembersList=getIntent().getExtras().getString(RegisterActivity.RESULT_KEY);

        Log.d("Res :@DateAttendance\n",registeredMembersList);

        //filling data into table if input is not empty

        dateAttendancePresenter = new DateAttendancePresenter(this);

        showTable();
    }

    private void showTable() {
        try {
            dateAttendancePresenter.fillTable(registeredMembersList,tableLayout);
        }
        catch (NullPointerException e) {
            Log.d("Null :\n",e.getMessage()+"\t"+e.getStackTrace().toString());
        }
    }
}
