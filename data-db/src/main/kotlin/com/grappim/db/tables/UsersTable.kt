package com.grappim.db.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.datetime

object UsersTable : UUIDTable(
  name = "users_table"
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

  val createdOn = datetime(
    name = "createdOn"
  )

  val updatedOn = datetime(
    name = "updatedOn"
  )
}