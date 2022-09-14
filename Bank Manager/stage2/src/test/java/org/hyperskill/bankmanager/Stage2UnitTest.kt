package org.hyperskill.bankmanager


import BankManagerUnitTest
import android.widget.Button
import android.widget.EditText
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


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
                userNameError = true
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
            newUserSignUp("Rob",
                "Beet",
                "Street w 43",
                "",
                "robb",
                "123535",
                phoneNumberError = true)
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
    fun checkLogInWithoutExtraSecuritySuccessful() {
        testActivity {
            newUserSignUp()
            MainScreenView().logInButton.clickAndRun().also {
                val userNameLogIn = activity.findViewByString<EditText>("userNameLogIn")
                userNameLogIn.text.append("jonD")
                val passwordLogIn = activity.findViewByString<EditText>("passwordLogIn")
                passwordLogIn.text.append("123533")
                val buttonLogIn = activity.findViewByString<Button>("logInButton")

                buttonLogIn.clickAndRun().also {
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

//todo test login fail for reasons it might fail
}

