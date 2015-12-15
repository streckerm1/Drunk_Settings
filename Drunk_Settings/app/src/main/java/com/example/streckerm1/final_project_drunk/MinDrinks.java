package com.example.streckerm1.final_project_drunk;

import android.app.ActionBar;
import android.app.Activity;
import java.lang.reflect.Method;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.android.internal.telephony.ITelephony;


import java.lang.reflect.Method;

/**
 * Created by streckerm1 on 12/2/2015.
 */
public class MinDrinks extends AppCompatActivity implements View.OnClickListener {

    private Button contactsLockYes;
    private Button contactsLockNo;

    private BroadcastReceiver receiver;
    private IntentFilter filter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsLockYes = (Button) findViewById(R.id.contacts_lock_yes);
        contactsLockNo = (Button) findViewById(R.id.contacts_lock_no);

        contactsLockYes.setOnClickListener(this);
        contactsLockNo.setOnClickListener(this);
    }


    public void clickedYes() {
        filter = new IntentFilter();
        filter.addAction("android.intent.action.PHONE_STATE");
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.i("Intent Action", action);
                if(intent!=null){
                    if(action.equals("android.intent.action.PHONE_STATE")){
                        try {
                            TelephonyManager tm = (TelephonyManager) context
                                    .getSystemService(Context.TELEPHONY_SERVICE);
                            Class<?> c = Class.forName(tm.getClass().getName());
                            Method m = c.getDeclaredMethod("getITelephony");
                            m.setAccessible(true);
                            com.android.internal.telephony.ITelephony telephonyService = (ITelephony) m
                                    .invoke(tm);
                            telephonyService.endCall();
                        } catch (Exception e) {
                            Log.e("Exception", e.toString());
                        }
                    }else if(action.equals("android.provider.Telephony.SMS_RECEIVED")){
                        Bundle extras = intent.getExtras();
                        if (extras != null) {
                            abortBroadcast();
                        }
                    }
                }
            }
        };
    }

    public void clickedNo() {
        Intent intent = new Intent(MinDrinks.this, Landing_Page.class);
        startActivity(intent);
        finish();
    }

    public void onClick(View v) {
        if (v == contactsLockYes) {
            clickedYes();
        } else if (v == contactsLockNo) {
            clickedNo();
        }
    }
}
