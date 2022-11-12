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
                val filesPath: String =
                    ".\\src\\test\\java\\org\\hyperskill\\bankmanager\\testfiles\\"
                val toPath = "sdcard/Download/New/"
                val usermenu = UserMenuView()
                copyFile(filesPath, "rentalbill", toPath, usermenu)
                copyFile(filesPath, "utillitybill", toPath, usermenu)
                checkLoadedFileInSpinner("rentalbill")
                checkLoadedFileInSpinner("utillitybill")
            }


        }
    }

    @Test
    fun checkIfNoBillLoadedMessageAppear() {
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
                val filesPath: String =
                    ".\\src\\test\\java\\org\\hyperskill\\bankmanager\\testfiles\\"
                val toPath = "sdcard/Download/New/"
                val usermenu = UserMenuView()
                copyFile(filesPath, "rentalbill", toPath, usermenu = usermenu)
                checkToastMessages(
                    noBillLoadedMessage = true,
                    notEnoughBalanceMessage = false,
                    billSuccessfullyPaidMessage = false,
                    selectedBill = null
                )
            }
        }
    }

    @Test
    fun checkIfNoFundsMessageAppear() {
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
                val filesPath: String =
                    ".\\src\\test\\java\\org\\hyperskill\\bankmanager\\testfiles\\"
                val toPath = "sdcard/Download/New/"
                val usermenu = UserMenuView()
                copyFile(filesPath, "rentalbill", toPath, usermenu)
                copyFile(filesPath, "utillitybill", toPath, usermenu)
                usermenu.userMenuPayBillsButton.clickAndRun()

                checkToastMessages(
                    noBillLoadedMessage = false,
                    notEnoughBalanceMessage = true,
                    billSuccessfullyPaidMessage = false,
                    selectedBill = null
                )
            }
        }
    }

    @Test
    fun checkReadFileDataBill() {
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
                val filesPath: String =
                    ".\\src\\test\\java\\org\\hyperskill\\bankmanager\\testfiles\\"
                val toPath = "sdcard/Download/New/"
                val usermenu = this.UserMenuView()
                copyFile(filesPath, "rentalbill", toPath, usermenu)
                copyFile(filesPath, "utillitybill", toPath, usermenu)
                checkLoadedFileInSpinner("rentalbill")
                checkLoadedFileInSpinner("utillitybill")
                selectSpinnerOptionAndReadDataFromFile(
                    "utillitybill",
                    "Utillity bill",
                    "DE 3245 345 6578",
                    "80"
                )
                selectSpinnerOptionAndReadDataFromFile(
                    "rentalbill",
                    "Rental bill",
                    "SE 3245 345 6345",
                    "250"
                )
            }
        }
    }

    @Test
    fun payBillSuccessfully() {
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
            addFundsToBankAccount(300.0)

            if (checkForFileWritingPermisions()) {
                val filesPath: String =
                    ".\\src\\test\\java\\org\\hyperskill\\bankmanager\\testfiles\\"
                val toPath = "sdcard/Download/New/"

                val usermenu = UserMenuView()
                copyFile(filesPath, "rentalbill", toPath, usermenu)
                copyFile(filesPath, "utillitybill", toPath, usermenu)
                checkLoadedFileInSpinner("rentalbill")
                checkLoadedFileInSpinner("utillitybill")
                selectSpinnerOptionAndReadDataFromFile(
                    "utillitybill",
                    "Utillity bill",
                    "DE 3245 345 6578",
                    "80"
                )
                selectSpinnerOptionAndReadDataFromFile(
                    "rentalbill",
                    "Rental bill",
                    "SE 3245 345 6345",
                    "250"
                )

                checkToastMessages(
                    noBillLoadedMessage = false,
                    notEnoughBalanceMessage = false,
                    billSuccessfullyPaidMessage = true,
                    selectedBill = "rentalbill"
                )


            }

        }
    }


}