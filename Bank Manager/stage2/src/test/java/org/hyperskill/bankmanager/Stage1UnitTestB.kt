package org.hyperskill.bankmanager

import BankManagerUnitTest

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Stage1UnitTestB : BankManagerUnitTest<MainActivity>(MainActivity::class.java) {

    @Test
    fun checkMainScreenComponentsTest() {
        testActivity {
            checkForMainScreenComponents()
        }
    }

    @Test
    fun checkLogInScreenComponentsTest() {
        testActivity {
            val mainScreenView = MainScreenView()
            mainScreenView.logInButton.clickAndRun().also {
                checkForLogInComponents()
            }
        }
    }

    @Test
    fun checkForSignUpComponentsTest() {
        testActivity {
            val mainScreenView = MainScreenView()
            mainScreenView.singUpButton.clickAndRun().also {
                checkForSignUpComponents()
            }
        }
    }
}


