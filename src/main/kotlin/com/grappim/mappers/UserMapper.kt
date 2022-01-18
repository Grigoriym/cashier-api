package com.grappim.mappers

import com.grappim.data_service.model.LoginUserDTO
import com.grappim.data_service.model.RegisterUserDTO
import com.grappim.data_service.model.UpdateUserDTO
import com.grappim.domain.model.user.LoginUser
import com.grappim.domain.model.user.RegisterUser
import com.grappim.domain.model.user.UpdateUser

fun RegisterUserDTO.toDomain() = RegisterUser(
  phone = this.phone,
  email = this.email,
  password = this.password
)

fun LoginUserDTO.toDomain(): LoginUser = LoginUser(
  mobile = this.mobile,
  password = this.password
)

fun UpdateUserDTO.toDomain(): UpdateUser = UpdateUser(
  user = UpdateUser.User(
    phone = this.user.phone,
    email = this.user.email,
    password = this.user.password
  )
)