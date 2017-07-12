package com.mytechwall.android.otpgenerator.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mytechwall.android.otpgenerator.model.SentSMSContarct;

/**
 * Created by arshdeep chimni on 12-07-2017.
 */

public class SmsDatabaseHelper extends SQLiteOpenHelper {


    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "smsdata.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    public SmsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + SentSMSContarct.SmsEntry.TABLE_NAME + " ("
                + SentSMSContarct.SmsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SentSMSContarct.SmsEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + SentSMSContarct.SmsEntry.COLUMN_OTP + " TEXT, "
                + SentSMSContarct.SmsEntry.COLUMN_TIME + " TEXT);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
