package org.hyperskill.bankmanager

import android.widget.*
import junit.framework.TestCase.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hyperskill.bankmanager.internals.AbstractUnitTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLooper
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
class Stage3UnitTest : AbstractUnitTest<MainActivity>(MainActivity::class.java) {

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
                    }
                }
            }
        }
    }

    private val logInButtonAtMainScreen: Button by lazy {
        activity.findViewByString("logInButton")
    }

    private val checkReturnToMainScreen by lazy {
        //check if image exists at mainscreen
        activity.findViewByString<ImageView>("imageBankManagerMainScreen")
        //check if log in button exists
        activity.findViewByString<Button>("logInButton")
        //check if signup button exists
        activity.findViewByString<Button>("signUpButton")

    }


    private val welcomeMessage: TextView by lazy {
        activity.findViewByString("welcomeTextAtMainMenu")
    }

    private val userNameAtMainMenu: TextView by lazy {
        val userName = activity.findViewByString<TextView>("usernameText")
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

    private val securityCodeInputField: EditText by lazy {
        activity.findViewByString<EditText>("securityCodeInput")
    }

    private val confirmCodeButton: Button by lazy {
        activity.findViewByString<Button>("confirmCodeButton")
    }

    @Test
    fun testNewUserSignUpTrue() {
        newUserSignUp("Jack", "Wert", "New York street 32", "3468821", "JaWe34", "3572", true)
    }

    @Test
    fun checkLogInSuccessful() {
        testActivity {
            logInButtonAtMainScreen.clickAndRun().also {
                val userNameLogIn = activity.findViewByString<EditText>("userNameLogIn")
                userNameLogIn.text.append("JaWe34")
                val passwordLogIn = activity.findViewByString<EditText>("passwordLogIn")
                passwordLogIn.text.append("3572")
                val buttonLogIn = activity.findViewByString<Button>("logInButton")
                buttonLogIn.clickAndRun().also {
                    var securityCode = ShadowToast.getTextOfLatestToast().toString()
                    securityCodeInputField.append(securityCode)
                    confirmCodeButton.clickAndRun().also {
                        welcomeMessage;assertEquals("Welcome", welcomeMessage.text)
                        userNameAtMainMenu;assertEquals("JaWe34", userNameAtMainMenu.text)
                        depositFundsButton;withdrawFundsButton;viewBalanceButton;convertFundsButton;payBillsButton
                    }



                }
            }
        }
    }
}