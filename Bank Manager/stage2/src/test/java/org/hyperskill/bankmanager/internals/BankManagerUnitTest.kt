import android.app.Activity
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

import org.hyperskill.bankmanager.internals.AbstractUnitTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

// todo same hierarchy structure for all stages
open class BankManagerUnitTest<T : Activity>(clazz: Class<T>) : AbstractUnitTest<T>(clazz) {

    inner class SignUpView() {
        val firstName: EditText by lazy {
            // Todo initial values assertions for all views
            activity.findViewByString("signUpFirstNameEt")
        }
        val lastName: EditText by lazy {
            // Todo initial values assertions for all views
            activity.findViewByString("signUpLastNameEt")
        }
        val address: EditText by lazy {
            // Todo initial values assertions for all views
            activity.findViewByString("signUpAddressEt")
        }
        val phone: EditText by lazy {
            // Todo initial values assertions for all views
            activity.findViewByString("signUpPhoneNumberEt")
        }
        val userName: EditText by lazy {
            // Todo initial values assertions for all views
            activity.findViewByString("signUpUsernameEt")
        }
        val password: EditText by lazy {
            // Todo initial values assertions for all views
            activity.findViewByString("signUpPasswordEt")
        }
        val signUpButton: Button by lazy {
            // Todo initial values assertions for all views
            activity.findViewByString("signUpButton")
        }

        fun signUpNewUser(
            firstNameInput: String,
            lastNameInput: String,
            addressInput: String,
            phoneInput: String,
            userNameInput: String,
            passwordInput: String
        ) {

            firstName.text.append(firstNameInput)
            lastName.text.append(lastNameInput)
            address.text.append(addressInput)
            phone.text.append(phoneInput)
            userName.text.append(userNameInput)
            password.text.append(passwordInput)
        }

        fun assertInputFields(
            firstNameError: Boolean,
            lastNameError: Boolean,
            addressError: Boolean,
            phoneNumberError: Boolean,
            userNameError: Boolean,
            passwordEmptyError: Boolean,
            passwordShortError: Boolean,
        ) {

            val actualFirstNameErrorMessage = firstName.error?.toString()
            val expectedFirstNameErrorMessage = "enter firstname"
            if (firstNameError) {
                // todo provide feedback message for every assertion
                assertEquals(
                    "You should always give a feedback message on assertions",  //TODO
                    expectedFirstNameErrorMessage,
                    actualFirstNameErrorMessage
                )
            }

            val actualLastNameErrorMessage = lastName.error?.toString()
            val expectedLastNameErrorMessage = "enter lastname"
            if (lastNameError) {
                // todo provide feedback message for every assertion
                assertEquals(expectedLastNameErrorMessage, actualLastNameErrorMessage)
            }

            val actualAddressErrorMessage = address.error?.toString()
            val expectedAddressErrorMessage = "enter address"
            if (addressError) {
                // todo provide feedback message for every assertion
                assertEquals(expectedAddressErrorMessage, actualAddressErrorMessage)
            }

            val actualPhoneNumberErrorMessage = phone.error?.toString()
            val expectedPhoneNumberErrorMessage = "enter phone number"
            if (phoneNumberError) {
                // todo provide feedback message for every assertion
                assertEquals(expectedPhoneNumberErrorMessage,
                    actualPhoneNumberErrorMessage)
            }

            val actualUserNameErrorMessage = userName.error?.toString()
            val expectedUserNameErrorMessage = "enter username"
            if (userNameError) {
                // todo provide feedback message for every assertion
                assertEquals(expectedUserNameErrorMessage, actualUserNameErrorMessage)
            }


            val actualErrorMessage = password.error?.toString()
            val expectedErrorMessageForEmptyPassword = "enter password"
            val expectedErrorMessageForShortPassword = "password must be at least 4 numbers long"
            if (passwordEmptyError.or(passwordShortError)) {
                if (passwordEmptyError) {
                    // todo provide feedback message for every assertion
                    assertEquals(expectedErrorMessageForEmptyPassword, actualErrorMessage)
                } else {
                    // todo provide feedback message for every assertion
                    assertEquals(expectedErrorMessageForShortPassword, actualErrorMessage)
                }
            }
        }

    }

    inner class MainScreenView() {
        val logInButton: Button by lazy {
            // Todo initial values assertions for all views
            activity.findViewByString("mainLogInButton")
        }
        val singUpButton: Button by lazy {
            // Todo initial values assertions for all views
            activity.findViewByString("mainSignUpButton")
        }
        val imageBankManagerMainScreen: ImageView by lazy {
            // Todo initial values assertions for all views
            activity.findViewByString("mainImage")
        }
    }

    inner class LogInView() {
        val logInUserNameEt: EditText by lazy {
            // todo copy this pattern of initialization assertions for all views, this was copied from stage1UnitTestB, but pay attention to renamed ids
            val view =  activity.findViewByString<EditText>("logInUserNameEt", "HERE IS THE CUSTOM MESSAGE FOR VIEW NOT FOUND")

            val actualInputType = view.inputType
            val expectedInputType = InputType.TYPE_CLASS_TEXT
            assertTrue("The input type for username should be text",
                expectedInputType == actualInputType)

            val actualHint = view.hint.toString().lowercase()
            val expectedHint = "username"
            assertEquals("Wrong hint for username", expectedHint, actualHint)

            view
        }
        val logInPasswordEt: EditText = activity.findViewByString("logInPasswordEt")
        val logInButton: Button = activity.findViewByString("logInButton")
    }

    inner class UserMenuView() {
        val userMenuConvertFundsButton: Button = activity.findViewByString("userMenuConvertFundsButton")
        val userMenuPayBillsButton: Button = activity.findViewByString("userMenuPayBillsButton")
        val userMenuDepositFundsButton: Button = activity.findViewByString("userMenuDepositFundsButton")
        val userMenuWithdrawFundsButton: Button = activity.findViewByString("userMenuWithdrawFundsButton")
        val userMenuViewBalanceButton: Button = activity.findViewByString("userMenuViewBalanceButton")
        val userMenuUsernameText: TextView = activity.findViewByString("userMenuUsernameText")
        val userMenuWelcomeText: TextView = activity.findViewByString("userMenuWelcomeText")
    }
    // Todo make inner classes for other screens

    fun newUserSignUp(
        firstNameInput: String = "Jon",
        lastNameInput: String = "Don",
        addressInput: String = "Wall Street 334",
        phoneInput: String = "5434526563",
        userNameInput: String = "jonD",
        passwordInput: String = "123533",
        firstNameError: Boolean = false,
        lastNameError: Boolean = false,
        addressError: Boolean = false,
        phoneNumberError: Boolean = false,
        userNameError: Boolean = false,
        passwordEmptyError: Boolean = false,
        passwordShortError: Boolean = false,
    ) {

        MainScreenView().singUpButton.clickAndRun()

        val singUpView = SignUpView()
        singUpView.signUpNewUser(
            firstNameInput,
            lastNameInput,
            addressInput,
            phoneInput,
            userNameInput,
            passwordInput
        )
        val isIncorrectInput = firstNameError
                || lastNameError
                || addressError
                || phoneNumberError
                || userNameError
                || passwordEmptyError
                || passwordShortError

        singUpView.signUpButton.clickAndRun()

        if (isIncorrectInput) {
            singUpView.assertInputFields(
                firstNameError,
                lastNameError,
                addressError,
                phoneNumberError,
                userNameError,
                passwordEmptyError,
                passwordShortError
            )
        } else {
            MainScreenView() // test return to main
        }
    }
}