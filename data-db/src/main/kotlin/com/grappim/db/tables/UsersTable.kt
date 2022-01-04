package com.grappim.db.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column

private const val usersTableName = "users_table"

object UsersTable : UUIDTable(
  name = usersTableName
) {

  val phone: Column<String> = varchar(
    name = "phone",
    length = 255
  ).uniqueIndex()

  val username: Column<String> = varchar(
    name = "username",
    length = 50
  ).uniqueIndex()

  val password = varchar(
    name = "password",
    length = 255
  )
}