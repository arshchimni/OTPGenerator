package com.mytechwall.android.otpgenerator;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by arshdeep chimni on 12-07-2017.
 */

public class SentSmsCursorAdapter extends CursorAdapter {

    public SentSmsCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
     return LayoutInflater.from(context).inflate(R.layout.cursor_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name = (TextView) view.findViewById(R.id.nameTextView);
        TextView otp = (TextView) view.findViewById(R.id.otpTextView);
        TextView date = (TextView) view.findViewById(R.id.dateTextView);


        int nameColumnIndex = cursor.getColumnIndex(SentSMSContarct.SmsEntry.COLUMN_NAME);
        int otpColumnIndex = cursor.getColumnIndex(SentSMSContarct.SmsEntry.COLUMN_OTP);
        int timeColumnIndex = cursor.getColumnIndex(SentSMSContarct.SmsEntry.COLUMN_TIME);


        String fullName = cursor.getString((nameColumnIndex));
        String otpValue = cursor.getString((otpColumnIndex));
        String timeValue = cursor.getString((timeColumnIndex));

        name.setText(fullName);
        otp.setText(otpValue);
        date.setText(timeValue);


    }
}
