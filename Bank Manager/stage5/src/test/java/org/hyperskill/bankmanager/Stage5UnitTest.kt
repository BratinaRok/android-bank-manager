package org.hyperskill.bankmanager

import BankManagerUnitTest
import android.Manifest
import androidx.test.rule.GrantPermissionRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Stage5UnitTest : BankManagerUnitTest<MainActivity>(MainActivity::class.java) {
    @Rule
    fun grantPermissionRule(): GrantPermissionRule {
        return GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    val stage4UnitTestB = Stage4UnitTestB()

    @Test
    fun signUpAndLoginCheck() {
        testActivity {
            stage4UnitTestB.checkForSignUpAndLogInSuccessful()
        }
    }


    @Test
    fun checkForComponents() {
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
            checkForPayBilsViewComponents()
        }
    }

    @Test
    fun noBillLoadedMessage() {
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
            noBillLoaded()
        }
    }

    @Test
    fun readBillAndCheckForCorrectInfo() {
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

            //TODO IF ASKS FOR PERMISSIONS CLICK ALLOW
            // READ BILL AND CHECK FOR CORRECT INPUT
        }
    }


}