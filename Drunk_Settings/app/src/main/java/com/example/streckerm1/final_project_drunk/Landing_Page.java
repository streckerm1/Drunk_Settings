package com.example.streckerm1.final_project_drunk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.Method;


/**
 * Created by streckerm1 on 12/3/2015.
 */
public class Landing_Page extends Activity implements View.OnClickListener {

    private ScrollView LogScrollView;
    private Button newLog;
    private Button contactUnlock;
    private Button signOut;


    private Account logOut;
    private AccountDB db;
    private BroadcastReceiver receiver;
    private IntentFilter filter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        LogScrollView = (ScrollView)findViewById( R.id.LogScrollView );
        signOut = (Button)findViewById(R.id.sign_out_btn);
        newLog = (Button)findViewById( R.id.new_log );
        contactUnlock = (Button)findViewById( R.id.contact_unlock );

        newLog.setOnClickListener(this);
        contactUnlock.setOnClickListener(this);
        signOut.setOnClickListener(this);
    }

    public void unlockContacts() {
        // unlock contacts
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
                            telephonyService.isRinging();
                        } catch (Exception e) {
                            Log.e("Exception", e.toString());
                        }
                    }else if(action.equals("android.provider.Telephony.SMS_RECEIVED")){
                        Bundle extras = intent.getExtras();
                        if (extras != null) {
                            clearAbortBroadcast();
                        }
                    }
                }
            }
        };
    }

    public void newLogRecord() {
        // log code
        Intent newLogIntent = new Intent(this, DrunkLog.class);
        startActivity(newLogIntent);
        finish();

    }

    public void signOutAccount() {
        // sign the user out and send them back to the login page
        AlertDialog alertDialog = new AlertDialog.Builder(Landing_Page.this).create();
        alertDialog.setMessage("You have successfully logged out");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

        Intent logoutIntent = new Intent(this, Login.class);
        startActivity(logoutIntent);
        finish();
    }


    @Override
    public void onClick(View v) {
        if(v == newLog) {
            newLogRecord();
        } else if(v == contactUnlock) {
            unlockContacts();
        } else if(v == signOut) {
            signOutAccount();
        }
    }
}
