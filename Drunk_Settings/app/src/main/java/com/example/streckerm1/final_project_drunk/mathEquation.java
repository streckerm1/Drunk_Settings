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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.Method;

public class mathEquation extends Activity implements View.OnClickListener{
    private TextView equationText;
    private EditText unlockAnswer;
    private Button unlockSubmit;
    private int num1;
    private int num2;
    private int num3;
    private int ans;
    private int ansInt;
    private String ansToCheck;

    private BroadcastReceiver receiver;
    private IntentFilter filter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_unlock);
        equationText = (TextView) findViewById(R.id.equation_test);
        unlockAnswer = (EditText) findViewById(R.id.unlock_answer);
        unlockSubmit = (Button) findViewById(R.id.unlock_submit);
        unlockSubmit.setOnClickListener(this);
    }


    //creates difficult math problem for upper spectrum of drink consumption
    public void makeHardEquation(){
        int num1 = (int)(Math.random()* 200) + 1;
        int num2 = (int)(Math.random()* 200) + 1;
        int num3 = (int)(Math.random()* 200) + 1;

        equationText.setText(""+ num1 + " + " + num2 + " + " + num3 + "");
        ans = num1 + num2 + num3;
    }
    //creates simple math problem for lower spectrum of drink consumption
    public void makeEasyEquation(){
        int num1 = (int)(Math.random()* 100) + 1;
        int num2 = (int)(Math.random()* 100) + 1;
        equationText.setText(""+ num1 + " + " + num2 + " ");
        ans = num1 + num2;
    }
    //brings input from EditText Answer
    public void checkAns(){
        EditText givenAns = unlockAnswer;
        ansToCheck = (givenAns.getText().toString());
        ansInt = Integer.parseInt(ansToCheck);

        //if valid, take to landing page and present the user with a success toast
        if (ansInt == ans){
            unlockContacts();
            //intent to landing page
            Context context = getApplicationContext();
            CharSequence text = "Your contacts have been unlocked successfully.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        // if invalid, user stays on page and must try again after being prompted by failure toast
        else{
            Context context = getApplicationContext();
            CharSequence text = "Please try another answer.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    private void unlockContacts() {
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

    //validates answer after submit button is pressed
  public void onClick(View v) {
        if (v == unlockSubmit ) {
            checkAns();
        }
    }

}
