package org.hyperskill.bankmanager

import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

import junit.framework.TestCase.assertEquals
import org.hyperskill.bankmanager.internals.AbstractUnitTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class Stage2UnitTest : AbstractUnitTest<MainActivity>(MainActivity::class.java) {


    private val signUpButtonAtMainScreen: Button by lazy {
        activity.findViewByString("signUpButton")

    }

    private fun signUpNewUser(firstNameInput: String, lastNameInput: String, addressInput: String, phoneInput: String, userNameInput: String, passwordInput: String) {

        val firstName = activity.findViewByString<EditText>("firstName")
        firstName.text.append(firstNameInput)

        val lastName= activity.findViewByString<EditText>("lastName")
        lastName.text.append(lastNameInput)

        val address = activity.findViewByString<EditText>("address")
        address.text.append(addressInput)

        val phone = activity.findViewByString<EditText>("phoneNumber")
        phone.text.append(phoneInput)

        val userName = activity.findViewByString<EditText>("username")
        userName.text.append(userNameInput)

        val password = activity.findViewByString<EditText>("password")
        password.text.append(passwordInput)

    }


    private val signUpButtonAtSignUp: Button by lazy {
        activity.findViewByString<Button>("signUpButton")

    }

    private val checkForErrorInputFieldsError by lazy {
        var actualErrorMessage = "";
        var expectedErrorMessage = "";

        //first name check
        val firstName = activity.findViewByString<EditText>("firstName")
        if (firstName.error != null) {
            actualErrorMessage = firstName.error.toString()
            expectedErrorMessage = "enter firstname"
            assertEquals(expectedErrorMessage, actualErrorMessage)
        }

        //last name check
        val lastName = activity.findViewByString<EditText>("lastName")
        if (lastName.error != null) {
            actualErrorMessage = lastName.error.toString()
            expectedErrorMessage = "enter lastname"
            assertEquals(expectedErrorMessage, actualErrorMessage)
        }
        //address check
        val address = activity.findViewByString<EditText>("address")
        if (address.error != null) {
            actualErrorMessage = address.error.toString()
            expectedErrorMessage = "enter address"
            assertEquals(expectedErrorMessage, actualErrorMessage)
        }

        //phone number check
        val phoneNumber = activity.findViewByString<EditText>("phoneNumber")
        if (phoneNumber.error != null) {
            actualErrorMessage = phoneNumber.error.toString()
            expectedErrorMessage = "enter phone number"
            assertEquals(expectedErrorMessage, actualErrorMessage)
        }
        //username check
        val userName = activity.findViewByString<EditText>("username")
        if (userName.error != null) {
            actualErrorMessage = userName.error.toString()
            expectedErrorMessage = "enter username"
            assertEquals(expectedErrorMessage, actualErrorMessage)
        }
        //password check
        val password = activity.findViewByString<EditText>("password")
        if (password.error != null) {
            actualErrorMessage = password.error.toString()

            expectedErrorMessage = "enter password"
            assertEquals(expectedErrorMessage, actualErrorMessage)
        }
    }

    private val checkReturnToMainScreen by lazy {
        //check if image exists at mainscreen
        activity.findViewByString<ImageView>("imageBankManagerMainScreen")
        //check if log in button exists
        activity.findViewByString<Button>("logInButton")
        //check if signup button exists
        activity.findViewByString<Button>("signUpButton")


    }

    @Test
    fun newUserSignUpSuccessfullSignUp() {
        testActivity {
            signUpButtonAtMainScreen
            signUpButtonAtMainScreen.clickAndRun().also {
                signUpNewUser("John", "Doe", "123 Main Street", "1234567890", "jdoe", "12345")

                signUpButtonAtSignUp.clickAndRun().also {
                    checkReturnToMainScreen
                }
            }
        }
    }


    @Test
    fun newUserSignUpWrongInput() {
        testActivity {
            signUpButtonAtMainScreen
            signUpButtonAtMainScreen.clickAndRun().also {
            signUpNewUser("Jon", "Doe", "123 Main Street", "1234567890", "jdoe", "")
                signUpButtonAtSignUp.clickAndRun().also {
                    checkForErrorInputFieldsError
                }
            }
        }
    }
}
