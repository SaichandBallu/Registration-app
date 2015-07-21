package com.hooper.registrationscreen.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hooper.registrationscreen.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by dpranavsai on 24-06-2015.
 */
public class ListDesignationActivity extends Activity
{
    @InjectView(R.id.designation_listView)
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        ButterKnife.inject(this);

        String[] values=getIntent().getExtras().getStringArray("StringArray");

        final ArrayList<String> list = new ArrayList<String>();

        //creating list of String elements with passes String Array

        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        //adding adapter to the list view

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.textview_layout,R.id.listFragmentTextView, list);
        listview.setAdapter(adapter);

        //setting listener to list view item click
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,int position, long id)
            {
                Intent data=new Intent();
                data.putExtra("position",position);

                //sending activity result through an intent

                setResult(RESULT_OK,data);
                onBackPressed();
            }
        });
    }
}

