package com.grappim.db.mappers

import com.grappim.db.entities.UserEntity
import com.grappim.domain.model.user.User

fun UserEntity.toUser(): User = User(
  phone = this.phone,
  email = this.username,
  password = this.password,
  id = this.id.value.toString()
)