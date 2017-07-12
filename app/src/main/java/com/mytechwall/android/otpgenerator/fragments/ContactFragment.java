package com.mytechwall.android.otpgenerator.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mytechwall.android.otpgenerator.activity.ContactInfoActivity;
import com.mytechwall.android.otpgenerator.model.ContactModel;
import com.mytechwall.android.otpgenerator.model.ContactsUtil;
import com.mytechwall.android.otpgenerator.R;
import com.mytechwall.android.otpgenerator.adapters.ListViewAdapter;

import java.util.ArrayList;

/**
 * Created by arshdeep chimni on 12-07-2017.
 */

public class ContactFragment extends Fragment {
    public ContactFragment() {
    }

    public static ContactFragment newInstance(int sectionNumber) {
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        ListView contactsList = (ListView) rootView.findViewById(R.id.contactsList);
        ContactsUtil dataContacts = new ContactsUtil();
        final ArrayList<ContactModel> contactDetails=dataContacts.getContact();
        ListViewAdapter adapter=new ListViewAdapter(getContext(),contactDetails);
        contactsList.setAdapter(adapter);

        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(),ContactInfoActivity.class);
                intent.putExtra("DETAILS",contactDetails.get(i));
                startActivity(intent);

            }
        });
        return rootView;
    }
}
