import android.app.Activity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import org.hyperskill.bankmanager.internals.AbstractUnitTest
import org.junit.Assert

open class BankManagerUnitTest<T : Activity>(clazz: Class<T>) : AbstractUnitTest<T>(clazz) {

    inner class MainScreenView() {

        fun checkIfExists() {
                val message = "Log in button doesn't exists at main screen"
                Assert.assertNotNull(message ,activity.findViewByString("mainLogInButton"))
            }

            val logInButton: Button by lazy { activity.findViewByString("mainLogIniButton") }
            val signUpButton: Button by lazy { activity.findViewByString("mainSignUpButton") }
            val imageBankManagerMainScreen: ImageView by lazy { activity.findViewByString("mainImage") }

        fun mainScreenCheckForText(logInButtonCheck : Boolean,
                                   signUpButtonCheck : Boolean,
                                   imageBankManagerMainScreenCheck : Boolean
        ) {

            val expectedLogInButtonText = "Log in"
            if (logInButtonCheck) {
                Assert.assertEquals("Log in button at main screen doesn't have correct text",
                    expectedLogInButtonText,logInButton.text)
            }
            val expectedSignUpButtonText= "Sign up"
            if (signUpButtonCheck) {
                Assert.assertEquals(
                    "Sign up button at main screen doesn't have correct text",
                    expectedSignUpButtonText,signUpButton.text)
            }
        }
    }


    inner class SignUpView() {
        val firstName: EditText = activity.findViewByString("signUpFirstNameEt")
        val lastName: EditText = activity.findViewByString("signUpLastNameEt")
        val address: EditText = activity.findViewByString("signUpAddressEt")
        val phone: EditText = activity.findViewByString("signUpPhoneNumberEt")
        val userName: EditText = activity.findViewByString("signUpUsernameEt")
        val password: EditText = activity.findViewByString("signUpPasswordEt")
        val signUpButton: Button = activity.findViewByString("signUpButton")


        fun assertInputFields(
            firstNameError: Boolean,
            lastNameError: Boolean,
            addressError: Boolean,
            phoneNumberError: Boolean,
            userNameError: Boolean,
            passwordEmptyError: Boolean,
            passwordShortError: Boolean,
        ) {
            // todo provide feedback message for every assertion
            val actualFirstNameErrorMessage = firstName.error?.toString()
            val expectedFirstNameErrorMessage = "enter firstname"
            if (firstNameError) {
                Assert.assertEquals(
                    "You should always give a feedback message on assertions",  //TODO
                    expectedFirstNameErrorMessage,
                    actualFirstNameErrorMessage
                )
            }

            val actualLastNameErrorMessage = lastName.error?.toString()
            val expectedLastNameErrorMessage = "enter lastname"
            if (lastNameError) {
                Assert.assertEquals(expectedLastNameErrorMessage, actualLastNameErrorMessage)
            }

            val actualAddressErrorMessage = address.error?.toString()
            val expectedAddressErrorMessage = "enter address"
            if (addressError) {
                Assert.assertEquals(expectedAddressErrorMessage, actualAddressErrorMessage)
            }

            val actualPhoneNumberErrorMessage = phone.error?.toString()
            val expectedPhoneNumberErrorMessage = "enter phone number"
            if (phoneNumberError) {
                Assert.assertEquals(expectedPhoneNumberErrorMessage,
                    actualPhoneNumberErrorMessage)
            }

            val actualUserNameErrorMessage = userName.error?.toString()
            val expectedUserNameErrorMessage = "enter username"
            if (userNameError) {
                Assert.assertEquals(expectedUserNameErrorMessage, actualUserNameErrorMessage)
            }


            val actualErrorMessage = password.error?.toString()
            val expectedErrorMessageForEmptyPassword = "enter password"
            val expectedErrorMessageForShortPassword = "password must be at least 4 numbers long"
            if (passwordEmptyError.or(passwordShortError)) {
                if (passwordEmptyError) {
                    Assert.assertEquals(expectedErrorMessageForEmptyPassword, actualErrorMessage)
                } else {
                    Assert.assertEquals(expectedErrorMessageForShortPassword, actualErrorMessage)
                }
            }
        }
    }



    inner class LogInView() {
        val logInUserNameEt: EditText = activity.findViewByString("logInUserNameEt")
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

}