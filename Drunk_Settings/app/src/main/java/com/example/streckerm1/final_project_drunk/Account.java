package com.example.streckerm1.final_project_drunk;

import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by streckerm1 on 12/1/2015.
 */
public class Account {

        private long accountId;
        private String FirstName;
        private String LastName;
        private String Email;
        private String Username;
        private String Password;
        private String Male;
        private String Female;

        public static final String TRUE = "1";
        public static final String FALSE = "0";

        // creates all the things that will be inputed and set them to null

        public Account(int i, EditText firstNameInput, EditText lastNameInput, EditText userNameInput, EditText passwordInput, RadioButton maleButton, RadioButton femaleButton) {
            FirstName = "";
            LastName = "";
            Email = "";
            Username = "";
            Password = "";
            Male = "";
            Female = "";
        }

        // gets the information to store into the database
        public Account(int accountId, String FirstName, String LastName,
                    String Email, String Username, String Password, String Male, String Female) {
            this.accountId = accountId;
            this.FirstName = FirstName;
            this.LastName = LastName;
            this.Email = Email;
            this.Username = Username;
            this.Password = Password;
            this.Male = Male;
            this.Female = Female;
        }

        public long getId() {return accountId;}

        public void setId(long accountId) {this.accountId = accountId;}

        public String getFirstName() {return FirstName;}

        public void setFirstName(String FirstName) {this.FirstName = FirstName;}

        public String getLastName() {return LastName;}

        public void setLastName(String LastName) {this.LastName = LastName;}

        public String getEmail() {return Email;}

        public void setEmail(String Email) {this.Email = Email;}

        public String getUsername(){return Username;}

        public void setUsername(String Username) {this.Username = Username;}

        public String getPassword() {return Password;}

        public void setPassword(String Password) {this.Password = Password;}

        public String getMale() {return Male;}

        public void setMale(String Male) {this.Male = Male;}

        public String getFemale() {return Female;}

        public void setFemale(String Female) {this.Female = Female;}
    }

