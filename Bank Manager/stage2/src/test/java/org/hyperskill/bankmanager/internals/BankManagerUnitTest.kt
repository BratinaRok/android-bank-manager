import android.app.Activity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import org.hyperskill.bankmanager.internals.AbstractUnitTest
import org.junit.Assert


open class BankManagerUnitTest<T : Activity>(clazz: Class<T>) : AbstractUnitTest<T>(clazz) {

    inner class SignUpView() {
        val firstName: EditText = activity.findViewByString("signUpFirstNameEt")
        val lastName: EditText = activity.findViewByString("signUpLastNameEt")
        val address: EditText = activity.findViewByString("signUpAddressEt")
        val phone: EditText = activity.findViewByString("signUpPhoneNumberEt")
        val userName: EditText = activity.findViewByString("signUpUsernameEt")
        val password: EditText = activity.findViewByString("signUpPasswordEt")
        val signUpButton: Button = activity.findViewByString("signUpButton")
    }

    inner class MainScreenView() {
        val logInButton: Button = activity.findViewByString("mainLogInButton")
        val singUpButton: Button = activity.findViewByString("mainSignUpButton")
        val imageBankManagerMainScreen: ImageView = activity.findViewByString("mainImage")
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
    // Todo inner classes for other views
}