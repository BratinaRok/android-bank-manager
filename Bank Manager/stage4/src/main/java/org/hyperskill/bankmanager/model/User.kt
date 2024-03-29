package org.hyperskill.bankmanager.model

import java.math.BigDecimal

data class User (
    val firstName: String,
    val lastname: String,
    val address: String,
    val number: String,
    val userName: String,
    val password: String,
    var balance: BigDecimal = BigDecimal.ZERO
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (userName != other.userName) return false

        return true
    }

    override fun hashCode(): Int {
        return userName.hashCode()
    }
}