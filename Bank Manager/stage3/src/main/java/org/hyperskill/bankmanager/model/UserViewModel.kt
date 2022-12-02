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

    fun addFunds(toAdd: BigDecimal, currency: String) {
        val currentBalance = currentUser?.balanceMap?.get(currency)
        currentUser?.balanceMap?.put(currency, currentBalance?.plus(toAdd)!!)
    }

    fun withdrawFunds(toWithdraw: BigDecimal, currency: String) {
        val currentBalance =  currentUser?.balanceMap?.get(currency)
        currentUser?.balanceMap?.put(currency, currentBalance?.minus(toWithdraw)!!)
    }

    fun getFundsAsString(currency: String): String {
         return ("%.2f".format(currentUser!!.balanceMap[currency] ?: BigDecimal.ZERO))
    }

    fun convertFunds(fromCurrency: String, toCurrency: String, amountToConvert : BigDecimal, convertedAmount : BigDecimal) {
        if (currentUser?.balanceMap?.get(fromCurrency)!! >= amountToConvert) {
            val currentBalanceConvertFromCurrency =  currentUser?.balanceMap?.get(fromCurrency)
            currentUser?.balanceMap?.put(fromCurrency, currentBalanceConvertFromCurrency?.minus(amountToConvert)!!)

            val currentBalanceConvertToCurrency = currentUser?.balanceMap?.get(toCurrency)
            currentUser?.balanceMap?.put(toCurrency,currentBalanceConvertToCurrency?.plus(convertedAmount)!!)



            currentUser?.balanceMap?.get(fromCurrency)?.minus(amountToConvert)
            currentUser?.balanceMap?.get(toCurrency)?.plus(convertedAmount)
        }
    }

    fun generateUserSecurityCodeForLogIn(): Int {
        val random = Random()
        val randomCode = random.nextInt(10000) + 1000;
        return randomCode;

    }
}