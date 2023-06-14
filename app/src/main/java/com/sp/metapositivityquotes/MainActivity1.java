package com.sp.metapositivityquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity1 extends AppCompatActivity {
    private TextView txtViewSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        txtViewSMS = (TextView) findViewById(R.id.txtViewSMS);

        // Get intent object sent from the SMSBroadcastReceiver
        Intent sms_intent = getIntent();
        Bundle b = sms_intent.getExtras();
        if (b != null) {
            // Display SMS in the TextView
            txtViewSMS.setText(b.getString("sms_str"));
        }
        fetchSMS();
        //fromParticularSender();
    }

    //function to read the SMS inbox

    public void fetchSMS() {
        final Uri uriSMSUri = Uri.parse("content://sms/inbox");
        String[] projection = new String[]{"_id", "address", "person", "body", "date", "type", "creator", "protocol"};
        final Cursor c = getContentResolver().query(uriSMSUri, projection, "address LIKE '%SAFARICOM%'", null, null);
        //final Cursor c = getContentResolver().query(uriSMSUri, projection, null, null, null);

        if (c != null && c.moveToFirst()) {
            final StringBuilder sb = new StringBuilder();
            final int idIndex = c.getColumnIndex("_id");
            final int addressIndex = c.getColumnIndex("address");
            final int bodyIndex = c.getColumnIndex("body");
            final int personIndex = c.getColumnIndex("person");
            final int dateIndex = c.getColumnIndex("date");
            final int typeIndex = c.getColumnIndex("type");
            final int creatorIndex = c.getColumnIndex("creator");
            final int protocolIndex = c.getColumnIndex("protocol");

            do {
                sb.append("ID: " + c.getString(idIndex) + "\n")
                        .append("From: " + c.getString(addressIndex) + "\n")
                        .append("Body: " + c.getString(bodyIndex) + "\n")
                        .append("Person: " + c.getString(personIndex) + "\n")
                        .append("Date: " + c.getString(dateIndex) + "\n")
                        .append("Type: " + c.getString(typeIndex) + "\n")
                        .append("Creator: " + c.getString(creatorIndex) + "\n")
                        .append("Protocol: " + c.getString(protocolIndex) + "\n")
                        .append("------------------------------------------------------------------------")
                        .append("\n\n");
            } while (c.moveToNext());

            txtViewSMS.setText(sb.toString());
        }
    }

    public void fromParticularSender() {
        StringBuilder smsBuilder = new StringBuilder();
        final String SMS_URI_INBOX = "content://sms/inbox";
        final String SMS_URI_ALL = "content://sms/";
        try {
            Uri uri = Uri.parse(SMS_URI_INBOX);
            String[] projection = new String[]{"_id", "address", "person", "body", "date", "type"};
            Cursor cur = getContentResolver().query(uri, projection, "address='+254715848829'", null, "date desc");
            if (cur.moveToFirst()) {
                int index_Address = cur.getColumnIndex("address");
                int index_Person = cur.getColumnIndex("person");
                int index_Body = cur.getColumnIndex("body");
                int index_Date = cur.getColumnIndex("date");
                int index_Type = cur.getColumnIndex("type");
                do {
                    String strAddress = cur.getString(index_Address);
                    int intPerson = cur.getInt(index_Person);
                    String strbody = cur.getString(index_Body);
                    long longDate = cur.getLong(index_Date);
                    int int_Type = cur.getInt(index_Type);

                    smsBuilder.append("[ ");
                    smsBuilder.append(strAddress + ", ");
                    smsBuilder.append(intPerson + ", ");
                    smsBuilder.append(strbody + ", ");
                    smsBuilder.append(longDate + ", ");
                    smsBuilder.append(int_Type);
                    smsBuilder.append(" ]\n\n");
                } while (cur.moveToNext());
                txtViewSMS.setText(smsBuilder.toString());

                if (!cur.isClosed()) {
                    cur.close();
                    cur = null;
                }
            } else {
                smsBuilder.append("no result!");
            } // end if
        } catch (SQLiteException ex) {
            Log.d("SQLiteException", ex.getMessage());
        }
    }
}
