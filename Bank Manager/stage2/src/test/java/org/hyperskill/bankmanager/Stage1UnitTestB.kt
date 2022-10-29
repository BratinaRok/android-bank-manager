package org.hyperskill.bankmanager

import BankManagerUnitTestv1

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Stage1UnitTestB : BankManagerUnitTestv1<MainActivity>(MainActivity::class.java) {

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
            mainScreenView.logInButtonMainScreenView.clickAndRun().also {
                checkForLogInComponents()
            }
        }
    }

    @Test
    fun checkForSignUpComponentsTest() {
        testActivity {
            val mainScreenView = MainScreenView()
            mainScreenView.singUpButtonMainScreenView.clickAndRun().also {
                checkForSignUpComponents()
            }
        }
    }
}


