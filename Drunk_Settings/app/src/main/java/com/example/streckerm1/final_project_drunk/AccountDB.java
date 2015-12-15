package com.example.streckerm1.final_project_drunk;

/**
 * Created by streckerm1 on 12/1/2015.
 */

import java.security.AccessControlContext;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class AccountDB {

    // database constants
    public static final String DB_NAME = "signUp.db";
    public static final int    DB_VERSION = 1;

    // task table constants
    public static final String TASK_TABLE = "account";

    public static final String TASK_ID = "_id";
    public static final int    TASK_ID_COL = 0;

    public static final String TASK_FirstNAME = "First_name";
    public static final int    TASK_FirstNAME_COL = 2;

    public static final String TASK_LastName = "Last_name";
    public static final int    TASK_LastName_COL = 3;

    public static final String TASK_Email = "Email";
    public static final int    TASK_Email_COL = 4;

    public static final String TASK_Username = "User_name";
    public static final int    TASK_Username_COL = 5;

    public static final String TASK_Password = "Password";
    public static final int    TASK_Password_COL = 6;

    public static final String TASK_Male = "Male";
    public static final int    TASK_Male_COL = 7;

    public static final String TASK_Female = "Female";
    public static final int    TASK_Female_COL = 8;

    // CREATE and DROP TABLE statements

    public static final String CREATE_TASK_TABLE =
            "CREATE TABLE " + TASK_TABLE + " (" +
                    TASK_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TASK_FirstNAME  + " TEXT, " +
                    TASK_LastName   + " TEXT, " +
                    TASK_Email      + " TEXT, " +
                    TASK_Username   + " TEXT, " +
                    TASK_Password   + " TEXT, " +
                    TASK_Male       + " TEXT, " +
                    TASK_Female     + " TEXT)";

    public static final String SIGNUP_TASK_TABLE =
            "DROP TABLE IF EXISTS " + TASK_TABLE;

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL(CREATE_TASK_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            Log.d("Task list", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            Log.d("Task list", "Deleting all data!");
            db.execSQL(SIGNUP_TASK_TABLE);
            onCreate(db);
        }
    }

    // database object and database helper object
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public AccountDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    public ArrayList<Account> getUser(String user) {
        String where = "userName = ?";
        String[] whereArgs = {user};

        this.openReadableDB();
        Cursor cursor = db.query(TASK_TABLE, null,
                where, whereArgs,
                null, null, null);
        ArrayList<Account> accounts = new ArrayList<Account>();
        while (cursor.moveToNext()) {
            accounts.add(getAccountFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return accounts;
    }

    public Account getPassword(String pass) {
        String where = TASK_Password + "= ?";
        String[] whereArgs = {pass};

        // handle exceptions?
        this.openReadableDB();
        Cursor cursor = db.query(TASK_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        Account account = getAccountFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return account;
    }

    public Account getAccount(long id) {
        String where = TASK_ID + "= ?";
        String[] whereArgs = { Long.toString(id) };

        // handle exceptions?
        this.openReadableDB();
        Cursor cursor = db.query(TASK_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        Account account = getAccountFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return account;
    }

    private static Account getAccountFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                Account account = new Account(
                        cursor.getInt(TASK_ID_COL),
                        cursor.getString(TASK_FirstNAME_COL),
                        cursor.getString(TASK_LastName_COL),
                        cursor.getString(TASK_Email_COL),
                        cursor.getString(TASK_Username_COL),
                        cursor.getString(TASK_Password_COL),
                        cursor.getString(TASK_Male_COL),
                        cursor.getString(TASK_Female_COL));
                return account;
            }
            catch(Exception e) {
                return null;
            }
        }
    }

    public long insertAccount(Account account) {
        ContentValues cv = new ContentValues();
        cv.put(TASK_FirstNAME, account.getFirstName());
        cv.put(TASK_LastName, account.getLastName());
        cv.put(TASK_Email, account.getEmail());
        cv.put(TASK_Username, account.getUsername());
        cv.put(TASK_Password, account.getPassword());
        cv.put(TASK_Male, account.getMale());
        cv.put(TASK_Female, account.getFemale());

        this.openWriteableDB();
        long rowID = db.insert(TASK_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    public int updateTask(Account account) {
        ContentValues cv = new ContentValues();
        cv.put(TASK_FirstNAME, account.getFirstName());
        cv.put(TASK_LastName, account.getLastName());
        cv.put(TASK_Email, account.getEmail());
        cv.put(TASK_Username, account.getUsername());
        cv.put(TASK_Password, account.getPassword());
        cv.put(TASK_Male, account.getMale());
        cv.put(TASK_Female, account.getFemale());


        String where = TASK_ID + "= ?";
        String[] whereArgs = { String.valueOf(account.getId()) };

        this.openWriteableDB();
        int rowCount = db.update(TASK_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteAccount(long id) {
        String where = TASK_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(TASK_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }
}
