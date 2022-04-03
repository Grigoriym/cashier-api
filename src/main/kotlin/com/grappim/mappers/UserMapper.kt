package com.grappim.mappers

import com.grappim.common_data.model.user.LoginUserDTO
import com.grappim.common_data.model.user.RegisterUserDTO
import com.grappim.common_data.model.user.UpdateUserDTO
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