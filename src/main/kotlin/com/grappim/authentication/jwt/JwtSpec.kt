package com.grappim.authentication.jwt

class JwtSpec(
    val privateKey: String,
    val issuer: String,
    val audience: String,
    val realm: String,
    val kid: String
)