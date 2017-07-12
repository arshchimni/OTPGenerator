package com.mytechwall.android.otpgenerator;

import java.io.Serializable;

/**
 * Created by arshdeep chimni on 11-07-2017.
 */

public class ContactModel implements Serializable {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public ContactModel(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
