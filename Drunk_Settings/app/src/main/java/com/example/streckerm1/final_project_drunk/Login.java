package com.example.streckerm1.final_project_drunk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by streckerm1 on 12/1/2015.
 */
public class Login extends Activity implements View.OnClickListener {

    private EditText LoginUser;
    private EditText LoginPassword;
    private Button LoginButton;

    private AccountDB db;
    private Account account;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        LoginButton = (Button) findViewById(R.id.LoginButton);
        LoginUser = (EditText) findViewById(R.id.LoginUser);
        LoginPassword = (EditText) findViewById(R.id.LoginPassword);

        LoginButton.setOnClickListener(this);
    }

    public void nullUser() {
        // prompts a dialog telling the user the login is not in the database
        if (LoginUser == null || LoginUser.equals("")) {
            AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
            alertDialog.setMessage("Please enter a valid user");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else {
            nextPage();
        }
    }

    public void nullPassword() {
        // prompts a dialog telling the user the password is invalid
        if (LoginPassword == null || LoginPassword.equals("")) {
            AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
            alertDialog.setMessage("PLease enter a valid password");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else {
            nextPage();
        }
    }

    // verifies the username with the database
    public void VerifyUserName(){
        String UserName = LoginUser.getText().toString();
        if(account.getUsername().equals(UserName)) {
            nextPage();
        } else {
            nullUser();
        }
    }

    // verifies the password with the database
    public void VerifyPassword(){
        String Password = LoginPassword.getText().toString();
        if(account.getPassword().equals(Password)) {
            nextPage();
        } else {
            nullUser();
        }
    }

    // sends the user to the FirstPage
    public void nextPage() {
        Intent intent = new Intent(Login.this, FirstPage.class);
        startActivity(intent);
        finish();
    }

    public void onClick(View v) {
        if (v == LoginButton) {
            nullUser();
            nullPassword();
        }
    }
}

