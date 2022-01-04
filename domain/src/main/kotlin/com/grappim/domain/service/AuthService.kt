package com.grappim.domain.service

import com.grappim.domain.model.user.LoginUser
import com.grappim.domain.model.user.RegisterUser
import com.grappim.domain.model.user.UpdateUser
import com.grappim.domain.model.user.User

interface AuthService {

  fun register(registerUser: RegisterUser): User
  fun loginAndGetUser(loginUser: LoginUser): User
  fun deleteUserById(
    id: String
  ): Int

  fun getUserById(
    id: String
  ): User

  fun updateUser(
    userId: String,
    updateUser: UpdateUser
  ): User
}