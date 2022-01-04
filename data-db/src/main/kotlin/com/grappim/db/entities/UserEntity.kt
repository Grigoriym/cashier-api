package com.grappim.db.entities

import com.grappim.db.tables.UsersTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class UserEntity(
  id: EntityID<UUID>
) : UUIDEntity(id) {

  companion object : UUIDEntityClass<UserEntity>(UsersTable)

  var phone by UsersTable.phone
  var username by UsersTable.username
  var password by UsersTable.password

}