package org.hyperskill.bankmanager

import org.hyperskill.bankmanager.internals.BankManagerUnitTest
import android.widget.Button
import android.widget.EditText
import org.hyperskill.bankmanager.R
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Stage1UnitTest : BankManagerUnitTest<MainActivity>(MainActivity::class.java) {


    @Test
    fun checkForMainScreen() {
        testActivity {
            MainScreenView()

        }
    }


    @Test
    fun checkForSignUp() {
        testActivity {
            SignUpView()
        }

        }


    @Test
    fun checkForlogIn() {
        testActivity {
            LogInView()

        }
    }


}
