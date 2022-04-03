package com.grappim.domain.password

interface PasswordManager {
  fun validatePassword(attempt: String, encryptedPassword: String): Boolean
  fun hashPassword(password: String): String
  fun decryptPassword(password: String): String
}