package com.mytechwall.android.otpgenerator.model;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by arshdeep chimni on 12-07-2017.
 */

public final class SentSMSContarct {
    private SentSMSContarct() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single sms.
     */
    public static final String CONTENT_AUTHORITY="com.mytechwall.android.otpgenerator";
    public static final Uri BASE_CONTENT_URI= Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_SMS = "sms";


    public static final class SmsEntry implements BaseColumns{

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SMS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SMS;

        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_SMS);


        public final static String TABLE_NAME = "sms";


        /**
         * Unique ID number for the pet (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the person.
         *
         * Type: TEXT
         */
        public final static String COLUMN_NAME ="name";

        /**
         * otp
         *
         * Type: TEXT
         */
        public final static String COLUMN_OTP = "otp";

        /**
         * otp
         *
         * Type: TEXT
         */
        public final static String COLUMN_TIME = "time";


    }
}
