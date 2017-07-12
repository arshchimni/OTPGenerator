package com.mytechwall.android.otpgenerator.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mytechwall.android.otpgenerator.model.ContactModel;
import com.mytechwall.android.otpgenerator.R;

import java.util.ArrayList;


/**
 * Created by arshdeep chimni on 11-07-2017.
 */

public class ListViewAdapter extends ArrayAdapter<ContactModel> {
    public ListViewAdapter(@NonNull Context context, @NonNull ArrayList<ContactModel> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

        }
        ContactModel currentData = getItem(position);
        TextView firstName = (TextView) listItemView.findViewById(R.id.firstname);
        TextView lastName = (TextView) listItemView.findViewById(R.id.lastName);

        firstName.setText(currentData.getFirstName());

        lastName.setText(currentData.getLastName());
        return listItemView;
    }
}
