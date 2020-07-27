package com.android.hootor.quiz1c.remote.core

/**
 * Base Class for handling errors/failures/exceptions.
 */

sealed class Failure {
    object NetworkConnectionError : Failure()
    object ServerError : Failure()
    object AuthError : Failure()
    object TokenError : Failure()

    object EmailAlreadyExistError : Failure()
    object EmailNotRegisteredError : Failure()
    object CantSendEmailError : Failure()

    object AlreadyFriendError : Failure()
    object AlreadyRequestedFriendError : Failure()
    object ContactNotFoundError : Failure()

    object NoSavedAccountsError : Failure()

    object FilePickError : Failure()
}