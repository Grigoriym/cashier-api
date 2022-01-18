package com.grappim.data_service.service

import com.grappim.utils.RegisterUserIncorrectFieldsException
import com.grappim.utils.UserDoesNotExist
import com.grappim.utils.UserExists
import com.grappim.db.entities.CashBoxEntity
import com.grappim.db.entities.StockEntity
import com.grappim.db.entities.UserEntity
import com.grappim.db.mappers.toDomain
import com.grappim.db.tables.UsersTable
import com.grappim.domain.model.user.LoginUser
import com.grappim.domain.model.user.RegisterUser
import com.grappim.domain.model.user.UpdateUser
import com.grappim.domain.model.user.User
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.transaction
import com.grappim.domain.service.AuthService
import java.util.*

class AuthServiceImpl : AuthService {

  override fun register(registerUser: RegisterUser): User = transaction {
    val userInDatabase = UserEntity.find {
      (UsersTable.username eq registerUser.email) or
          (UsersTable.phone eq registerUser.email)
    }.firstOrNull()

    if (userInDatabase != null) throw UserExists()

    checkUserFields(registerUser)

    val newUser = UserEntity.new {
      username = registerUser.email
      phone = registerUser.phone
      password = registerUser.password
    }

    createAdditionalData(newUser)

    newUser.toDomain()
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
      if (isUsernameBlank) blankFieldsList.add("username is empty")
      if (isPhoneBlank) blankFieldsList.add("phone is empty")
      if (isPasswordBlank) blankFieldsList.add("password is empty")

      val emptyFields = blankFieldsList.joinToString()
      throw RegisterUserIncorrectFieldsException.BlankFieldsException(
        emptyFields
      )
    }
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

  override fun loginAndGetUser(
    loginUser: LoginUser
  ): User = transaction {
    UserEntity.find {
      (UsersTable.phone eq loginUser.mobile) and
          (UsersTable.password eq loginUser.password)
    }.firstOrNull()?.toDomain() ?: throw UserDoesNotExist()
  }


  override fun deleteUserById(
    id: String
  ): Int = transaction {
    UsersTable.deleteWhere {
      UsersTable.id eq UUID.fromString(id)
    }
  }

  override fun getUserById(
    id: String
  ): User = transaction {
    getUser(id).toDomain()
  }

  override fun updateUser(
    userId: String,
    updateUser: UpdateUser
  ): User = transaction {
    val user = getUser(userId)
    user.apply {
      phone = updateUser.user.phone
      password = updateUser.user.password
      username = updateUser.user.email
    }.toDomain()
  }

  private fun getUser(id: String): UserEntity =
    UserEntity.findById(UUID.fromString(id)) ?: throw UserDoesNotExist()

}