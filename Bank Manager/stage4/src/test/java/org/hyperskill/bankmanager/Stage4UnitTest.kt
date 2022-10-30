package org.hyperskill.bankmanager

import BankManagerUnitTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Stage4UnitTest : BankManagerUnitTest<MainActivity>(MainActivity::class.java) {
    val stage3Test = Stage3UnitTestB()


    @Test
    fun checkForSignUpAndLogInSuccessful() {
        testActivity {
            stage3Test.logInWithSecurityCodeSuccessful()
        }
    }

    @Test
    fun checkForConvertViewItems() {
        testActivity {
            newUserSignUp(
                "Jack",
                "Wert",
                "New York street 32",
                "3468821",
                "JaWe34",
                "3572"
            )

            logInUserWithSecurityCodeInput("JaWe34", "3572", null)
            convertFundsCheckDropdownOptions(
                3,
                "USD",
                "EUR",
                "GBP",
                3,
                "USD",
                "EUR",
                "GBP")
        }

    }


}