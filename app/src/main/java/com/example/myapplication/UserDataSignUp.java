package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDataSignUp extends AppCompatActivity {

    public EditText firstName;
    private EditText lastname;
    private EditText address;
    private EditText number;
    private EditText userName;
    private EditText password;
    private String balance;
    private Map<String, String> map = new HashMap<>();
    TextInputLayout textInputLayout;
    private static ArrayList<Map> userDataArray = new ArrayList<>();

    public UserDataSignUp() {
    }

    public UserDataSignUp(EditText firstName, EditText lastname, EditText address, EditText number, EditText userName, EditText password) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.address = address;
        this.number = number;
        this.userName = userName;
        this.password = password;
        this.balance = "0.0";

    }


    private View.OnClickListener onButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Button button = (Button) findViewById(R.id.button2);

        button.setOnClickListener(onButtonClick);

    }


    public void saveUserData() {
        String firstNameStr = firstName.getText().toString();
        String lastNameStr = lastname.getText().toString();
        String addressStr = address.getText().toString();
        String phoneStr = number.getText().toString();
        String userNameStr = userName.getText().toString();
        String passwordStr = password.getText().toString();

        map.put("firstName", firstNameStr);
        map.put("lastname", lastNameStr);
        map.put("address", addressStr);
        map.put("phoneNumber", phoneStr);
        map.put("userName", userNameStr);
        map.put("password", passwordStr);
        map.put("balance", balance);


        userDataArray.add(map);


    }

    public Map<String, String> getMap() {
        return map;
    }

    public ArrayList<Map> getUserDataArray() {
        return userDataArray;
    }


}

