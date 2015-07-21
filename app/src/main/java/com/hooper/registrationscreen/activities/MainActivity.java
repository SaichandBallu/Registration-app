package com.hooper.registrationscreen.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hooper.registrationscreen.R;
import com.hooper.registrationscreen.presenters.MainPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends Activity implements View.OnClickListener
{
    @InjectView(R.id.hr)
    Button HR;

    @InjectView(R.id.checkin)
     Button CheckIn;

    @InjectView(R.id.checkout)
     Button CheckOut;

    @InjectView(R.id.register)
     Button Register;


    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        HR.setOnClickListener(this);
        Register.setOnClickListener(this);

        mainPresenter=new MainPresenter(this);
    }

    @Override
    public void onClick(View view) {
        if (view==HR)
        {
            String queryResponse=mainPresenter.Onclick_HR();

            //creating intent and adding query-response to intent
            Intent attendance_intent=new Intent(getApplicationContext(),DateAttendanceActivity.class);
            attendance_intent.putExtra(RegisterActivity.RESULT_KEY,queryResponse);
            startActivity(attendance_intent);
        }
        else if(view==Register)
        {
            mainPresenter.Onclick_Register();
        }
    }
    public void navigateToRegisterActivity()
    {
        Intent register_intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(register_intent);
    }
}