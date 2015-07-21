package com.hooper.registrationscreen.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hooper.registrationscreen.R;
import com.hooper.registrationscreen.presenters.RegisterPresenter;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class RegisterActivity extends Activity implements View.OnClickListener
{
    @InjectView(R.id.profilePictureCircleView)
    ImageView profilePicture;

    @InjectView(R.id.profileName)
    EditText name;

    @InjectView(R.id.mobileNumber)
    EditText mobile;

    @InjectView(R.id.joiningDatePicker)
    DatePicker datePicker;

    @InjectView(R.id.designationSpinner)
    Spinner spinner;

    @InjectView(R.id.dateTextView)
    TextView dateText;

    @InjectView(R.id.progress)
    ProgressBar progressBar;

    @InjectView(R.id.addMember)
    Button add;

    @InjectView(R.id.cancelRegister)
    Button cancel;

    String imagePath=null;

    public static final String RESULT_KEY="RESULT";

    private String[] designation = {
            "--Designation--",
            "Junior Software Engineer",
            "Senior Software Engineer",
            "Manager",
            "HR"
    };

    RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.inject(this);

        profilePicture.setOnClickListener(this);
        add.setOnClickListener(this);
        cancel.setOnClickListener(this);

        //initializing variables spinner and date picker
        initSpinner();
        initDatePicker();

        registerPresenter=new RegisterPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //on image pick activity result

    public void onActivityResult(int reqCode, int resCode, Intent data) {

        //data is passed as null to reset image to default image
        if (data == null)
        {
            profilePicture.setVisibility(View.VISIBLE);
            profilePicture.setImageResource(R.drawable.ic_launcher);
            return;
        }

        //request code is 1 for image picker event

        else if (reqCode == 1)
        {
            if (resCode == RESULT_OK) {
                if (reqCode == 1) {

                    imagePath = data.getData().toString();
                    profilePicture.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load(data.getData()).into(profilePicture);

                }
            }
        }

        //request code is 2 for List Designation class intent for drop down

        else if (reqCode == 2) {
            if (resCode == RESULT_OK) {
                int position = data.getIntExtra("position", 0);
                spinner.setSelection(position);
            }
            else if (resCode == RESULT_CANCELED)
            {
                Toast.makeText(getApplicationContext(), "Designation not Selected ",Toast.LENGTH_LONG).show();
            }
        }
    }

    //initialize all the variables

    private void initSpinner() {
        //adding designation drop down activity

        //adding array of elements to spinner data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, designation);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner.getSelectedItemPosition() != 0) {
                    Toast.makeText(getApplicationContext(), "Designation Selected : " + spinner.getSelectedItem().toString(),
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void  initDatePicker() {
        dateText.setText(" 1-1-2015.");
        datePicker.init(2015,0,1,
                new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String str = " " + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year + ".";
                        dateText.setText(str);
                    }
                });
    }

    //generating dialog to appear on the screen

    private void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    private void reset() {
        //  reset all the fields in the form to default values

        name.setText("");//text reset
        spinner.setSelection(0);//drop down reset
        //date reset
        dateText.setText(" 1-1-2015.");
        datePicker.init(2015, 0, 1,
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String str = " " + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year + ".";
                        dateText.setText(str);

                    }
                });
        mobile.setText("");

        //image reset
        imagePath=null;

        //passing null to reset profile picture
        onActivityResult(0,0,null);

        onBackPressed();
    }

    @Override
    public void onClick(View view) {
        if(view==add)
        {
            //verify all the fields
            //if all the fields are non-empty then add employee to database

            if(registerPresenter.verifyAndAdd(mobile.getText().toString(),
                    name.getText().toString(),
                    dateText.getText().toString(),
                    spinner.getSelectedItem().toString(),
                    imagePath)) {
                Toast.makeText(this, "Employee added", Toast.LENGTH_SHORT).show();
                reset();
            }

        }
        else if(view==cancel)
        {
            reset();
        }
        else if(view==profilePicture)
        {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Contact Image"), 1);
        }

    }

    public int NameError() {
        name.requestFocus();
        name.setError("Empty");
        return -1;

    }

    public int MobileNumberError() {
        mobile.requestFocus();
        mobile.setError("Empty");
        return -1;
    }

    public int ImageSelectionError() {
        profilePicture.requestFocus();
        showMessage("Error","Choose your display picture");
        return -1;
    }

    public int DesignationError() {
        spinner.requestFocus();
        showMessage("Error","Choose your designation");
        return -1;
    }

    public void navigateToDateAttendanceActivity(String str) {

        Intent dateAttendanceIntent = new Intent(getApplicationContext(), DateAttendanceActivity.class);

        //adding extra string to intent  to pass the query response

        dateAttendanceIntent.putExtra(RESULT_KEY,str);
        startActivity(dateAttendanceIntent);
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

     public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

}