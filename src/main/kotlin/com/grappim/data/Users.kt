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


class UserEntity(
    id: EntityID<UUID>
) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserEntity>(Users)

    var phone by Users.phone
    var username by Users.username
    var password by Users.password

    fun toUser(): User = User(
        phone = this.phone,
        username = this.username,
        password = this.password,
        id = this.id.value.toString()
    )
}