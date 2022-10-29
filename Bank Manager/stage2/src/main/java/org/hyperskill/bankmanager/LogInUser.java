package org.hyperskill.bankmanager;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class LogInUser {
    private static String username;
    private String password;
    private ArrayList<Map> userData;
    private boolean userNameIdentification = false;
    private boolean passwordIdentification = false;
    private Activity activity;


    public LogInUser() {
    }

    public LogInUser(ArrayList<Map> userData, EditText username, EditText password, Activity activity) {
        this.userData = userData;
        LogInUser.username = username.getText().toString();
        this.password = password.getText().toString();
        this.activity = activity;

    }


    public boolean userLogInDataCheck() {
        if (userData.size() == 0) {
            Toast.makeText(activity, "No user data found, you need to signup first", Toast.LENGTH_LONG).show();
        } else {
            for (int i = 0; i < userData.size(); i++) {
                if (Objects.equals(userData.get(i).get("userName"), username)) {
                    userNameIdentification = true;
                } else {
                    Toast.makeText(activity, "Wrong username", Toast.LENGTH_SHORT).show();
                }
                if (Objects.equals(userData.get(i).get("password"), password)) {
                    passwordIdentification = true;
                } else if (userNameIdentification && !passwordIdentification) {
                    Toast.makeText(activity, "Wrong password", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return userNameIdentification && passwordIdentification;
    }

// For stage 4
//    public static int createRandomCode() {
//        Random random = new Random();
//        int randomCode = random.nextInt(10000) + 1000;
//        return randomCode;
//    }


    // For stage 4
//    public boolean securityCodeCheck(String inputField, int securityCode) {
//        return Integer.parseInt(inputField) == securityCode;
//    }

    public String getUsername() {
        return username;
    }

    public boolean isUserNameIdentification() {
        return userNameIdentification;
    }

    public boolean isPasswordIdentification() {
        return passwordIdentification;
    }
}
