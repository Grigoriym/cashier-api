package com.grappim.util

class UserExists : RuntimeException()

sealed class RegisterUserIncorrectFieldsException(
    open val exceptionMessage: String
) : RuntimeException() {
    data class BlankFieldsException(
        override val exceptionMessage: String
    ) : RegisterUserIncorrectFieldsException(exceptionMessage)
}

class UserDoesNotExists : RuntimeException()

class AuthenticationException : RuntimeException()