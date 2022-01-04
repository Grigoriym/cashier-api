package com.grappim.mappers

import com.grappim.db.entities.UserEntity
import com.grappim.domain.model.user.LoginUser
import com.grappim.domain.model.user.RegisterUser
import com.grappim.domain.model.user.UpdateUser
import com.grappim.model.LoginUserDTO
import com.grappim.model.RegisterUserDTO
import com.grappim.model.UpdateUserDTO
import com.grappim.model.UserDTO

fun UserEntity.toUserDao(): UserDTO = UserDTO(
  phone = this.phone,
  email = this.username,
  password = this.password,
  id = this.id.value.toString()
)

fun RegisterUserDTO.toRegisterUser() = RegisterUser(
  phone = this.phone,
  email = this.email,
  password = this.password
)

fun LoginUserDTO.toLoginUser(): LoginUser = LoginUser(
  mobile = this.mobile,
  password = this.password
)

fun UpdateUserDTO.toUpdateUser(): UpdateUser = UpdateUser(
  user = UpdateUser.User(
    phone = this.user.phone,
    email = this.user.email,
    password = this.user.password
  )
)