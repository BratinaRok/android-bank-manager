package org.hyperskill.bankmanager

import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

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


    private fun signUpNewUserInputFields(
        firstNameInput: String,
        lastNameInput: String,
        addressInput: String,
        phoneInput: String,
        userNameInput: String,
        passwordInput: String
    ) {

        val firstName = activity.findViewByString<EditText>("firstName")
        firstName.text.append(firstNameInput)

        val lastName = activity.findViewByString<EditText>("lastName")
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

            var expectedErrorMessageForEmptyField = "enter password"
            var expectedErrorMessageForShortPassword = "password must be at least 4 numbers long"

            if (actualErrorMessage == expectedErrorMessageForEmptyField) {
                assertEquals(expectedErrorMessageForEmptyField, actualErrorMessage)
            } else {
                assertEquals(expectedErrorMessageForShortPassword, actualErrorMessage)

            }

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

    private fun newUserSignUp(
        firstNameInput: String,
        lastNameInput: String,
        addressInput: String,
        phoneInput: String,
        userNameInput: String,
        passwordInput: String,
        isCorrectInput: Boolean
    ) {
        testActivity {
            signUpButtonAtMainScreen
            signUpButtonAtMainScreen.clickAndRun().also {
                signUpNewUserInputFields(
                    firstNameInput,
                    lastNameInput,
                    addressInput,
                    phoneInput,
                    userNameInput,
                    passwordInput
                )

                signUpButtonAtSignUp.clickAndRun().also {
                    if (isCorrectInput) {
                        checkReturnToMainScreen
                    } else {
                        checkForErrorInputFieldsError
                    }
                }
            }
        }
    }

    private val logInButtonAtMainScreen: Button by lazy {
        activity.findViewByString("logInButton")
    }

    private val welcomeMessage: TextView by lazy {
        activity.findViewByString("welcomeTextAtMainMenu")
    }

    private val userNameAtMainMenu: TextView by lazy {
       val userName =  activity.findViewByString<TextView>("usernameText")
        userName
    }

    private val depositFundsButton: Button by lazy {
        activity.findViewByString("depositFundsButton")
    }

    private val withdrawFundsButton: Button by lazy {
        activity.findViewByString("withdrawFundsButton")
    }

    private val viewBalanceButton: Button by lazy {
        activity.findViewByString("viewBalanceButton")
    }
    private val convertFundsButton: Button by lazy {
        activity.findViewByString("convertFundsButton")
    }

    private val payBillsButton: Button by lazy {
        activity.findViewByString("payBillsButton")
    }


    @Test
    fun testNewUserSignUpFalseUserName() {
        newUserSignUp("Bob", "Wert", "Great street 55", "43252356", "", "44753", false)
    }

    @Test
    fun testNewUserSignUpFalsePassword() {
        newUserSignUp("Rob", "Beet", "Street w 43", "334342234", "robb", "123", false)
    }

    @Test
    fun testNewUserSignUpFalsePhoneNumber() {
        newUserSignUp("Rob", "Beet", "Street w 43", "", "robb", "123535", false)
    }

    @Test
    fun testNewUserSignUpFalseAddress() {
        newUserSignUp("Rob", "Beet", "", "43252356", "robb", "123543", false)
    }

    @Test
    fun testNewUserSignUpFalseLastName() {
        newUserSignUp("Rob", "", "Great street 55", "43252356", "robb", "123543", false)
    }

    @Test
    fun testNewUserSignUpFalseFirstName() {
        newUserSignUp("", "Wert", "Great street 55", "43252356", "robb", "12343", false)
    }

    @Test
    fun testNewUserSignUpTrue() {
        newUserSignUp("Jack", "Wert", "New York street 32", "3468821", "JaWe34", "3572", true)
    }

    @Test
    fun testNewUserSignUpTrue2() {
        newUserSignUp("Jon", "Don", "Wall Street 334", "5434526563", "jonD", "123533", true)
    }


    @Test
    fun checkLogInSuccessful() {
        testActivity {
            logInButtonAtMainScreen.clickAndRun().also {
                val userNameLogIn =  activity.findViewByString<EditText>("userNameLogIn")
                userNameLogIn.text.append("jonD")
                val passwordLogIn =  activity.findViewByString<EditText>("passwordLogIn")
                passwordLogIn.text.append("123533")
                val buttonLogIn =  activity.findViewByString<Button>("logInButton")
                buttonLogIn.clickAndRun().also {
                    welcomeMessage;assertEquals("Welcome", welcomeMessage.text)
                    userNameAtMainMenu;assertEquals("jonD", userNameAtMainMenu.text)
                    depositFundsButton;withdrawFundsButton;viewBalanceButton;convertFundsButton;payBillsButton

                }

            }

                }
            }
        }


