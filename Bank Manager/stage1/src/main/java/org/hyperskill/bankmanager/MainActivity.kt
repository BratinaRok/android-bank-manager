package com.example.myapplication

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
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.LogInUser.createRandomCode
import com.example.myapplication.databinding.ActivityMainBinding


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
        Toast.makeText(this@MainActivity, "New user created", Toast.LENGTH_SHORT).show();

        var firstName = findViewById<EditText>(R.id.firstName);
        var lastName = findViewById<EditText>(R.id.lastName);
        var address = findViewById<EditText>(R.id.address);
        var phoneNumber = findViewById<EditText>(R.id.phoneNumber);
        var userName = findViewById<EditText>(R.id.username);
        var password = findViewById<EditText>(R.id.password);


        val newUser = UserDataSignUp(firstName, lastName, address, phoneNumber, userName, password);
        newUser.saveUserData();
    }


    fun logInMethod(view: View) {
        var userNameInput = findViewById<EditText>(R.id.userNameLogIn);
        var passwordInput = findViewById<EditText>(R.id.passwordLogIn);
        val objUserData = UserDataSignUp()
        val obj = LogInUser(objUserData.userDataArray, userNameInput, passwordInput);


        fun showSecurityInputFields() {
            val inputField = findViewById<EditText>(R.id.securityCodeInput)
            inputField.visibility = View.VISIBLE

            val buttonConfirm = findViewById<Button>(R.id.confirmCodeButton)
            buttonConfirm.visibility = View.VISIBLE
        }


        if (obj.userLogInDataCheck()) {
            showSecurityInputFields()
            securityCode = createRandomCode()
            Toast.makeText(this@MainActivity, "Security code : $securityCode", Toast.LENGTH_SHORT)
                .show();


        } else {
            Toast.makeText(this@MainActivity, "try again", Toast.LENGTH_SHORT).show();
        }


    }


    fun securityCheck(view: View) {
        var codeEntered = findViewById<EditText>(R.id.securityCodeInput)
        val codeInput = codeEntered.text.toString()
        val obj = LogInUser()
        if (obj.securityCodeCheck(codeInput, securityCode)) {
            Toast.makeText(this@MainActivity, "Log in successfully", Toast.LENGTH_SHORT).show()
            val b = findViewById<Button>(R.id.menuButton)
            b.visibility = View.VISIBLE;
        }
    }

    fun fundsDeposit(view: View) {
        val text = findViewById<EditText>(R.id.inputAddFunds)
        var obj =  DepositFundsScreen()
        obj.addFunds(text);
    }


     fun fundsConverter(view: View) {
         val spinner : Spinner = findViewById(R.id.spinner)
         val spinner2 : Spinner = findViewById(R.id.spinner1)
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