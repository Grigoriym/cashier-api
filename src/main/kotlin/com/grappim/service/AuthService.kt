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
            (Users.username eq registerUser.user.username) or
                    (Users.phone eq registerUser.user.username)
        }.firstOrNull()

        if (userInDatabase != null) throw UserExists()

        checkUserFields(registerUser)

        val newUser = UserEntity.new {
            username = registerUser.user.username
            phone = registerUser.user.phone
            password = registerUser.user.password
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
        if (registerUser.user.username.isBlank() ||
            registerUser.user.phone.isBlank() ||
            registerUser.user.password.isBlank()
        ) {
            val isUsernameBlank = registerUser.user.username.isBlank()
            val isPhoneBlank = registerUser.user.phone.isBlank()
            val isPasswordBlank = registerUser.user.password.isBlank()

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
            (Users.phone eq loginUser.user.phone) and
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

    fun updateUser(
        userId: String,
        updateUser: UpdateUser
    ): User = transaction {
        val user = getUser(userId)
        user.apply {
            phone = updateUser.user.phone
            password = updateUser.user.password
            username = updateUser.user.username
        }.toUser()
    }

    private fun getUser(id: String): UserEntity =
        UserEntity.findById(UUID.fromString(id)) ?: throw UserDoesNotExists()

}