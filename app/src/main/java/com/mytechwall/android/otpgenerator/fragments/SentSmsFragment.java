package com.mytechwall.android.otpgenerator.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mytechwall.android.otpgenerator.R;
import com.mytechwall.android.otpgenerator.model.SentSMSContarct;
import com.mytechwall.android.otpgenerator.adapters.SentSmsCursorAdapter;


public class SentSmsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView sentList;
    private SentSmsCursorAdapter adapter;

    public SentSmsFragment() {
        // Required empty public constructor
    }


    public static SentSmsFragment newInstance(int position) {
        SentSmsFragment fragment = new SentSmsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sent_sms, container, false);
        sentList = (ListView) rootView.findViewById(R.id.sentList);
        adapter = new SentSmsCursorAdapter(getContext(), null);
        sentList.setAdapter(adapter);
        TextView emptyTextView=(TextView)rootView.findViewById(R.id.emptytextView);
        sentList.setEmptyView(emptyTextView);
        getLoaderManager().initLoader(0, null, this);
        return rootView;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                SentSMSContarct.SmsEntry._ID,
                SentSMSContarct.SmsEntry.COLUMN_NAME,
                SentSMSContarct.SmsEntry.COLUMN_OTP,
                SentSMSContarct.SmsEntry.COLUMN_TIME,

        };
        Uri baseUri = SentSMSContarct.SmsEntry.CONTENT_URI;

        return new CursorLoader(getContext(), baseUri,
                projection, null, null, SentSMSContarct.SmsEntry._ID+" DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
