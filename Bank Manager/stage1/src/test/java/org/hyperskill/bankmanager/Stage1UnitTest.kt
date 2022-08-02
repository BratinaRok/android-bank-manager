package org.hyperskill.bankmanager

import android.widget.Button
import android.widget.EditText
import org.hyperskill.bankmanager.internals.AbstractUnitTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Stage1UnitTest : AbstractUnitTest<MainActivity>(MainActivity::class.java) {

    //check if log in button exists
    private val logInButton: Button by lazy {
        activity.findViewByString("log_in_button")
    }

    //check if signup button exists
    private val signUpButton: Button by lazy {
        activity.findViewByString("signUpButton")
    }

    // check if username EditText exists
    private val loginUsernameField: EditText by lazy {
        activity.findViewByString("userNameLogIn")
    }

    // check if password EditText exists
    private val loginPasswordField: EditText by lazy {
        activity.findViewByString("passwordLogIn")
    }

    private val firstNameField: EditText by lazy {
        activity.findViewByString("firstName")
    }

    private val lastNameField: EditText by lazy {
        activity.findViewByString("lastName")
    }

    private val addressField: EditText by lazy {
        activity.findViewByString("address")
    }

    private val phoneField: EditText by lazy {
        activity.findViewByString("phoneNumber")
    }

    private val usernameField: EditText by lazy {
        activity.findViewByString("username")
    }

    private val passwordField: EditText by lazy {
        activity.findViewByString("password")
    }




    @Test
    fun checkForlogIn() {

        testActivity {
            logInButton
            signUpButton

            logInButton.clickAndRun()
                .also { loginUsernameField;loginPasswordField;logInButton }



        }
    }

    @Test
    fun checkForSignUp() {
        testActivity {
            signUpButton
            signUpButton.clickAndRun()
                .also { firstNameField;lastNameField;addressField;phoneField;usernameField;passwordField;signUpButton }

        }
    }


}
