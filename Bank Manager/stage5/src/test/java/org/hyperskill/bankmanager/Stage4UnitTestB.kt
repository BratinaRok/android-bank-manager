package org.hyperskill.bankmanager

import BankManagerUnitTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Stage4UnitTestB : BankManagerUnitTest<MainActivity>(MainActivity::class.java) {

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


    @Test
    fun convertFundsSuccess() {
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
            addFundsToBankAccount(400.0)
            checkConversion("USD","GBP","120.0","105.24")
            checkConversion("USD","EUR","120.0","105.24")



        }
    }


}