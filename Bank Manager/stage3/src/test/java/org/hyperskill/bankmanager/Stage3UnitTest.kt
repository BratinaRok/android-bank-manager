package org.hyperskill.bankmanager

import BankManagerUnitTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Stage3UnitTest : BankManagerUnitTest<MainActivity>(MainActivity::class.java) {


    @Test
    fun testNewUserSignUpTrue() {
        testActivity {
            newUserSignUp(
                "Jack",
                "Wert",
                "New York street 32",
                "3468821",
                "JaWe34",
                "3572"
            )
        }
    }


    @Test
    fun logInWithSecurityCodeFailEmptyfieldAtSecurityCode() {
        testActivity {
            newUserSignUp(
                "Jack",
                "Wert",
                "New York street 32",
                "3468821",
                "JaWe34",
                "3572"
            )


            logInUserWithSecurityCodeInput("JaWe34", "3572", "", securityCodeEmpty = true)
        }
    }

    @Test
    fun logInWithSecurityCodeFailWrongCodeInputAtSecurityCode() {
        testActivity {
            newUserSignUp(
                "Jack",
                "Wert",
                "New York street 32",
                "3468821",
                "JaWe34",
                "3572"
            )

            logInUserWithSecurityCodeInput("JaWe34", "3572", "0", wrongSecurityCode = true)
        }
    }

    @Test
    fun logInWithSecurityCodeSuccessful() {
        testActivity {
            newUserSignUp(
                "Jack",
                "Wert",
                "New York street 32",
                "3468821",
                "JaWe34",
                "3572"
            )

            logInUserWithSecurityCodeInput("JaWe34", "3572",null)
        }
    }



}