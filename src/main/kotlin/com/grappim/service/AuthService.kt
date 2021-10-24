package com.grappim.service

import com.grappim.data.*
import com.grappim.models.LoginUser
import com.grappim.models.RegisterUser
import com.grappim.models.UpdateUser
import com.grappim.models.User
import com.grappim.util.RegisterUserIncorrectFieldsException
import com.grappim.util.UserDoesNotExists
import com.grappim.util.UserExists
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class AuthService {

    fun register(registerUser: RegisterUser): User = transaction {
        val userInDatabase = UserEntity.find {
            (Users.username eq registerUser.user.username) or
                    (Users.email eq registerUser.user.username)
        }.firstOrNull()

        if (userInDatabase != null) throw UserExists()

        if (registerUser.user.username.isBlank() ||
            registerUser.user.email.isBlank() ||
            registerUser.user.password.isBlank()
        ) {
            val isUsernameBlank = registerUser.user.username.isBlank()
            val isEmailBlank = registerUser.user.email.isBlank()
            val isPasswordBlank = registerUser.user.password.isBlank()

            val emptyFields = StringBuilder("Empty fields are: ")
                .append(
                    if (isUsernameBlank) {
                        "username, "
                    } else {
                        ""
                    }
                ).append(
                    if (isEmailBlank) {
                        "email, "
                    } else {
                        ""
                    }
                ).append(
                    if (isPasswordBlank) {
                        "password"
                    } else {
                        ""
                    }
                )
            throw RegisterUserIncorrectFieldsException.BlankFieldsException(
                emptyFields.toString()
            )
        }

        val newUser = UserEntity.new {
            username = registerUser.user.username
            email = registerUser.user.email
            password = registerUser.user.password
        }

        val stock = StockEntity.new {
            merchantId = newUser.id.value
            stockName = "${newUser.username} store"
        }

        CashBoxEntity.new {
            name = "${stock.stockName} cashBox"
            merchantId = newUser.id.value
            stockId = stock.id.value
        }

        newUser.toUser()
    }

    fun loginAndGetUser(loginUser: LoginUser): User = transaction {
        UserEntity.find {
            (Users.email eq loginUser.user.email) and
                    (Users.password eq loginUser.user.password)
        }.firstOrNull()?.toUser() ?: throw UserDoesNotExists()
    }

    fun deleteUserById(
        id: String
    ) = transaction {
        Users.deleteWhere {
            Users.id eq UUID.fromString(id)
        }
    }

    fun getUserById(
        id: String
    ): User = transaction {
        getUser(id).toUser()
    }

    fun getUserByEmail(
        email: String
    ): User = transaction {
        UserEntity.find {
            Users.email eq email
        }.firstOrNull()?.toUser() ?: throw UserDoesNotExists()
    }

    fun updateUser(
        userId: String,
        updateUser: UpdateUser
    ): User = transaction {
        val user = getUser(userId)
        user.apply {
            email = updateUser.user.email
            password = updateUser.user.password
            username = updateUser.user.username
        }.toUser()
    }

    private fun getUser(id: String): UserEntity =
        UserEntity.findById(UUID.fromString(id)) ?: throw UserDoesNotExists()

}