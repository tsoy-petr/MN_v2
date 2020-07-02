package com.android.hootor.mn_v2.domain.interactor

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