package com.grappim.authentication.jwt

import com.auth0.jwk.JwkProvider
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.grappim.models.LoginUser
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*

class JwtController(
    private val spec: JwtSpec,
    private val jwkProvider: JwkProvider
) {

    fun generateToken(loginUser: LoginUser): String {
        val publicKey = jwkProvider.get(spec.kid).publicKey
        val keySpecPKCS8 = PKCS8EncodedKeySpec(Base64.getDecoder().decode(spec.privateKey))
        val privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpecPKCS8)
        return JWT.create()
            .withAudience(spec.audience)
            .withIssuer(spec.issuer)
            .withClaim("email", loginUser.user.email)
            .withExpiresAt(Date(System.currentTimeMillis() + 600_000))
            .sign(Algorithm.RSA256(publicKey as RSAPublicKey, privateKey as RSAPrivateKey))
    }

}