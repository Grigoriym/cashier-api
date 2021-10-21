package com.grappim.service

import com.grappim.data.UserEntity
import com.grappim.data.Users
import com.grappim.models.LoginUser
import com.grappim.models.RegisterUser
import com.grappim.models.UpdateUser
import com.grappim.models.User
import com.grappim.util.UserDoesNotExists
import com.grappim.util.UserExists
import org.jetbrains.exposed.sql.and
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
        UserEntity.new {
            username = registerUser.user.username
            email = registerUser.user.email
            password = registerUser.user.password
        }.toUser()
    }

    fun loginAndGetUser(loginUser: LoginUser) = transaction {
        UserEntity.find {
            (Users.email eq loginUser.user.email) and
                    (Users.password eq loginUser.user.password)
        }.firstOrNull() ?: throw UserDoesNotExists()
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
    ) = transaction {
        val user = getUser(userId)
        user.apply {
            email = updateUser.user.email
            password = updateUser.user.password
            username = updateUser.user.username
        }
    }

    private fun getUser(id: String): UserEntity =
        UserEntity.findById(UUID.fromString(id)) ?: throw UserDoesNotExists()

}