package com.mytechwall.android.otpgenerator.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mytechwall.android.otpgenerator.R;
import com.mytechwall.android.otpgenerator.model.ContactModel;

import java.io.Serializable;

public class ContactInfoActivity extends AppCompatActivity implements Serializable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        final ContactModel currentDetail = (ContactModel) getIntent().getSerializableExtra("DETAILS");
        TextView firstName = (TextView) findViewById(R.id.firstName);
        TextView lastName = (TextView) findViewById(R.id.lastName);
        TextView contactNumber = (TextView) findViewById(R.id.contactNumber);
        Button smsSender = (Button) findViewById(R.id.smsButton);
        smsSender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),SmsCompose.class);
                intent.putExtra("CONTACT",currentDetail);
                startActivity(intent);
                finish();
            }
        });


        firstName.setText(currentDetail.getFirstName());
        lastName.setText(currentDetail.getLastName());
        contactNumber.setText(currentDetail.getPhoneNumber());

    }
}
