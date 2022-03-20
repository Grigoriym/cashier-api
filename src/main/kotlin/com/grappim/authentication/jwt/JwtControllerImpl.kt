package com.grappim.authentication.jwt

import com.auth0.jwk.JwkProvider
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.grappim.domain.model.user.User
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*

class JwtControllerImpl(
  private val spec: JwtSpec,
  private val jwkProvider: JwkProvider
) : JwtController {

  companion object {
    const val CLAIM_ID = "id"
  }

  private fun generateToken(user: User): String {
    val publicKey = jwkProvider.get(spec.kid).publicKey
    val keySpecPKCS8 = PKCS8EncodedKeySpec(Base64.getDecoder().decode(spec.privateKey))
    val privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpecPKCS8)
    return JWT.create()
      .withAudience(spec.audience)
      .withIssuer(spec.issuer)
      .withClaim(CLAIM_ID, user.id.toString())
      .withExpiresAt(Date(System.currentTimeMillis() + 600_000))
      .sign(Algorithm.RSA256(publicKey as RSAPublicKey, privateKey as RSAPrivateKey))
  }

  fun getTokenForRespond(user: User): HashMap<String, String> {
    val token = generateToken(user)
    return hashMapOf("token" to token)
  }

  override fun getTokenForRespondAsString(user: User): String {
    return generateToken(user)
  }

}