package com.example.streckerm1.final_project_drunk;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by streckerm1 on 12/1/2015.
 */
public class FirstPage extends Activity implements View.OnClickListener {

    private RadioGroup FirstPageRG;
    private RadioButton FirstPageRB1;
    private RadioButton FirstPageRB2;
    private RadioButton FirstPageRB3;
    private RadioButton FirstPageRB4;
    private RadioButton FirstPageRB5;
    private RadioButton FirstPageRB6;

    private BroadcastReceiver receiver;
    private IntentFilter filter;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);

        FirstPageRG = (RadioGroup) findViewById(R.id.FirstPageRG);
        FirstPageRB1 = (RadioButton) findViewById(R.id.MinDrinks);
        FirstPageRB2 = (RadioButton) findViewById(R.id.medDrinks);
        FirstPageRB3 = (RadioButton) findViewById(R.id.largeDrinks);
        FirstPageRB4 = (RadioButton) findViewById(R.id.extraDrinks);
        FirstPageRB5 = (RadioButton) findViewById(R.id.maxDrinks);
        FirstPageRB6 = (RadioButton) findViewById(R.id.contact_page);

        FirstPageRB1.setOnClickListener(this);
        FirstPageRB2.setOnClickListener(this);
        FirstPageRB3.setOnClickListener(this);
        FirstPageRB4.setOnClickListener(this);
        FirstPageRB5.setOnClickListener(this);
        FirstPageRB6.setOnClickListener(this);
    }

    // sends the user to the second page prompting the user if they want to record a drunk log
    public void secondPage() {
        Intent intent = new Intent(FirstPage.this, MinDrinks.class);
        startActivity(intent);
        finish();
    }

    // locks the contacts, sets a timer on the lock, and sends the user to the log page
    public void thirdPage() {
        Intent intent = new Intent(FirstPage.this, MedDrinks.class);
        startActivity(intent);
        finish();

        final long startMillis = System.currentTimeMillis();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                long elaspedMillis = System.currentTimeMillis() - startMillis;
            }
        };
        Timer timer = new Timer(true);
        timer.schedule(task, 7200);

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
    // locks the contacts, sets a timer on the lock, and sends the user to the log page
    public void fourthPage() {
        Intent intent = new Intent(FirstPage.this, LargeDrinks.class);
        startActivity(intent);

        final long startMillis = System.currentTimeMillis();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                long elaspedMillis = System.currentTimeMillis() - startMillis;
            }
        };
        Timer timer = new Timer(true);
        timer.schedule(task, 14400);


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
    // locks the contacts, sets a timer on the lock, and sends the user to the log page
    public void fifthPage() {
        Intent intent = new Intent(FirstPage.this, ExtraDrinks.class);
        startActivity(intent);


        final long startMillis = System.currentTimeMillis();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                long elaspedMillis = System.currentTimeMillis() - startMillis;
            }
        };
        Timer timer = new Timer(true);
        timer.schedule(task, 18000);

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

    // locks the contacts, sets a timer on the lock, and sends the user to the log page
    public void sixthPage() {
        Intent intent = new Intent(FirstPage.this, MaxDrinks.class);
        startActivity(intent);


        final long startMillis = System.currentTimeMillis();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                long elaspedMillis = System.currentTimeMillis() - startMillis;
            }
        };
        Timer timer = new Timer(true);
        timer.schedule(task, 21600);

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
    // sends the user to view their contact list
    public void showContacts() {
        Intent intent = new Intent(FirstPage.this, ContactFragments.class);
        startActivity(intent);
    }

    // handles all the radio button clicks
    public void onClick(View v) {
        if (v == FirstPageRB1) {
            secondPage();
        } else if (v == FirstPageRB2) {
            thirdPage();
        } else if (v == FirstPageRB3) {
            fourthPage();
        } else if (v == FirstPageRB4) {
            fifthPage();
        } else if (v == FirstPageRB5) {
            sixthPage();
        } else if(v == FirstPageRB6) {
            showContacts();
        }
    }
}
