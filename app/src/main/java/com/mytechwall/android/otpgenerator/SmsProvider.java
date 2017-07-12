package com.mytechwall.android.otpgenerator;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by arshdeep chimni on 12-07-2017.
 */

public class SmsProvider extends ContentProvider {
    public static final String LOG_TAG = SmsProvider.class.getSimpleName();
    private SmsDatabaseHelper dbHelper;
    private static final int SMS = 100;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(SentSMSContarct.CONTENT_AUTHORITY, SentSMSContarct.PATH_SMS, SMS);
    }
    @Override
    public boolean onCreate() {
        dbHelper = new SmsDatabaseHelper(getContext());
        return true;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        int match = sUriMatcher.match(uri);
       switch (match) {
           case SMS:
           cursor = db.query(SentSMSContarct.SmsEntry.TABLE_NAME, strings, s, strings1
                   , null, null, s1);
               break;
           default:
               throw new IllegalArgumentException("Cannot query unknown URI " + uri);
       }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SMS:
                return insertSms(uri, contentValues);
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    private Uri insertSms(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long id = db.insert(SentSMSContarct.SmsEntry.TABLE_NAME, null, contentValues);

        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        //signal that a change has occured in the table and change the ui display accordingly
        getContext().getContentResolver().notifyChange(uri, null);
        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);

    }

}
