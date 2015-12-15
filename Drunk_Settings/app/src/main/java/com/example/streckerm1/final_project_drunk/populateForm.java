package com.example.streckerm1.final_project_drunk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by burnsj7 on 12/3/2015.
 */
public class populateForm extends Activity {
    private EditText locationScrollview;
    private EditText timeScrollview;
    private EditText withYouScrollview;
    private LinearLayout LinearFormView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        locationScrollview = (EditText) findViewById(R.id.location_scrollview);
        timeScrollview = (EditText) findViewById(R.id.time_scrollview);
        withYouScrollview = (EditText) findViewById(R.id.with_you_scrollview);
        LinearFormView = (LinearLayout) findViewById(R.id.LinearFormView);

        importStrings();

    }
    public void importStrings() {
        String LocationString;
        String TimeString;
        String PeopleString;

        Bundle extras = getIntent().getExtras();
        LocationString = extras.getString("location");
        TimeString = extras.getString("time");
        PeopleString = extras.getString("people");
        locationScrollview.setText(LocationString, EditText.BufferType.EDITABLE);
        timeScrollview.setText(TimeString, EditText.BufferType.EDITABLE);
        withYouScrollview.setText(PeopleString, EditText.BufferType.EDITABLE);
    }


}


