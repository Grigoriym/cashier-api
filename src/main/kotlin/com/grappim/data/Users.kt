package com.grappim.data

import com.grappim.models.User
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

private const val usersTableName = "users_table"

object Users : UUIDTable(
    name = usersTableName
) {

    val email: Column<String> = varchar(
        name = "email",
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


class UserEntity(
    id: EntityID<UUID>
) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserEntity>(Users)

    var email by Users.email
    var username by Users.username
    var password by Users.password

    fun toUser(): User = User(
        email = email,
        username = username,
        password = password,
        id = id.value.toString()
    )
}