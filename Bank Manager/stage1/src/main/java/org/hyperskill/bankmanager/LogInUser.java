package org.hyperskill.bankmanager;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class LogInUser {
    private static String username;
    private String password;
    private ArrayList<Map> userData;
    private boolean logInIdentification = false;
    private boolean userNameIdentification = false;
    private boolean passwordIdentification = false;
    private int securityCode;

    public LogInUser() {
    }

    public LogInUser(ArrayList<Map> userData, EditText username, EditText password) {
        this.userData = userData;
        this.username = username.getText().toString();
        this.password = password.getText().toString();
    }


    public boolean userLogInDataCheck() {
        for (int i = 0; i < userData.size(); i++) {
            if (userData.get(i).get("userName").equals(username)) {
                System.out.println("Correct username");
                userNameIdentification = true;
                if (userData.get(i).get("password").equals(password)) {
                    passwordIdentification = true;
                    System.out.println("Correct password");

                }
            }
        }

        if (userNameIdentification && passwordIdentification) {
            logInIdentification = true;
        } else if (!userNameIdentification) {
            System.out.println("User " + username + " doesn't exists");
        } else if (!passwordIdentification) {
            System.out.println("Wrong password");
        }

        return logInIdentification;
    }


    public static int createRandomCode() {
        Random random = new Random();
       int randomCode = random.nextInt(10000) + 1000;
       return randomCode;
    }


    public boolean securityCodeCheck(String inputField, int securityCode) {
        return Integer.parseInt(inputField) == securityCode;
    }

    public String getUsername() {
        return username;
    }
}
