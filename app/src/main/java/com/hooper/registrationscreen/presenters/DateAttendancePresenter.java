package com.hooper.registrationscreen.presenters;

import android.widget.TableLayout;

import com.hooper.registrationscreen.activities.DateAttendanceActivity;
import com.hooper.registrationscreen.interactors.DateAttendanceInteractor;

/**
 * Created by dpranavsai on 27-06-2015.
 */
public class DateAttendancePresenter {

    DateAttendanceActivity dateAttendanceActivity;
    DateAttendanceInteractor dateAttendanceInteractor;

    public DateAttendancePresenter(DateAttendanceActivity dateAttendanceActivity) {
       this.dateAttendanceActivity=dateAttendanceActivity;
        dateAttendanceInteractor=new DateAttendanceInteractor(this,dateAttendanceActivity);
    }

    public TableLayout fillTable(String str,TableLayout tableLayout){
        return dateAttendanceInteractor.fillTable(str,tableLayout);
    }
}