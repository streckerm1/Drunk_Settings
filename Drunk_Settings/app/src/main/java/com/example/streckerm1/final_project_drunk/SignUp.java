package com.example.streckerm1.final_project_drunk;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Intent;

import java.util.Random;

public class SignUp extends Activity implements OnClickListener {

    private EditText firstNameInput;
    private EditText emailInput;
    private EditText lastNameInput;
    private EditText userNameInput;
    private EditText PasswordInput;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private RadioGroup RadioGroup;
    private Button Submit;

    private AccountDB db;
    private Account account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firstNameInput = (EditText) findViewById(R.id.firstNameInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
        lastNameInput = (EditText) findViewById(R.id.lastNameInput);
        userNameInput = (EditText) findViewById(R.id.userNameInput);
        PasswordInput = (EditText) findViewById(R.id.PasswordInput);
        RadioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        maleButton = (RadioButton) findViewById(R.id.maleButton);
        femaleButton = (RadioButton) findViewById(R.id.femaleButton);
        Submit = (Button) findViewById(R.id.Submit);


        maleButton.setOnClickListener(this);
        femaleButton.setOnClickListener(this);
        Submit.setOnClickListener(this);

        db = new AccountDB(this);
    }


    private void saveToDB() {
        // get data from widgets
        String FirstName = firstNameInput.getText().toString();
        String LastName = lastNameInput.getText().toString();
        String Email = emailInput.getText().toString();
        String UserName = userNameInput.getText().toString();
        String Password = PasswordInput.getText().toString();
        String Male = maleButton.getText().toString();
        String Female = femaleButton.getText().toString();

        // if no account name, exit method
        if (UserName == null || UserName.equals("")) {
            return;
        } else if (Password == null || Password.equals("")) {
            return;
        } else if (Email == null || Email.equals("")) {
            return;
        } else if (FirstName == null || FirstName.equals("")) {
            return;
        } else if (LastName == null || LastName.equals("")) {
            return;
        }

        // put account into database
        account.setFirstName(FirstName);
        account.setLastName(LastName);
        account.setEmail(Email);
        account.setUsername(UserName);
        account.setPassword(Password);
        account.setMale(Male);
        account.setFemale(Female);
    }
    public void newAccount() {

        // inserts the new account

        Random r = new Random();
        int x = (r.nextInt(10000 - 1) + 1);

        Account SignIn = new Account(x, firstNameInput, lastNameInput, userNameInput, PasswordInput, maleButton, femaleButton);
        long insertId = db.insertAccount(SignIn);


    }

    public void nextPage() {
        // takes the user to the login page
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);

    }

    public void onClick(View v) {
        if (v == Submit) {
            newAccount();
            nextPage();
        }
    }
}
