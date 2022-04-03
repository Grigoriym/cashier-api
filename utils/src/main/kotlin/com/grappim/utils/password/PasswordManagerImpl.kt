package com.grappim.utils.password

import at.favre.lib.crypto.bcrypt.BCrypt
import com.grappim.domain.password.PasswordManager
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class PasswordManagerImpl : PasswordManager {

  override fun validatePassword(attempt: String, encryptedPassword: String): Boolean =
    BCrypt
      .verifyer(BCrypt.Version.VERSION_2Y)
      .verify(attempt.toCharArray(), encryptedPassword.toCharArray())
      .verified

  override fun hashPassword(password: String): String =
    BCrypt
      .with(BCrypt.Version.VERSION_2Y)
      .hashToString(15, password.toCharArray())

  override fun decryptPassword(password: String): String {
    return AES256.decrypt(password, "cbdh380JHG61qVazmdkoiuhgt56f4fgf")
  }

  private object AES256 {
    private const val AES_ALGORITHM = "AES"
    private const val TRANSFORMATION = "AES/CBC/PKCS5Padding"

    private fun cipher(
      encryptionMode: Int,
      secretKey: String
    ): Cipher {
      val cipher = Cipher.getInstance(TRANSFORMATION)
      val secretKeySpec = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), AES_ALGORITHM)
      val ivParams = IvParameterSpec(secretKey.substring(0, 16).toByteArray(Charsets.UTF_8))
      cipher.init(encryptionMode, secretKeySpec, ivParams)
      return cipher
    }

    fun encrypt(str: String, secretKey: String): String {
      val encrypted = cipher(
        encryptionMode = Cipher.ENCRYPT_MODE,
        secretKey = secretKey
      ).doFinal(str.toByteArray(Charsets.UTF_8))
      return String(Base64.getEncoder().encode(encrypted))
    }

    fun decrypt(str: String, secretKey: String): String {
      val byteStr = Base64.getDecoder().decode(
        str.toByteArray(Charsets.UTF_8)
      )
      return String(cipher(Cipher.DECRYPT_MODE, secretKey).doFinal(byteStr))
    }
  }

}