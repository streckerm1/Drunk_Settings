package com.example.streckerm1.final_project_drunk;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor.*;
import android.widget.Adapter;

// http://developer.android.com/training/contacts-provider/retrieve-names.html
// http://stackoverflow.com/questions/25691237/how-to-retrieve-data-from-contact-uri-of-a-list-item-obtained-from-onitemclick

/**
 * Created by streckerm1 on 12/2/2015.
 */
public abstract class ContactFragments extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemClickListener {

    //  an array that contains column names to move the cursor

    @SuppressLint("InlinedApi")
    private final static String[] FROM_COLUMNS = {
            Build.VERSION.SDK_INT
                    >= Build.VERSION_CODES.HONEYCOMB ?
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                    ContactsContract.Contacts.DISPLAY_NAME};

    // array that contains the layout view resource ids

    private final static int[] TO_IDS = {
            android.R.id.text1
    };
    // listview object
    ListView MyContactsList;
    // contact id values
    long MyContactId;
    // contacts lookup id
    String MyContactKey;
    // a content uri for the selected content
    Uri MyContactUri;
    // binds the result adapter to the listview
    private SimpleCursorAdapter MyCursorAdapter;

    // empty public constructor

    public ContactFragments() {
    }

    //inflate views

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        return inflater.inflate(R.layout.contact_list_text,
                container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Gets the ListView from the View list of the parent activity
        MyContactsList =
                (ListView) getActivity().findViewById(R.id.contact_list);
        // Gets a CursorAdapter
        MyCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.contact_list_text,
                null,
                FROM_COLUMNS, TO_IDS,
                0);
        // Sets the adapter for the ListView
        MyContactsList.setAdapter(MyCursorAdapter);

        // set listeners
        MyContactsList.setOnItemClickListener(this);
    }

    @SuppressLint("InlinedApi")
    private static final String[] PROJECTION =
            {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.LOOKUP_KEY,
                    Build.VERSION.SDK_INT
                            >= Build.VERSION_CODES.HONEYCOMB ?
                            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                            ContactsContract.Contacts.DISPLAY_NAME

            };


    // The column index for the _ID column
    private static final int CONTACT_ID_INDEX = 0;
    // The column index for the LOOKUP_KEY column
    private static final int CONTACT_KEY_INDEX = 1;

    @SuppressLint("InlinedApi")
    private static final String SELECTION =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?" :
                    ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
    // Defines a variable for the search string
    private String MySearchString;
    // Defines the array to hold values that replace the ?
    private String[] MySelectionArgs = {MySearchString};

    @Override
    public void onItemClick(
            AdapterView<?> parent, View item, int position, long rowID) {
        // Get the Cursor
        Cursor cursor = ((SimpleCursorAdapter) parent.getAdapter()).getCursor();
        // Move to the selected contact
        cursor.moveToPosition(position);
        // Get the _ID value
        MyContactId = cursor.getLong(CONTACT_ID_INDEX);
        // Get the selected LOOKUP KEY
        MyContactKey = cursor.getString(CONTACT_KEY_INDEX);
        // Create the contact's content Uri
        MyContactUri = ContactsContract.Contacts.getLookupUri(MyContactId, MyContactKey);
    }

    public abstract class ContactFragment extends Fragment implements
            LoaderManager.LoaderCallbacks<Cursor> {

        // Called just before the Fragment displays its UI
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            // Always call the super method first
            super.onActivityCreated(savedInstanceState);

            // Initializes the loader
            getLoaderManager().initLoader(0, null, this);
        }

        @Override
        public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        /*
         * Makes search string into pattern and
         * stores it in the selection array
         */
            MySelectionArgs[0] = "%" + MySearchString + "%";
            // Starts the query
            return new CursorLoader(
                    getActivity(),
                    ContactsContract.Contacts.CONTENT_URI,
                    PROJECTION,
                    SELECTION,
                    MySelectionArgs,
                    null
            );
        }
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Put the result Cursor in the adapter for the ListView
        MyCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Delete the reference to the existing Cursor
        MyCursorAdapter.swapCursor(null);
    }

}


