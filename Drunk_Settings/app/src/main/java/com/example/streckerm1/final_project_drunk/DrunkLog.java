package com.example.streckerm1.final_project_drunk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by streckerm1 on 12/3/2015.
 */
public class DrunkLog extends Activity implements View.OnClickListener {

    private EditText formLocation;
    private EditText formTime;
    private EditText formPeople;
    private Button formSubmit;
    private LinearLayout linearLayout;
    private ScrollView LogScrollView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drunk_log_form);


        linearLayout = (LinearLayout)findViewById( R.id.LinearFormView );
        formLocation = (EditText) findViewById(R.id.form_location);
        formTime = (EditText) findViewById(R.id.form_time);
        formPeople = (EditText) findViewById(R.id.form_people);
        formSubmit = (Button) findViewById(R.id.form_submit);
        LogScrollView = (ScrollView) findViewById(R.id.LogScrollView);
        formSubmit.setOnClickListener(this);
    }


        public void setFormSubmit() {
        EditText LocationTxt = formLocation;
        EditText TimeTxt = formTime;
        EditText PeopleTxt = formPeople;

        String location = LocationTxt.getText().toString();
        String time = TimeTxt.getText().toString();
        String people = PeopleTxt.getText().toString();

        Intent intent = new Intent(DrunkLog.this, populateForm.class);
        intent.putExtra("location", location);
        intent.putExtra("time", time);
        intent.putExtra("people", people);
        startActivity(intent);

    }

    public void populateScrollView() {
        LogScrollView.addView(linearLayout);
    }


    @Override
    public void onClick(View v) {
        if ( v == formSubmit ) {
            setFormSubmit();
            populateScrollView();
        }
    }
}
