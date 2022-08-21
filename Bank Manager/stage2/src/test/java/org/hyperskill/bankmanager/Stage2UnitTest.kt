package org.hyperskill.bankmanager

import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import junit.framework.TestCase.assertEquals
import org.hyperskill.bankmanager.internals.AbstractUnitTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class Stage2UnitTest : AbstractUnitTest<MainActivity>(MainActivity::class.java) {


    private val signUpButtonAtMainScreen: Button by lazy {
        activity.findViewByString<Button>("signUpButton")

    }

    private val signUpNewUserCorrectInput by lazy {
        val firstName = activity.findViewByString<EditText>("firstName")
        val inputName = "John"
        firstName.text.append(inputName)

        val lastName = activity.findViewByString<EditText>("lastName")
        val inputLastName = "Doe"
        lastName.text.append(inputLastName)

        val address = activity.findViewByString<EditText>("address")
        val inputAddress = "TimeSquare, USA"
        address.text.append(inputAddress)

        val userName = activity.findViewByString<EditText>("username")
        val inputUserName = "johnDo15"
        userName.text.append(inputUserName)

        val password = activity.findViewByString<EditText>("password")
        val inputPassword = "4636"
        password.text.append(inputPassword)

    }


    private val signUpButtonAtSignUp: Button by lazy {
        val view = activity.findViewByString<Button>("signUpButton")

        view
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
        //TO DO check if fragment main screen is displayed


    }


    @Test
    fun newUserSignUp() {
        testActivity {
            signUpButtonAtMainScreen.clickAndRun().also {
                signUpNewUserCorrectInput
                signUpButtonAtSignUp.clickAndRun().also {
                    checkForErrorInputFieldsError; checkReturnToMainScreen
                }
            }
        }
    }
}
