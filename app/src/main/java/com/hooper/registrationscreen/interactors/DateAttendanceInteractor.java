package com.hooper.registrationscreen.interactors;

import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.hooper.registrationscreen.R;
import com.hooper.registrationscreen.activities.DateAttendanceActivity;
import com.hooper.registrationscreen.presenters.DateAttendancePresenter;
import com.hooper.registrationscreen.sqlite.DatabaseHelper;

/**
 * Created by dpranavsai on 27-06-2015.
 */
public class DateAttendanceInteractor {

    DateAttendancePresenter dateAttendancePresenter;
    DateAttendanceActivity dateAttendanceActivity;

    public DateAttendanceInteractor(DateAttendancePresenter dateAttendancePresenter, DateAttendanceActivity dateAttendanceActivity) {
        this.dateAttendanceActivity = dateAttendanceActivity;
        this.dateAttendancePresenter = dateAttendancePresenter;
    }

    public TableLayout fillTable(String str,TableLayout tableLayout) {

        TableRow tableRow = new TableRow(dateAttendanceActivity);
        TextView textView;

        Log.d("MANUAL CHECKING","");
        Log.d("\nERR_NO_REC_FOUND :\n",DatabaseHelper.ERR_NO_REC_FOUND);
        Log.d("\nSTRING_PASSED :\n",str);

        if(str.equals(DatabaseHelper.ERR_NO_REC_FOUND) ||str==null) {

            textView=new TextView(dateAttendanceActivity);
            textView.setText("NO RECORDS FOUND.");
            TableRow tr=new TableRow(dateAttendanceActivity);
            tr.addView(textView);
            tableLayout.addView(tr);
        }
        else {
            //creating table Headers row

            for (int j = 1; j <= 5; j++) {
                textView = new TextView(dateAttendanceActivity);
                GradientDrawable gd = new GradientDrawable();
                gd.setStroke(2, R.color.black);

                textView.setBackground(gd);
                textView.setTextSize((float) 20);
                textView.setTextColor(R.color.black);
                textView.setPadding(10, 10, 10, 10);

                switch (j) {
                    case 1:
                        textView.setText("ID");
                        break;
                    case 2:
                        textView.setText("MOBILE");
                        break;
                    case 3:
                        textView.setText("NAME");
                        break;
                    case 4:
                        textView.setText("JOINING");
                        break;
                    case 5:
                        textView.setText("DESIGNATION");
                        break;

                }
                tableRow.addView(textView);
            }
            tableLayout.addView(tableRow);

            //filling the table cells with corresponding details of registered members

            String[] rows = str.split("\n");

            for (int i = 0; i < rows.length; i++) {
                String[] temp = rows[i].split("\t");
                tableRow = new TableRow(dateAttendanceActivity);
                for (int j = 0; j < temp.length - 1; j++) {
                    textView = new TextView(dateAttendanceActivity);
                    GradientDrawable gd = new GradientDrawable();
                    gd.setStroke(2, R.color.black);
                    textView.setBackground(gd);

                    textView.setTextSize((float) 20);
                    textView.setTextColor(R.color.black);

                    textView.setText(temp[j]);
                    textView.setPadding(10, 10, 10, 10);

                    //adding cell as text view to the table
                    tableRow.addView(textView);
                }
                //adding row view to the table
                tableLayout.addView(tableRow);
            }
        }
        return tableLayout;
    }
}