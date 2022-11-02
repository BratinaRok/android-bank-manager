package org.hyperskill.bankmanager

import BankManagerUnitTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)

class Stage5UnitTest : BankManagerUnitTest<MainActivity>(MainActivity::class.java) {

    @Test
    fun checkForComponents() {
        testActivity {
            
        }
    }



}