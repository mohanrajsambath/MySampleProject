package com.androidexample.contentprovideremail;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ArrayList<ContactModel> arrayList_ContactDetails=new ArrayList<>();
    ArrayList<String> arrayList_emails=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView output=(TextView) findViewById(R.id.output);
        AutoCompleteTextView autocomplete=findViewById(R.id.autocomplete);
        //   refreshData();
        getContactDetails();
        ArrayAdapter<String> adapter=new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, arrayList_emails);
        autocomplete.setThreshold(1);
        autocomplete.setAdapter(adapter);
        autocomplete.setTextColor(Color.RED);
        // Fetch emails from contact list
        // String emailStr=refreshData();
        // Show emails on screen
        // output.setText(emailStr);

    }


    private String refreshData() {
        String emaildata="";
        String companyname="";
        try {

            /**************************************************/

            ContentResolver cr=getBaseContext().getContentResolver();
            Cursor cur=cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if (cur.getCount() > 0) {
                Log.i("Content provider", "Reading contact  emails");
                while (cur.moveToNext()) {
                    String contactId=cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name=cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                    // Create query to use CommonDataKinds classes to fetch emails
                    Cursor emails=cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID
                                    + " = " + contactId, null, null);
								
								/*
								//You can use all columns defined for ContactsContract.Data 
								// Query to get phone numbers by directly call data table column
								  
								Cursor c = getContentResolver().query(Data.CONTENT_URI,
								          new String[] {Data._ID, Phone.NUMBER, Phone.TYPE, Phone.LABEL},
								          Data.CONTACT_ID + "=?" + " AND "
								                  + Data.MIMETYPE + "='" + Phone.CONTENT_ITEM_TYPE + "'",
								          new String[] {String.valueOf(contactId)}, null);
								*/

                    while (emails.moveToNext()) {

                        // This would allow you get several email addresses
                        String emailAddress=emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));

                        if (cur.getString(cur.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)) {
                            companyname=cur.getString(cur.getColumnIndex("data1"));// get company

                        }

                        //   if (name != null && !name.isEmpty()) {
                    //    arrayList_ContactDetails.add(companyname);
                        //   }
                        //Log.e("email==>", emailAddress);

                        emaildata+="\n  " + emailAddress + "\n"
                                + "--------------------------------------";
                    }

                    emails.close();
                }

            } else {
                emaildata+="\nData not found.\n";

            }
            cur.close();


        } catch (Exception e) {

            emaildata+="\nException : " + e + "\n";
        }

        return emaildata;
    }


    void getContactDetails() {


        Uri uri=ContactsContract.Contacts.CONTENT_URI; // Contact URI
        Cursor contactsCursor=getContentResolver().query(uri, null, null,
                null, ContactsContract.Contacts.DISPLAY_NAME + " ASC "); // Return


        if (contactsCursor.moveToFirst()) {
            do {
                long contctId=contactsCursor.getLong(contactsCursor
                        .getColumnIndex("_ID")); // Get contact ID
                Uri dataUri=ContactsContract.Data.CONTENT_URI; // URI to get
                // data of
                // contacts
                Cursor dataCursor=getContentResolver().query(dataUri, null,
                        ContactsContract.Data.CONTACT_ID + " = " + contctId,
                        null, null);// Retrun data cusror represntative to
                // contact ID

                // Strings to get all details
                String displayName="";
                String nickName="";
                String homeEmail="",emailAddress="";
                String companyName="";
                String title="";

                // This strings stores all contact numbers, email and other
                // details like nick name, company etc.
                String contactNumbers="";
                String contactEmailAddresses="";
                String contactOtherDetails="";

                // Now start the cusrsor
                if (dataCursor.moveToFirst()) {
                    displayName=dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));// get
                    // the // contact // name
                    do {
                        if (dataCursor
                                .getString(
                                        dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE)) {

                        }

                        if (dataCursor
                                .getString(
                                        dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {


                             emailAddress=dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                            arrayList_ContactDetails.add(new ContactModel(emailAddress));
                            arrayList_emails.add(emailAddress);
                            // In this get all Emails like home, work etc and
                            // add them to email string
                          /*  switch (dataCursor.getInt(dataCursor
                                    .getColumnIndex("data2"))) {
                                case ContactsContract.CommonDataKinds.Email.DATA:
                                    homeEmail=dataCursor.getString(dataCursor
                                            .getColumnIndex("data1"));
                                    arrayList_ContactDetails.add(homeEmail);
                                    break;


                            }*/
                        }

                        if (dataCursor.getString(dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)) {
                            companyName=dataCursor.getString(dataCursor
                                    .getColumnIndex("data1"));// get company
                            // name
                            contactOtherDetails+="Company Name : "
                                    + companyName + "\n";
                            title=dataCursor.getString(dataCursor
                                    .getColumnIndex("data4"));// get Company
                            // title
                            contactOtherDetails+="Title : " + title + "\n";
                           // arrayList_ContactDetails.add(new ContactModel(displayName,companyName,emailAddress));
                        }
                    } while (dataCursor.moveToNext()); // Now move to next

                }

            } while (contactsCursor.moveToNext());
        }
    }

}
