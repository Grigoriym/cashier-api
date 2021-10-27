package com.grappim.service

import com.grappim.data.CashBoxEntity
import com.grappim.data.StockEntity
import com.grappim.data.UserEntity
import com.grappim.data.Users
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
            (Users.username eq registerUser.email) or
                    (Users.phone eq registerUser.email)
        }.firstOrNull()

        if (userInDatabase != null) throw UserExists()

        checkUserFields(registerUser)

        val newUser = UserEntity.new {
            username = registerUser.email
            phone = registerUser.phone
            password = registerUser.password
        }

        createAdditionalData(newUser)

        newUser.toUser()
    }

    private fun createAdditionalData(newUser: UserEntity) {
        val stock = StockEntity.new {
            merchantId = newUser.id.value
            stockName = "${newUser.username} store"
        }

        CashBoxEntity.new {
            name = "${stock.stockName} cashBox"
            merchantId = newUser.id.value
            stockId = stock.id.value
        }
    }

    private fun checkUserFields(registerUser: RegisterUser) {
        if (registerUser.email.isBlank() ||
            registerUser.phone.isBlank() ||
            registerUser.password.isBlank()
        ) {
            val isUsernameBlank = registerUser.email.isBlank()
            val isPhoneBlank = registerUser.phone.isBlank()
            val isPasswordBlank = registerUser.password.isBlank()

            val blankFieldsList = mutableListOf<String>()
            if (isUsernameBlank) blankFieldsList.add("username")
            if (isPhoneBlank) blankFieldsList.add("phone")
            if (isPasswordBlank) blankFieldsList.add("password")

            val emptyFields = blankFieldsList.joinToString()
            throw RegisterUserIncorrectFieldsException.BlankFieldsException(
                emptyFields
            )
        }
    }

    fun loginAndGetUser(loginUser: LoginUser): User = transaction {
        UserEntity.find {
            (Users.phone eq loginUser.phone) and
                    (Users.password eq loginUser.password)
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

    fun updateUser(
        userId: String,
        updateUser: UpdateUser
    ): User = transaction {
        val user = getUser(userId)
        user.apply {
            phone = updateUser.user.phone
            password = updateUser.user.password
            username = updateUser.user.email
        }.toUser()
    }

    private fun getUser(id: String): UserEntity =
        UserEntity.findById(UUID.fromString(id)) ?: throw UserDoesNotExists()

}