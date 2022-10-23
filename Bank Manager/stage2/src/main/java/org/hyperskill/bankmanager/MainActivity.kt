package org.hyperskill.bankmanager

import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
//import org.hyperskill.bankmanager.LogInUser.createRandomCode  // for stage 4
import org.hyperskill.bankmanager.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var securityCode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    private fun setSupportActionBar(toolbar: CoordinatorLayout) {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun method2(view: View) {
        val firstName = findViewById<EditText>(R.id.signUpFirstNameEt)
        val lastName = findViewById<EditText>(R.id.signUpLastNameEt);
        val address = findViewById<EditText>(R.id.signUpAddressEt);
        val phoneNumber = findViewById<EditText>(R.id.signUpPhoneNumberEt);
        val userName = findViewById<EditText>(R.id.signUpUsernameEt);
        val password = findViewById<EditText>(R.id.signUpPasswordEt);


        if (!checkInput(firstName, lastName, address, phoneNumber, userName, password)) {
            val newUser =
                UserDataSignUp(firstName, lastName, address, phoneNumber, userName, password);
            // check if username already exists
            if (!newUser.saveUserData()) {
                newUser.saveUserData()
                Toast.makeText(this@MainActivity, "New user created", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_signUp3_to_FirstFragment)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "User : " + userName.text.toString() + " already exists",
                    Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    // check for input fields not empty and for proper length of password
    private fun checkInput(
        firstName: EditText,
        lastName: EditText,
        address: EditText,
        phoneNumber: EditText,
        userName: EditText,
        password: EditText
    ): Boolean {
        var isFieldEmpty: Boolean = false;
        if (firstName.text.toString().isEmpty()) {
            firstName.error = "enter firstname"
            isFieldEmpty = true;
            //Toast.makeText(this@MainActivity, "Enter first name", Toast.LENGTH_SHORT).show();
        }
        if (lastName.text.toString().isEmpty()) {
            lastName.error = "enter lastname"
            isFieldEmpty = true;
            // Toast.makeText(this@MainActivity, "Enter last name", Toast.LENGTH_SHORT).show();
        }
        if (address.text.toString().isEmpty()) {
            address.error = "enter address"
            isFieldEmpty = true;
            // Toast.makeText(this@MainActivity, "Enter address", Toast.LENGTH_SHORT).show();
        }
        if (phoneNumber.text.toString().isEmpty()) {
            phoneNumber.error = "enter phone number"
            isFieldEmpty = true;
            // Toast.makeText(this@MainActivity, "Enter phone number", Toast.LENGTH_SHORT).show();
        }
        if (userName.text.toString().isEmpty()) {
            userName.error = "enter username"
            isFieldEmpty = true;
            // Toast.makeText(this@MainActivity, "Enter username", Toast.LENGTH_SHORT).show();
        }
        if (password.text.toString().isEmpty()) {
            password.error = "enter password"
            isFieldEmpty = true;
            //  Toast.makeText(this@MainActivity, "Enter password", Toast.LENGTH_SHORT).show();
        } else if (password.text.toString().length < 4) {
            password.error = "password must be at least 4 numbers long"
            isFieldEmpty = true;
            /*  Toast.makeText(
                  this@MainActivity,
                  "Password length needs to be 4 digits or more",
                  Toast.LENGTH_SHORT
              ).show();*/
        }
        return isFieldEmpty;

    }


    fun logInMethod(view: View) {

        var userNameInput = findViewById<EditText>(R.id.logInUserNameEt);
        var passwordInput = findViewById<EditText>(R.id.logInPasswordEt);
        if (userNameInput.text.toString().isEmpty()) {
            userNameInput.error = "enter username"
            Toast.makeText(this@MainActivity, "Enter username", Toast.LENGTH_SHORT).show();
        } else if (passwordInput.text.toString().isEmpty()) {
            passwordInput.error = "enter password"
            Toast.makeText(this@MainActivity, "Enter password", Toast.LENGTH_SHORT).show();
        } else {
            val objUserData = UserDataSignUp()
            val obj =
                LogInUser(
                    objUserData.userDataArray,
                    userNameInput,
                    passwordInput,
                    this@MainActivity
                );
            if (obj.userLogInDataCheck()) {
                Toast.makeText(this@MainActivity, "User logged in", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_SecondFragment_to_userMenu)
            }
        }

// for stage 4
//
//        fun showSecurityInputFields() {
//            val inputField = findViewById<EditText>(R.id.securityCodeInput)
//            inputField.visibility = View.VISIBLE
//
//            val buttonConfirm = findViewById<Button>(R.id.confirmCodeButton)
//            buttonConfirm.visibility = View.VISIBLE
//        }


//        if (obj.userLogInDataCheck()) {
//            showSecurityInputFields()
//            securityCode = createRandomCode()
//            Toast.makeText(this@MainActivity, "Security code : $securityCode", Toast.LENGTH_LONG)
//                .show();
//
//        }


    }

// for stage 4
//    fun securityCheck(view: View): Boolean {
//        var codeEntered = findViewById<EditText>(R.id.securityCodeInput)
//        val codeInput = codeEntered.text.toString()
//        val obj = LogInUser()
//        if (codeInput != null && codeInput != "") {
//            val isSecurityCodeCorrect: Boolean = obj.securityCodeCheck(codeInput, securityCode);
//            if (isSecurityCodeCorrect) {
//                Toast.makeText(this@MainActivity, "Log in successfully", Toast.LENGTH_SHORT).show()
//                return true;
//            } else if (!isSecurityCodeCorrect) {
//                Toast.makeText(this@MainActivity, "Wrong security code", Toast.LENGTH_SHORT).show()
//            }
//        }
//        Toast.makeText(this@MainActivity, "Enter code", Toast.LENGTH_SHORT).show()
//
//
//        return false;
//
//    }

    fun fundsDeposit(view: View) {
        val text = findViewById<EditText>(R.id.inputAddFunds)
        var obj = DepositFundsScreen()
        obj.addFunds(text);
    }


    fun fundsConverter(view: View) {
        val spinner: Spinner = findViewById(R.id.spinnerConvertFrom)
        val spinner2: Spinner = findViewById(R.id.spinnerConvertTo)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            spinner2.adapter = adapter

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2296) {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // perform action when allow permission success
                } else {
                    Toast.makeText(this, "Allow permission for storage access!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }


}