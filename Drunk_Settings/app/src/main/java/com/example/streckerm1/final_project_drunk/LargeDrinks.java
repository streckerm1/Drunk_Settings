package com.example.streckerm1.final_project_drunk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by streckerm1 on 12/2/2015.
 */
public class LargeDrinks extends Activity implements View.OnClickListener{
    private Button drunkLogYes;
    private Button drunkLogNo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_outing);

        drunkLogYes = (Button)findViewById( R.id.drunk_log_yes );
        drunkLogNo = (Button)findViewById( R.id.drunk_log_no );

        drunkLogYes.setOnClickListener(this);
        drunkLogNo.setOnClickListener(this);
    }

    public void clickedLogYes() {
        Intent intent = new Intent(LargeDrinks.this, DrunkLog.class);
        startActivity(intent);
        finish();
    }

    public void clickedLogNo() {
        Intent intent = new Intent(LargeDrinks.this, Landing_Page.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if ( v == drunkLogYes ) {
            clickedLogYes();
        } else if ( v == drunkLogNo ) {
            clickedLogNo();
        }
    }
}

