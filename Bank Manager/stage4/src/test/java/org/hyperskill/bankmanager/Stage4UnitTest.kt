package org.hyperskill.bankmanager

import BankManagerUnitTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Stage4UnitTest : BankManagerUnitTest<MainActivity>(MainActivity::class.java) {

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
    fun convertFundsUSDtoGBP() {
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
        }
    }

    @Test
    fun convertFundsUSDtoEUR() {
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
            checkConversion("USD","EUR","120.0","120.00")
        }
    }

    @Test
    fun convertFundsGBPtoEUR() {
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
            checkConversion("GBP","EUR","120.0","136.80")
        }
    }


}