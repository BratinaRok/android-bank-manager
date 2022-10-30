package org.hyperskill.bankmanager.model

import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import java.util.*

class UserViewModel : ViewModel() {

    private val users = mutableMapOf<String, User>()
    private var currentUser: User? = null

    fun addUser(user: User) {
        users[user.userName] = user
    }

    fun containsUser(user: User): Boolean {
        return users.containsKey(user.userName)
    }

    fun getUser(userName: String): User? {
        return users[userName]
    }

    fun loginUser(user: User) {
        currentUser = user
    }

    fun getLoggedUser(): User {
        return currentUser ?: throw IllegalStateException("No user logged")
    }

    fun addFunds(toAdd: BigDecimal) {
        val currentFund = currentUser?.balance ?: BigDecimal.ZERO
        currentUser?.balance = currentFund.plus(toAdd)
    }

    fun withdrawFunds(toWithdraw : BigDecimal) {
        val currentFund = currentUser?.balance?:BigDecimal.ZERO
        currentUser?.balance = currentFund.minus(toWithdraw)
    }

    fun getFundsAsString(): String {
        return "%.2f".format(currentUser!!.balance)
    }

     fun  generateUserSecurityCodeForLogIn() : Int {
        val random =  Random()
        val randomCode = random.nextInt(10000) + 1000;
        return randomCode;

    }
}