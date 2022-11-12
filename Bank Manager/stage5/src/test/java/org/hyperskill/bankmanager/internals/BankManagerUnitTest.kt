import android.app.Activity
import android.content.pm.PackageManager
import android.text.InputType
import android.text.InputType.TYPE_CLASS_NUMBER
import android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import org.hyperskill.bankmanager.R
import org.hyperskill.bankmanager.internals.AbstractUnitTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.robolectric.shadows.ShadowToast
import java.io.File
import java.math.BigDecimal


// todo same hierarchy structure for all stages
open class BankManagerUnitTest<T : Activity>(clazz: Class<T>) : AbstractUnitTest<T>(clazz) {

    inner class ToolbarBackNavigation {
        val toolbar = activity.findNavController(R.id.nav_host_fragment_content_main)
    }


    inner class SignUpView() {
        val firstName: EditText by lazy {
            val view = activity.findViewByString<EditText>(
                "signUpFirstNameEt",
                "EditText firstName at Signup View was not found"
            )

            val actualInputType = view.inputType
            val expectedInputType =
                InputType.TYPE_CLASS_TEXT + InputType.TYPE_TEXT_VARIATION_PERSON_NAME
            assertTrue(
                "The input type for firstName should be textPersonName",
                expectedInputType == actualInputType
            )

            val actualHint = view.hint.toString().lowercase()
            val expectedHint = "firstname"
            assertEquals("Wrong hint for firstName", expectedHint, actualHint)

            view

        }
        val lastName: EditText by lazy {
            val view = activity.findViewByString<EditText>(
                "signUpLastNameEt",
                "EditText lastName at Signup View was not found"
            )
            val actualInputType = view.inputType
            val expectedInputType =
                InputType.TYPE_CLASS_TEXT + InputType.TYPE_TEXT_VARIATION_PERSON_NAME
            assertTrue(
                "The input type for lastName should be textPersonName",
                expectedInputType == actualInputType
            )

            val actualHint = view.hint.toString().lowercase()
            val expectedHint = "lastname"
            assertEquals("Wrong hint for lastname", expectedHint, actualHint)

            view
        }
        val address: EditText by lazy {
            val view = activity.findViewByString<EditText>(
                "signUpAddressEt",
                "EditText address at Signup View was not found"
            )
            val actualInputType = view.inputType
            val expectedInputType =
                InputType.TYPE_CLASS_TEXT + InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS

            assertTrue(
                "The input type for address should be textPostalAddress",
                expectedInputType == actualInputType
            )

            val actualHint = view.hint.toString().lowercase()
            val expectedHint = "address"
            assertEquals("Wrong hint for address", expectedHint, actualHint)

            view
        }
        val phone: EditText by lazy {
            val view = activity.findViewByString<EditText>(
                "signUpPhoneNumberEt",
                "EditText PhoneNumber at Signup View was not found"
            )

            val actualInputType = view.inputType
            val expectedInputType = InputType.TYPE_CLASS_PHONE
            assertTrue(
                "The inputType for phoneNumber should be phone",
                expectedInputType == actualInputType
            )

            val actualHint = view.hint.toString().lowercase()
            val expectedHint = "phone"
            assertEquals("Wrong hint for phoneNumber", expectedHint, actualHint)

            view
        }
        val userName: EditText by lazy {
            val view = activity.findViewByString<EditText>(
                "signUpUsernameEt",
                "EditText userName at Signup View was not found"
            )

            val actualInputType = view.inputType
            val expectedInputType = InputType.TYPE_CLASS_TEXT
            assertTrue(
                "The input type for username should be text",
                expectedInputType == actualInputType
            )

            val actualHint = view.hint.toString().lowercase()
            val expectedHint = "username"
            assertEquals("Wrong hint for username", expectedHint, actualHint)

            view
        }
        val password: EditText by lazy {
            val view = activity.findViewByString<EditText>(
                "signUpPasswordEt",
                "EditText password at Signup View was not found"
            )

            val actualInputType = view.inputType
            val expectedInputType =
                InputType.TYPE_CLASS_NUMBER + InputType.TYPE_NUMBER_VARIATION_PASSWORD
            assertTrue(
                "The input type for password should be  $expectedInputType, but it is $actualInputType",
                expectedInputType == actualInputType
            )

            val actualHint = view.hint.toString().lowercase()
            val expectedHint = "password"
            assertEquals("Wrong hint for password", expectedHint, actualHint)

            view
        }

        val signUpButton: Button by lazy {
            val view = activity.findViewByString<Button>(
                "signUpButton",
                "Button sign up at Signup View was not found"
            )

            view
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
            userNameEmptyError: Boolean,
            userNameExistsError: Boolean,
            passwordEmptyError: Boolean,
            passwordShortError: Boolean,
        ) {

            val actualFirstNameErrorMessage = firstName.error?.toString()
            val expectedFirstNameErrorMessage = "enter firstname"
            if (firstNameError) {
                assertEquals(
                    "Wrong firstname error message for empty input field",
                    expectedFirstNameErrorMessage,
                    actualFirstNameErrorMessage
                )
            }

            val actualLastNameErrorMessage = lastName.error?.toString()
            val expectedLastNameErrorMessage = "enter lastname"
            if (lastNameError) {
                assertEquals(
                    "Wrong lastname error message for empty input field",
                    expectedLastNameErrorMessage, actualLastNameErrorMessage
                )
            }

            val actualAddressErrorMessage = address.error?.toString()
            val expectedAddressErrorMessage = "enter address"
            if (addressError) {
                assertEquals(
                    "Wrong address error message for empty input field",
                    expectedAddressErrorMessage, actualAddressErrorMessage
                )
            }

            val actualPhoneNumberErrorMessage = phone.error?.toString()
            val expectedPhoneNumberErrorMessage = "enter phone number"
            if (phoneNumberError) {
                assertEquals(
                    "Wrong phone number error message for empty input field",
                    expectedPhoneNumberErrorMessage, actualPhoneNumberErrorMessage
                )
            }

            val actualUserNameErrorMessage = userName.error?.toString()
            val expectedUserNameEmptyErrorMessage = "enter username"
            val expectedUserNameExistsErrorMessage =
                "User : " + userName.text.toString() + " already exists"
            if (userNameEmptyError) {
                assertEquals(
                    "Wrong username error message for empty input field",
                    expectedUserNameEmptyErrorMessage, actualUserNameErrorMessage
                )
            } else if (userNameExistsError) {
                val actualUserNameExistsErrorMessage = ShadowToast.getTextOfLatestToast().toString()
                assertEquals(
                    "Wrong username error message for user already exists error",
                    expectedUserNameExistsErrorMessage, actualUserNameExistsErrorMessage
                )
            }


            val actualPasswordErrorMessage = password.error?.toString()
            val expectedErrorMessageForEmptyPassword = "enter password"
            val expectedErrorMessageForShortPassword = "password must be at least 4 numbers long"
            if (passwordEmptyError.or(passwordShortError)) {
                if (passwordEmptyError) {
                    assertEquals(
                        "Wrong password error message for empty input field",
                        expectedErrorMessageForEmptyPassword, actualPasswordErrorMessage
                    )
                } else {
                    assertEquals(
                        "Wrong password error message for minimum number length input",
                        expectedErrorMessageForShortPassword, actualPasswordErrorMessage
                    )
                }
            }
        }

    }

    inner class MainScreenView() {
        val logInButtonMainScreenView: Button by lazy {
            val view = activity.findViewByString<Button>(
                "mainLogInButton",
                "Button LogIn at Mainscreen View was not found"
            )
            view
        }
        val singUpButtonMainScreenView: Button by lazy {
            val view = activity.findViewByString<Button>(
                "mainSignUpButton",
                "Button SignUp at Mainscreen View was not found"
            )
            view
        }
        val imageBankManagerMainScreen: ImageView by lazy {
            val view = activity.findViewByString<ImageView>(
                "mainImage",
                "ImageView mainImage at Mainscreen View was not found"
            )
            view
        }
    }

    inner class LogInView() {
        val logInUserNameEt: EditText by lazy {
            val view = activity.findViewByString<EditText>(
                "logInUserNameEt",
                "EditText username at logIn View was not found"
            )

            val actualInputType = view.inputType
            val expectedInputType = InputType.TYPE_CLASS_TEXT
            assertTrue(
                "The input type for username should be text",
                expectedInputType == actualInputType
            )

            val actualHint = view.hint.toString().lowercase()
            val expectedHint = "username"
            assertEquals("Wrong hint for username", expectedHint, actualHint)

            view
        }

        val logInPasswordEt: EditText by lazy {
            val view = activity.findViewByString<EditText>(
                "logInPasswordEt",
                "EditText password at logIn View was not found"
            )

            val actualInputType = view.inputType
            val expectedInputType = TYPE_CLASS_NUMBER + TYPE_NUMBER_VARIATION_PASSWORD
            assertTrue(
                "The input type for password should be  $expectedInputType, but it is $actualInputType",
                expectedInputType == actualInputType
            )

            val actualHint = view.hint.toString().lowercase()
            val expectedHint = "password"
            assertEquals("Wrong hint for password", expectedHint, actualHint)

            view
        }

        val logInSecurityCode: EditText by lazy {
            val view = activity.findViewByString<EditText>(
                "securityCodeInput",
                "EditText securityCodeInput at login View was not found"
            )
            val actualInputType = view.inputType
            val expectedInputType = TYPE_CLASS_NUMBER
            assertTrue(
                "The input type for security code should be $expectedInputType, but it is $actualInputType",
                expectedInputType == actualInputType
            )

            val actualHint = view.hint.toString().lowercase()
            val expectedHint = "enter security code"
            assertEquals("Wrong hint for security code field", expectedHint, actualHint)

            view
        }

        val logInButtonAtLogInView: Button by lazy {
            val view = activity.findViewByString<Button>(
                "logInButtonLogInView",
                "Button login at Login View was not found"
            )

            view
        }

        val securityCodeButtonConfirm: Button by lazy {
            val view = activity.findViewByString<Button>(
                "confirmCodeButton",
                "Button confirmCodeButton at Login View was not found for security code button confirm"
            )

            view
        }


        fun logInUser(
            userNameInput: String = "",
            passwordInput: String = "",
            securityCodeInput: String? = ""
        ) {
            logInUserNameEt.text.append(userNameInput)
            logInPasswordEt.text.append(passwordInput)
            logInSecurityCode.text.append(securityCodeInput.toString())
        }

        fun assertInputFields(
            userNameError: Boolean,
            passwordError: Boolean,
            userDoesntExists: Boolean,
            wrongPassword: Boolean,
            securityCodeEmpty: Boolean,
            securityCodeWrong: Boolean
        ) {

            val actualUsernameErrorMessage = logInUserNameEt.error?.toString()
            val expectedUsernameErrorMessage = "enter username"
            if (userNameError) {
                assertEquals(
                    "Wrong userName error message for empty input field",
                    expectedUsernameErrorMessage,
                    actualUsernameErrorMessage
                )
            }

            val actualPasswordErrorMessage = logInPasswordEt.error?.toString()
            val expectedPasswordErrorMessage = "enter password"
            if (passwordError) {
                assertEquals(
                    "Wrong password error message for empty input field",
                    expectedPasswordErrorMessage,
                    actualPasswordErrorMessage
                )
            }

            val actualUserDoesntExistsMessage = ShadowToast.getTextOfLatestToast().toString()
            val expectedUserDoesntExistsErrorMessage = "No such user exists"
            if (userDoesntExists) {
                assertEquals(
                    "Wrong user Doesnt Exists error message for user Doesnt Exists",
                    expectedUserDoesntExistsErrorMessage,
                    actualUserDoesntExistsMessage
                )
            }

            val actualWrongPasswordMessage = ShadowToast.getTextOfLatestToast().toString()
            val expectedWrongPasswordErrorMessage = "Invalid credentials"
            if (wrongPassword) {
                assertEquals(
                    "Wrong password error message for wrong password input",
                    expectedWrongPasswordErrorMessage,
                    actualWrongPasswordMessage
                )
            }

            val actualsecurityCodeEmptyMessage = ShadowToast.getTextOfLatestToast().toString()
            val expectedsecurityCodeEmptyMessage = "Enter security code"
            if (securityCodeEmpty) {
                assertEquals(
                    "Wrong security code error message for empty security input field",
                    expectedsecurityCodeEmptyMessage,
                    actualsecurityCodeEmptyMessage
                )
            }


            val actualsecurityCodeWrongCodeMessage = ShadowToast.getTextOfLatestToast().toString()
            val expectedsecurityCodeWrongCodeMessage = "Wrong security code"
            if (securityCodeWrong) {
                assertEquals(
                    "Wrong security code error message for Wrong security code input",
                    expectedsecurityCodeWrongCodeMessage,
                    actualsecurityCodeWrongCodeMessage
                )
            }

        }

    }

    inner class UserMenuView() {
        val userMenuConvertFundsButton: Button by lazy {
            val view = activity.findViewByString<Button>(
                "userMenuConvertFundsButton",
                "Button convert Funds at usermenu View was not found"
            )

            view
        }

        val userMenuPayBillsButton: Button by lazy {
            val view = activity.findViewByString<Button>(
                "userMenuPayBillsButton",
                "Button convert Funds at usermenu View was not found"
            )

            view
        }

        val userMenuDepositFundsButton: Button by lazy {
            val view = activity.findViewByString<Button>(
                "userMenuDepositFundsButton",
                "Button deposit Funds at usermenu View was not found"
            )

            view
        }

        val userMenuWithdrawFundsButton: Button by lazy {
            val view = activity.findViewByString<Button>(
                "userMenuWithdrawFundsButton",
                "Button withdraw Funds at usermenu View was not found"
            )

            view
        }

        val userMenuViewBalanceButton: Button by lazy {
            val view = activity.findViewByString<Button>(
                "userMenuViewBalanceButton",
                "Button view balance at usermenu View was not found"
            )

            view
        }

        val userMenuUsernameText: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "userMenuUsernameText",
                "TextView username at usermenu View was not found"
            )

            view
        }

        val userMenuWelcomeText: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "userMenuWelcomeText",
                "TextView welcome text at usermenu View was not found"
            )

            view
        }

    }

    inner class DepositFundsView() {
        val depositFundsTextDepositFunds: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "textdepositfunds",
                "TextView text deposit funds at DepositFunds View was not found "
            )
            val actualDepositFundsText = view.text.toString()
            val expectedDepositFundsText = "Deposit funds"
            assertEquals(
                "Wrong deposit funds text at depositFunds view ",
                expectedDepositFundsText, actualDepositFundsText
            )


            view
        }

        val depositFundsInputAddFunds: EditText by lazy {
            val view = activity.findViewByString<EditText>(
                "inputAddFunds",
                "EditText input add funds at DepositFunds View was not found "
            )
            val actualInputType = view.inputType
            val expectedInputType = InputType.TYPE_CLASS_NUMBER + InputType.TYPE_NUMBER_FLAG_DECIMAL
            assertEquals(
                "Wrong input type for deposit funds input add funds at DepositFunds view ",
                expectedInputType, actualInputType
            )
            val actualHintText = view.hint.toString()
            val expectedHintText = "enter amount to add"
            assertEquals(
                "Wrong hint text for deposit funds input add funds at DepositFunds view ",
                expectedHintText, actualHintText
            )

            view
        }

        val depositFundsButtonAddFunds: Button by lazy {
            val view = activity.findViewByString<Button>(
                "buttonAddFunds",
                "Button AddFunds at DepositFunds View was not found "
            )

            view
        }

        fun depositFunds(addFunds: Double = 0.0) {
            depositFundsInputAddFunds.append(addFunds.toString())
        }

    }


    inner class WithdrawFundsView() {

        val withdrawFundsTextWithdrawFunds: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "textWithdrawFunds",
                "TextView withdraw funds at withdrawFunds View was not found"
            )

            view
        }

        val withdrawFundsEnterAmountWithdraw: EditText by lazy {
            val view = activity.findViewByString<EditText>(
                "enterAmountWithdraw",
                "EditText enter amount at withdrawFunds View was not found"
            )

            view
        }

        val withdrawFundsWithdrawButton: Button by lazy {
            val view = activity.findViewByString<Button>(
                "withdrawButton",
                "Button withdraw at withdrawFunds View was not found"
            )
            view
        }

        fun withdrawFundsFromAccount(withdrawAmount: Double = 0.0) {
            withdrawFundsEnterAmountWithdraw.append(withdrawAmount.toString())
        }


    }


    inner class ViewBalanceView() {
        val viewBalanceTextViewBalance: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "textViewBalance",
                "TextView view balance at ViewBalance View was not found"
            )

            view
        }

        val viewBalanceShowBalanceText: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "showBalanceText",
                "TextView show Balance Text at ViewBalance View was not found"
            )

            view
        }

        val viewBalanceButton: Button by lazy {
            val view = activity.findViewByString<Button>(
                "backButtonShowBalanceView",
                "Button backButtonShowBalanceView at ViewBalance View was not found"
            )

            view
        }


        fun checkAccountBalance(expectedAmount: BigDecimal = BigDecimal.ZERO) {
            val actualAmount = viewBalanceShowBalanceText.text.toString()
            assertEquals(
                "Wrong account balance at Balance view",
                "%.2f".format(expectedAmount),
                actualAmount
            )
        }

    }

    inner class ConvertFundsView() {

        val convertFundsConvertFundsText: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "convertFundsText",
                "TextView convert Funds Text at convertFunds View was not found"
            )

            view
        }

        val convertFundsConvertFromText: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "convertFromText",
                "TextView convert from  Text at convertFunds View was not found"
            )

            view
        }

        val convertFundsConvertToText: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "convertToText",
                "TextView convert to  Text at convertFunds View was not found"
            )

            view
        }

        val convertFundsSpinnerConvertFrom: Spinner by lazy {
            val view = activity.findViewByString<Spinner>(
                "spinnerConvertFrom",
                "Spinner convert from  at convertFunds View was not found"
            )

            view
        }

        val convertFundsSpinnerConvertTo: Spinner by lazy {
            val view = activity.findViewByString<Spinner>(
                "spinnerConvertTo",
                "Spinner convert to  at convertFunds View was not found"
            )

            view
        }

        val convertFundsEnterAmountConvert: EditText by lazy {
            val view = activity.findViewByString<EditText>(
                "inputFundsToConvert",
                "EditText enter Amount  at convertFunds View was not found"
            )

            view
        }

        val convertFundsButtonConvert: Button by lazy {
            val view = activity.findViewByString<Button>(
                "buttonConvertFunds",
                "Button convert  at convertFunds View was not found"
            )

            view
        }

        fun checkDropdownConvertFromDropdownOptions(
            expectedDropdownOptionsLength: Int,
            itemOne: String,
            itemTwo: String,
            itemThree: String
        ) {
            val actualspinnerConvertFromOptionsLength = convertFundsSpinnerConvertFrom.count
            assertEquals(
                "Wrong number of options to convert from ",
                expectedDropdownOptionsLength,
                actualspinnerConvertFromOptionsLength
            )

            val acutalItemAtOne = convertFundsSpinnerConvertFrom.getItemAtPosition(0).toString()
            assertEquals("Wrong first Item at convert from dropdown ", itemOne, acutalItemAtOne)
            val acutalItemAtTwo = convertFundsSpinnerConvertFrom.getItemAtPosition(1).toString()
            assertEquals("Wrong second Item at convert from dropdown ", itemTwo, acutalItemAtTwo)
            val acutalItemAtThree = convertFundsSpinnerConvertFrom.getItemAtPosition(2).toString()
            assertEquals("Wrong third Item at convert from dropdown ", itemThree, acutalItemAtThree)


        }

        fun checkDropdownConvertToDropdownOptions(
            expectedDropdownOptionsLength: Int,
            itemOne: String,
            itemTwo: String,
            itemThree: String
        ) {
            val actualspinnerConvertToOptionsLength = convertFundsSpinnerConvertTo.count
            assertEquals(
                "Wrong number of options to convert to ",
                expectedDropdownOptionsLength,
                actualspinnerConvertToOptionsLength
            )

            val acutalItemAtOne = convertFundsSpinnerConvertTo.getItemAtPosition(0).toString()
            assertEquals("Wrong first Item at convert to dropdown ", itemOne, acutalItemAtOne)
            val acutalItemAtTwo = convertFundsSpinnerConvertTo.getItemAtPosition(1).toString()
            assertEquals("Wrong second Item at convert to dropdown ", itemTwo, acutalItemAtTwo)
            val acutalItemAtThree = convertFundsSpinnerConvertTo.getItemAtPosition(2).toString()
            assertEquals("Wrong third Item at convert to dropdown ", itemThree, acutalItemAtThree)
        }

        fun convertFundsSetCurrencies(
            selectCurrencyConvertFrom: String,
            selectCurrencyConvertTo: String,
        ) {
            var convertFrom = 0
            var convertTo = 0

            when (selectCurrencyConvertFrom) {
                "USD" -> convertFrom = 0
                "EUR" -> convertFrom = 1
                "GBP" -> convertFrom = 2
            }
            convertFundsSpinnerConvertFrom.setSelection(convertFrom, true)

            when (selectCurrencyConvertTo) {
                "USD" -> convertTo = 0
                "EUR" -> convertTo = 1
                "GBP" -> convertTo = 2
            }
            convertFundsSpinnerConvertTo.setSelection(convertTo, true)


        }
    }

    inner class BillPaymentView() {
        val billPaymentBillInformationText: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "getBillInformationText",
                "TextView bill information text at billPayment was not found"
            )

            view
        }

        val billPaymentReadFileButton: Button by lazy {
            val view = activity.findViewByString<Button>(
                "readFileButton",
                "Button read File  at billPayment was not found"
            )

            view
        }

        val billPaymentPaymentForText: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "paymentForText",
                "TextView payment For Text at billPayment was not found"
            )

            view
        }

        val billPaymentAccountNumberText: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "accountNumberText",
                "TextView account Number Text at billPayment was not found"
            )

            view
        }

        val billPaymentPriceText: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "priceText",
                "TextView price Text at billPayment was not found"
            )

            view
        }

        val billPaymentPaymentForField: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "paymentForField",
                "TextView payment For Field at billPayment was not found"
            )

            view
        }

        val billPaymentAccNumberInputField: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "accNumberInputField",
                "TextView acc Number InputField at billPayment was not found"
            )

            view
        }
        val billPaymentPriceInputField: TextView by lazy {
            val view = activity.findViewByString<TextView>(
                "priceInputField",
                "TextView price Input Field at billPayment was not found"
            )

            view
        }


        val billPaymentButtonPay: Button by lazy {
            val view = activity.findViewByString<Button>(
                "payButton",
                "Button pay at billPayment was not found"
            )

            view
        }

        val dropdownSpinner: Spinner by lazy {
            val view = activity.findViewByString<Spinner>(
                "billPaymentSelectBillSpinner",
                "Spinner at Bill payment View was not found"
            )

            view
        }

    }


    fun checkForMainScreenComponents() {
        val mainScreenView = MainScreenView();
        mainScreenView.logInButtonMainScreenView
        mainScreenView.singUpButtonMainScreenView
        mainScreenView.imageBankManagerMainScreen

    }

    fun checkForLogInComponents() {
        val logInView = LogInView()
        logInView.logInButtonAtLogInView
        logInView.logInPasswordEt
        logInView.logInUserNameEt

    }

    fun checkForSignUpComponents() {
        val signUpView = SignUpView()
        signUpView.firstName
        signUpView.lastName
        signUpView.address
        signUpView.phone
        signUpView.userName
        signUpView.password
        signUpView.signUpButton

    }

    fun checkForUserMenuComponents() {
        val userMenu = UserMenuView()
        userMenu.userMenuWelcomeText
        userMenu.userMenuUsernameText
        userMenu.userMenuViewBalanceButton
        userMenu.userMenuDepositFundsButton
        userMenu.userMenuWithdrawFundsButton
        userMenu.userMenuConvertFundsButton // stage 4
        userMenu.userMenuPayBillsButton // stage 5
    }

    fun checkForPayBilsViewComponents() {
        val usermenu = UserMenuView()
        usermenu.userMenuPayBillsButton.clickAndRun()

        val billPaymentView = BillPaymentView()
        billPaymentView.billPaymentButtonPay
        billPaymentView.billPaymentPriceText
        billPaymentView.billPaymentBillInformationText
        billPaymentView.billPaymentPaymentForText
        billPaymentView.billPaymentAccNumberInputField
        billPaymentView.billPaymentAccountNumberText
        billPaymentView.billPaymentPriceInputField
        billPaymentView.billPaymentReadFileButton
        billPaymentView.billPaymentPaymentForField
        billPaymentView.dropdownSpinner
    }


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
        userNameEmptyError: Boolean = false,
        userNameExistsError: Boolean = false,
        passwordEmptyError: Boolean = false,
        passwordShortError: Boolean = false,
    ) {

        MainScreenView().singUpButtonMainScreenView.clickAndRun()

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
                || userNameEmptyError
                || userNameExistsError
                || passwordEmptyError
                || passwordShortError

        singUpView.signUpButton.clickAndRun()

        if (isIncorrectInput) {
            singUpView.assertInputFields(
                firstNameError,
                lastNameError,
                addressError,
                phoneNumberError,
                userNameEmptyError,
                userNameExistsError,
                passwordEmptyError,
                passwordShortError
            )
        } else {
            MainScreenView() // test return to main
        }
    }


    fun logInUserMethod(
        userNameInput: String = "jond",
        passwordInput: String = "3432",
        securityCodeInput: String? = "",
        usernameError: Boolean = false,
        passwordError: Boolean = false,
        userDoesntExists: Boolean = false,
        securityCodeWrong: Boolean = false,
        wrongPassword: Boolean = false,
        securityCodeEmpty: Boolean = false
    ) {

        MainScreenView().logInButtonMainScreenView.clickAndRun()

        val logInView = LogInView()
        logInView.logInUser(userNameInput, passwordInput, securityCodeInput)
        val isIncorrectInput =
            usernameError || passwordError || securityCodeWrong || userDoesntExists || wrongPassword

        logInView.logInButtonAtLogInView.clickAndRun()

        if (isIncorrectInput) {
            logInView.assertInputFields(
                usernameError,
                passwordError,
                userDoesntExists,
                wrongPassword,
                securityCodeEmpty,
                securityCodeWrong
            )

        } else {
            val actualLogInToastMessageMessage = ShadowToast.getTextOfLatestToast().toString()
            val expectedLogInToastMessage = "User logged in"
            assertEquals(
                "Wrong Toast message if user logged in succesfully",
                expectedLogInToastMessage,
                actualLogInToastMessageMessage
            )

            val userMenuView = UserMenuView() // test goes to UserMenuView
            // at stage 2 checks for :
            userMenuView.userMenuDepositFundsButton
            userMenuView.userMenuWithdrawFundsButton
            userMenuView.userMenuViewBalanceButton
        }

    }

    fun addFundsToBankAccount(addFunds: Double = 0.0) {
        val userMenu = UserMenuView()
        userMenu.userMenuDepositFundsButton.clickAndRun()

        val depositFundsView = DepositFundsView()
        depositFundsView.depositFunds(addFunds)

        depositFundsView.depositFundsButtonAddFunds.clickAndRun()

        userMenu.userMenuUsernameText
        userMenu.userMenuViewBalanceButton
        userMenu.userMenuDepositFundsButton
        userMenu.userMenuWithdrawFundsButton


        val actualToastMessageMessage = ShadowToast.getTextOfLatestToast().toString()
        val expectedToastMessage = "Funds added"
        assertEquals(
            "Wrong Toast message for add funds",
            actualToastMessageMessage,
            expectedToastMessage
        )

    }

    fun checkAccountBalance(expectedAmount: Double = 0.0) {
        val userMenu = UserMenuView()
        userMenu.userMenuViewBalanceButton.clickAndRun()

        val viewBalance = ViewBalanceView()
        viewBalance.checkAccountBalance(expectedAmount.toBigDecimal())
        viewBalance.viewBalanceButton.clickAndRun()


    }


    fun withdraw(withdrawAmount: Double = 0.0) {
        val menu = UserMenuView()
        menu.userMenuWithdrawFundsButton.clickAndRun()

        val withdrawFunds = WithdrawFundsView()
        withdrawFunds.withdrawFundsEnterAmountWithdraw.append(withdrawAmount.toString())
        withdrawFunds.withdrawFundsWithdrawButton.clickAndRun()

    }

    //stage 3
    fun logInUserWithSecurityCodeInput(
        userNameInput: String,
        passwordInput: String,
        securityCodeInput: String?,
        usernameError: Boolean = false,
        passwordError: Boolean = false,
        securityCodeEmpty: Boolean = false,
        userDoesntExists: Boolean = false,
        wrongPassword: Boolean = false,
        wrongSecurityCode: Boolean = false
    ) {

        MainScreenView().logInButtonMainScreenView.clickAndRun()

        val logInView = LogInView()
        logInView.logInUser(userNameInput, passwordInput)
        val isIncorrectInput =
            usernameError || passwordError || securityCodeEmpty || userDoesntExists || wrongPassword || wrongSecurityCode

        logInView.logInButtonAtLogInView.clickAndRun()

        logInView.logInSecurityCode
        logInView.securityCodeButtonConfirm

        if (wrongSecurityCode) {
            logInView.logInSecurityCode.text.append(securityCodeInput.toString())
            logInView.securityCodeButtonConfirm.clickAndRun()
        } else if (securityCodeEmpty) {
            logInView.securityCodeButtonConfirm.clickAndRun()
        } else {
            logInView.logInSecurityCode.text.append(ShadowToast.getTextOfLatestToast().toString())
            logInView.securityCodeButtonConfirm.clickAndRun()
        }

        if (isIncorrectInput) {
            logInView.assertInputFields(
                usernameError,
                passwordError,
                userDoesntExists,
                wrongPassword,
                securityCodeEmpty,
                wrongSecurityCode
            )


        } else {
            val actualLogInToastMessageMessage = ShadowToast.getTextOfLatestToast().toString()
            val expectedLogInToastMessage = "Log in successfully"
            assertEquals(
                "Wrong Toast message if user logged in succesfully",
                expectedLogInToastMessage,
                actualLogInToastMessageMessage
            )

            val userMenuView = UserMenuView() // test goes to UserMenuView
            // at stage 2-3 checks for :
            userMenuView.userMenuDepositFundsButton
            userMenuView.userMenuWithdrawFundsButton
            userMenuView.userMenuViewBalanceButton
        }
    }

    // stage 4
    fun convertFundsCheckForItems() {
        val userMenu = UserMenuView()
        userMenu.userMenuConvertFundsButton.clickAndRun()
        val convertFundsView = ConvertFundsView()
        convertFundsView.convertFundsButtonConvert
        convertFundsView.convertFundsConvertFundsText
        convertFundsView.convertFundsConvertToText
        convertFundsView.convertFundsConvertFromText
        convertFundsView.convertFundsButtonConvert
        convertFundsView.convertFundsSpinnerConvertFrom
        convertFundsView.convertFundsEnterAmountConvert
        convertFundsView.convertFundsSpinnerConvertTo
    }

    // stage 4
    fun convertFundsCheckDropdownOptions(
        convertFromexpectedDropdownOptionsLength: Int,
        convertFromitemOne: String,
        convertFromitemTwo: String,
        convertFromitemThree: String,
        convertToexpectedDropdownOptionsLength: Int,
        convertToitemOne: String,
        convertToitemTwo: String,
        convertToitemThree: String
    ) {


        val userMenu = UserMenuView()
        userMenu.userMenuConvertFundsButton.clickAndRun()
        val convertFundsView = ConvertFundsView()

        convertFundsView.checkDropdownConvertFromDropdownOptions(
            convertFromexpectedDropdownOptionsLength,
            convertFromitemOne,
            convertFromitemTwo,
            convertFromitemThree
        )
        convertFundsView.checkDropdownConvertToDropdownOptions(
            convertToexpectedDropdownOptionsLength,
            convertToitemOne,
            convertToitemTwo,
            convertToitemThree
        )
    }

    // stage 4
    fun checkConversion(
        selectCurrencyConvertFrom: String,
        selectCurrencyConvertTo: String,
        amountToConvert: String,
        expectedCurrencyConvertedAmount: String
    ) {
        val userMenu = UserMenuView()
        userMenu.userMenuConvertFundsButton.clickAndRun()
        val convertFundsView = ConvertFundsView()

        convertFundsView.convertFundsSetCurrencies(
            selectCurrencyConvertFrom,
            selectCurrencyConvertTo
        )
        convertFundsView.convertFundsEnterAmountConvert.append(amountToConvert)

        convertFundsView.convertFundsButtonConvert.clickAndRun()
        val getConvertedAmountMessageToast = ShadowToast.getTextOfLatestToast().toString()
        val expectedMessageToast =
            "$amountToConvert $selectCurrencyConvertFrom funds, converted to $expectedCurrencyConvertedAmount $selectCurrencyConvertTo successfully"

        assertEquals(expectedMessageToast, getConvertedAmountMessageToast)

        val toolbarcontroller = ToolbarBackNavigation()
        toolbarcontroller.toolbar.navigateUp()
    }

    //stage 5
//    fun noBillLoaded() {
//        val userMenu = UserMenuView()
//        userMenu.userMenuPayBillsButton.clickAndRun()
//
//        val payBillView = BillPaymentView()
//        payBillView.billPaymentButtonPay.clickAndRun()
//        val expectedReadBillInfoMessage = "First read bill info"
//        val actualReadBillMessage = ShadowToast.getTextOfLatestToast()
//        assertEquals(
//            "Wrong error message for First read bill info at bill payment view",
//            expectedReadBillInfoMessage,
//            actualReadBillMessage
//        )
//    }

    fun checkForFileWritingPermisions(): Boolean {
        val permision = ActivityCompat.checkSelfPermission(
            activity,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permision == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }


    fun copyFile(copyFromPath: String, file: String, toPath: String, usermenu: UserMenuView) {
        //val usermenu = UserMenuView()
        usermenu.userMenuPayBillsButton.clickAndRun()

        val dir: File = File(toPath)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        val fileToCopy: File = File(copyFromPath + file)
        val to: File = File(toPath + file)
        fileToCopy.copyTo(to, true)

    }

    fun checkLoadedFileInSpinner(fileName: String) {
        val billPaymentView = BillPaymentView()
        var position: Int = 0
        when (fileName) {
            "rentalbill" -> position = 0
            "utillitybill" -> position = 1
        }

        if (billPaymentView.dropdownSpinner.count > 0) {
            val spinnerItem = billPaymentView.dropdownSpinner.getItemAtPosition(position)
            assertEquals("Wrong File or empty", fileName, spinnerItem)
        } else {
            java.lang.AssertionError("No file loaded in spinner for bill payment")
        }
    }

    fun selectSpinnerOptionAndReadDataFromFile(
        selectBill: String,
        paymentFor: String,
        accountNumber: String,
        price: String
    ) {
        val billPaymentView = BillPaymentView()

        var position: Int = 0
        when (selectBill) {
            "rentalbill" -> position = 0
            "utillitybill" -> position = 1
        }
        billPaymentView.dropdownSpinner.setSelection(position)
        billPaymentView.billPaymentReadFileButton.clickAndRun()


        val expectedPaymentFor = paymentFor
        val actualPaymentFor = billPaymentView.billPaymentPaymentForField.text.toString()
        assertEquals(
            "Wrong payment for description at BillPayment view",
            expectedPaymentFor,
            actualPaymentFor
        )

    }

    fun checkToastMessages(
        noBillLoadedMessage: Boolean,
        notEnoughBalanceMessage: Boolean,
        billSuccessfullyPaidMessage: Boolean,
        selectedBill: String?
    ) {

        val billPaymentView = BillPaymentView()

        if (noBillLoadedMessage) {
            if (billPaymentView.billPaymentPaymentForText.text != "" || billPaymentView.billPaymentPaymentForText.text.isNotEmpty()) {
                billPaymentView.billPaymentButtonPay.clickAndRun() // first click to delete data from previous test activity
            }
            billPaymentView.billPaymentButtonPay.clickAndRun()

                val expectedMessage = "First read bill info"
                val actualMessage = ShadowToast.getTextOfLatestToast()
                assertEquals(
                    "Wrong Toast message for no bill loaded at Billpayment view",
                    expectedMessage,
                    actualMessage
                )
        }
        if (notEnoughBalanceMessage) {
            billPaymentView.billPaymentReadFileButton.clickAndRun()
            billPaymentView.billPaymentButtonPay.clickAndRun()

            val expectedMessage = "Not enough balance in account"
            val actualMessage = ShadowToast.getTextOfLatestToast()
            assertEquals(
                "Wrong Toast message for not enough balance in account at Billpayment view",
                expectedMessage,
                actualMessage
            )
        }
        if (billSuccessfullyPaidMessage) {
            billPaymentView.billPaymentReadFileButton.clickAndRun()
            billPaymentView.billPaymentButtonPay.clickAndRun()

            var paymentForName : String = ""
            when (selectedBill) {
                "rentalbill" -> paymentForName = "Rental bill"
                "utillitybill" -> paymentForName = "Utility bill"
            }



            val expectedMessage =
                "$paymentForName was successfully paid"
            val actualMessage = ShadowToast.getTextOfLatestToast()
            assertEquals(
                "Wrong Toast message for bill successfull paid at Billpayment view",
                expectedMessage,
                actualMessage
            )
        }
    }

}





