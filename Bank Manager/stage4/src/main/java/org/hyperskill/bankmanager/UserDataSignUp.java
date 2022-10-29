package org.hyperskill.bankmanager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
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
    private Map<String, String> userInfoMap = new HashMap<>();
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


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Button button = (Button) findViewById(R.id.signUpButton);
        button.setOnClickListener(onButtonClick);

    }


    public boolean saveUserData() {
        String firstNameStr = firstName.getText().toString();
        String lastNameStr = lastname.getText().toString();
        String addressStr = address.getText().toString();
        String phoneStr = number.getText().toString();
        String userNameStr = userName.getText().toString();
        String passwordStr = password.getText().toString();

        boolean userAlreadyExists = false;
        //check if username exists
        for (int i = 0; i < userDataArray.size(); i++) {
            if (userDataArray.get(i).containsValue(userNameStr)) {
                userAlreadyExists = true;

                break;
            }
        }

        if (!userAlreadyExists) {
            userInfoMap.put("firstName", firstNameStr);
            userInfoMap.put("lastname", lastNameStr);
            userInfoMap.put("address", addressStr);
            userInfoMap.put("phoneNumber", phoneStr);
            userInfoMap.put("userName", userNameStr);
            userInfoMap.put("password", passwordStr);
            userInfoMap.put("balance", balance);

            userDataArray.add(userInfoMap);
        }
        return userAlreadyExists;
    }

    public Map<String, String> getUserInfoMap() {
        return userInfoMap;
    }

    public ArrayList<Map> getUserDataArray() {
        return userDataArray;
    }


}

