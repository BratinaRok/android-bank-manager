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


    //Enable writing files to SD card
    @Rule
    fun grantPermissionRuleWrite(): GrantPermissionRule {
        return GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    //Enable read form SD card
    @Rule
    fun grantPermissionRuleRead(): GrantPermissionRule {
        return GrantPermissionRule.grant(Manifest.permission.READ_EXTERNAL_STORAGE)
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
    fun checkSpinnerdropdownOptions() {
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
            if (checkForFileWritingPermisions()) {
                val filesPath : String =".\\src\\test\\java\\org\\hyperskill\\bankmanager\\testfiles\\"
                val toPath = "sdcard/Download/New/"
                val usermenu = UserMenuView()
                copyFile(filesPath,"rentalbill.txt",toPath,usermenu)
                copyFile(filesPath,"utillitybill.txt",toPath,usermenu)
                checkLoadedFileInSpinner("rentalbill.txt")
                checkLoadedFileInSpinner("utillitybill.txt")
            }


        }
    }

    @Test
    fun checkReadFileData() {
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
            if (checkForFileWritingPermisions()) {
                val filesPath : String =".\\src\\test\\java\\org\\hyperskill\\bankmanager\\testfiles\\"
                val toPath = "sdcard/Download/New/"
                val usermenu = UserMenuView()
                copyFile(filesPath,"rentalbill.txt",toPath,usermenu)
                copyFile(filesPath,"utillitybill.txt",toPath,usermenu)
                checkLoadedFileInSpinner("rentalbill.txt")
                checkLoadedFileInSpinner("utillitybill.txt")
                selectSpinnerOptionAndReadDataFromFile("utillitybill.txt","Utillity bill","DE 3245 345 6578", "80")
                selectSpinnerOptionAndReadDataFromFile("rentalbill.txt","Rental bill","SE 3245 345 6345", "250")
            }
        }
    }


}