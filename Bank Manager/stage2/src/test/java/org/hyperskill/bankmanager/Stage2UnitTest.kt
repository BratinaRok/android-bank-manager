package org.hyperskill.bankmanager

import BankManagerUnitTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException


@RunWith(RobolectricTestRunner::class)
class Stage2UnitTest : BankManagerUnitTest<MainActivity>(MainActivity::class.java) {


    @Test
    fun testNewUserSignUpFalseUserName() {
        testActivity {
            newUserSignUp(
                "Bob",
                "Wert",
                "Great street 55",
                "43252356",
                "",
                "44753",
                userNameEmptyError = true
            )
        }
    }

    @Test
    fun testNewUserSignUpShortPassword() {
        testActivity {
            newUserSignUp(
                "Rob",
                "Beet",
                "Street w 43",
                "334342234",
                "robb",
                "123",
                passwordShortError = true
            )
        }
    }

    @Test
    fun testNewUserSignUpEmptyPassword() {
        testActivity {
            newUserSignUp(
                "Rob",
                "Beet",
                "Street w 43",
                "334342234",
                "robb",
                "",
                passwordEmptyError = true
            )
        }
    }

    @Test
    fun testNewUserSignUpFalsePhoneNumber() {
        testActivity {
            newUserSignUp(
                "Rob",
                "Beet",
                "Street w 43",
                "",
                "robb",
                "123535",
                phoneNumberError = true
            )
        }
    }

    @Test
    fun testNewUserSignUpFalseAddress() {
        testActivity {
            newUserSignUp(
                "Rob",
                "Beet",
                "",
                "43252356",
                "robb",
                "123543",
                addressError = true
            )
        }
    }

    @Test
    fun testNewUserSignUpFalseLastName() {
        testActivity {
            newUserSignUp(
                "Rob",
                "",
                "Great street 55",
                "43252356",
                "robb",
                "123543",
                lastNameError = true
            )
        }
    }

    @Test
    fun testNewUserSignUpFalseFirstName() {
        testActivity {
            newUserSignUp(
                "",
                "Wert",
                "Great street 55",
                "43252356",
                "robb",
                "12343",
                firstNameError = true
            )
        }
    }

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
    fun testNewUserSignUpTrue2() {
        testActivity {

            newUserSignUp(
                "Jon",
                "Don",
                "Wall Street 334",
                "5434526563",
                "jonD",
                "123533"
            )
        }
    }


    @Test
    fun checkLogInWithoutExtraSecurityUserNameAlreadyExists() {
        testActivity {
            newUserSignUp(
                "Jon",
                "Don",
                "Wall Street 334",
                "5434526563",
                "jonD2",
                "123533"
            )

            newUserSignUp(
                "Jon",
                "Don",
                "Wall Street 334",
                "5434526563",
                "jonD2",
                "123533",
                userNameExistsError = true
            )
        }


    }


//    @Before
//    fun clearData() {
//        val deleteCmd = ".\\adb shell pm clear bankmanager"
//        val runtime = Runtime.getRuntime()
//        try {
//            runtime.exec(deleteCmd)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }


    @Test
    fun checkLogInWithoutExtraSecurityFailUserAlreadyExists() {
        testActivity {
            newUserSignUp(
                "Jon",
                "Don",
                "Wall Street 334",
                "5434526563",
                "jonD",
                "123533",
                userNameExistsError = true
            )
            val mainScreenView = MainScreenView()
            mainScreenView.logInButton.clickAndRun().also {
                val logInView = LogInView()

                logInView.logInUserNameEt.text.append("jonD")

                logInView.logInPasswordEt.text.append("123533")

                logInView.logInButton.clickAndRun().also {
                    val userMenuView = UserMenuView()
                    assertEquals("Welcome", userMenuView.userMenuWelcomeText.text.toString())
                    assertEquals("jonD", userMenuView.userMenuUsernameText.text.toString())
                }
            }
        }
        // todo fix solution to pass this test, you should not keep mutating state on static variables
        // if you run test individually it passes
        // if you run all tests then if fails because it will consider the user jonD as already created,
        // but it shouldn't be, each @test should be a new clean state


    }
}

//todo test login fail for reasons it might fail
//}

