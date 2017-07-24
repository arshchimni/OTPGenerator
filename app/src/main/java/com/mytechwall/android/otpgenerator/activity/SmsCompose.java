package com.mytechwall.android.otpgenerator.activity;

import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mytechwall.android.otpgenerator.R;
import com.mytechwall.android.otpgenerator.model.ContactModel;
import com.mytechwall.android.otpgenerator.model.SentSMSContarct;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;


public class SmsCompose extends AppCompatActivity {
    private ContactModel currentDetail;
    private Random random;
    private int otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_compose);
        currentDetail = (ContactModel) getIntent().getSerializableExtra("CONTACT");
        TextView composeSms = (TextView) findViewById(R.id.composeSmsTextView);
        Button sendSms = (Button) findViewById(R.id.sendButton);
        random = new Random();
        otp = 100000 + random.nextInt(900000);
        String smsBody="Hi. Your OTP is: "+otp;
        composeSms.setText(smsBody);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()){
                    performNetworkRequest();
                }
                else{
                    Toast.makeText(SmsCompose.this, "NO INTERNET", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void performNetworkRequest(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = "Replace with yout url";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        saveSmsData();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"SERVER UNREACHABLE",Toast.LENGTH_LONG).show();
                        onBackPressed();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                super.getParams();

                Map<String,String> params = new HashMap<String, String>();
                String otpVal=String.valueOf(otp);
                params.put("OTP",otpVal);
                return params;
            }
        };
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


    }



    private void saveSmsData() {
        String time = getDateTime();
        ContentValues values = new ContentValues();
        values.put(SentSMSContarct.SmsEntry.COLUMN_NAME, currentDetail.getFirstName() + " " + currentDetail.getLastName());
        values.put(SentSMSContarct.SmsEntry.COLUMN_TIME, time);
        values.put(SentSMSContarct.SmsEntry.COLUMN_OTP, String.valueOf(otp));
        Uri newRow = getContentResolver().insert(SentSMSContarct.SmsEntry.CONTENT_URI, values);
        if (newRow==null){
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            //Toast.makeText(this, "Sucess", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
