package org.hyperskill.bankmanager

import android.text.InputType.*
import android.widget.Button
import android.widget.EditText
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.hyperskill.bankmanager.internals.AbstractUnitTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Stage1UnitTestB : AbstractUnitTest<MainActivity>(MainActivity::class.java) {


    //check if log in button exists
    private val logInButton: Button by lazy {
       activity.findViewByString("logInButton")
    }

    //check if signup button exists
    private val signUpButton: Button by lazy {
        activity.findViewByString("signUpButton")
    }

    // check if username EditText at log in screen  exists
    private val loginUsernameField: EditText by lazy {
       val view =  activity.findViewByString<EditText>("userNameLogIn")

        val actualInputType = view.inputType
        val expectedInputType = TYPE_CLASS_TEXT
        assertTrue("The input type for username should be text",expectedInputType == actualInputType)

        val actualHint = view.hint.toString().lowercase()
        val expectedHint = "username"
        assertEquals("Wrong hint for username", expectedHint, actualHint)

        view

    }

    // check if password EditText at login exists
    private val loginPasswordField: EditText by lazy {
        val view =  activity.findViewByString<EditText>("passwordLogIn")
        val actualInputType = view.inputType
        val expectedInputType = TYPE_CLASS_NUMBER + TYPE_NUMBER_VARIATION_PASSWORD
        assertTrue("The input type for password should be  $expectedInputType, but it is $actualInputType",expectedInputType == actualInputType)

        val actualHint = view.hint.toString().lowercase()
        val expectedHint = "password"
        assertEquals("Wrong hint for password", expectedHint, actualHint)

        view
    }

    //Sign up form
    private val firstNameField: EditText by lazy {
        val view = activity.findViewByString<EditText>("firstName")
        val actualInputType = view.inputType
        val expectedInputType = TYPE_CLASS_TEXT + TYPE_TEXT_VARIATION_PERSON_NAME
        assertTrue("The input type for firstName should be textPersonName",expectedInputType == actualInputType)

        val actualHint = view.hint.toString().lowercase()
        val expectedHint = "firstname"
        assertEquals("Wrong hint for firstName", expectedHint, actualHint)

        view
    }

    private val lastNameField: EditText by lazy {
        val view = activity.findViewByString<EditText>("lastName")
        val actualInputType = view.inputType
        val expectedInputType = TYPE_CLASS_TEXT + TYPE_TEXT_VARIATION_PERSON_NAME
        assertTrue("The input type for lastName should be textPersonName",expectedInputType == actualInputType)

        val actualHint = view.hint.toString().lowercase()
        val expectedHint = "lastname"
        assertEquals("Wrong hint for lastname", expectedHint, actualHint)

        view
    }

    private val addressField: EditText by lazy {
        val view = activity.findViewByString<EditText>("address")
        val actualInputType = view.inputType
        val expectedInputType = TYPE_CLASS_TEXT  + TYPE_TEXT_VARIATION_POSTAL_ADDRESS

        assertTrue("The input type for address should be textPostalAddress",expectedInputType == actualInputType)

        val actualHint = view.hint.toString().lowercase()
        val expectedHint = "address"
        assertEquals("Wrong hint for address", expectedHint, actualHint)

        view
    }

    private val phoneField: EditText by lazy {
        val view = activity.findViewByString<EditText>("phoneNumber")

        val actualInputType = view.inputType
        val expectedInputType = TYPE_CLASS_PHONE
        assertTrue("The inputType for phoneNumber should be phone", expectedInputType == actualInputType)

        val actualHint = view.hint.toString().lowercase()
        val expectedHint = "phone"
        assertEquals("Wrong hint for phoneNumber", expectedHint, actualHint)

        view
    }

    private val usernameField: EditText by lazy {
    val view =  activity.findViewByString<EditText>("username")

        val actualInputType = view.inputType
        val expectedInputType = TYPE_CLASS_TEXT
        assertTrue("The input type for username should be text",expectedInputType == actualInputType)

        val actualHint = view.hint.toString().lowercase()
        val expectedHint = "username"
        assertEquals("Wrong hint for username", expectedHint, actualHint)

        view

    }

    private val passwordField: EditText by lazy {
        val view = activity.findViewByString<EditText>("password")

        val actualInputType = view.inputType
        val expectedInputType = TYPE_CLASS_NUMBER + TYPE_NUMBER_VARIATION_PASSWORD
        assertTrue("The input type for password should be  $expectedInputType, but it is $actualInputType",expectedInputType == actualInputType)

        val actualHint = view.hint.toString().lowercase()
        val expectedHint = "password"
        assertEquals("Wrong hint for password", expectedHint, actualHint)

        view
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
