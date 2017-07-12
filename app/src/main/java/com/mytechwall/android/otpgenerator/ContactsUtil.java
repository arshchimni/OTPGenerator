package com.mytechwall.android.otpgenerator;

import java.util.ArrayList;

/**
 * Created by arshdeep chimni on 11-07-2017.
 */

public  class ContactsUtil {

    ArrayList<ContactModel> contact=new ArrayList<>();

    public ContactsUtil() {
        initializeData();
    }

    private void initializeData() {
        contact.add(new ContactModel("John","Smith","013645459"));
        contact.add(new ContactModel("Jane","Doe","789785"));
        contact.add(new ContactModel("John","Doe","4533323"));
        contact.add(new ContactModel("Ars","Tech","78894513"));
        contact.add(new ContactModel("Ser","Bri","1223545"));
        contact.add(new ContactModel("Man","Adam","45666"));
    }


    public ArrayList<ContactModel> getContact() {
        return contact;
    }
}
