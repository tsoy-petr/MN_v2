package com.android.hootr.domen.usecases

interface UseCase<T, R> {
    suspend fun execute(request: T?, onSuccess: (R) -> Unit, onFailure: (String) -> Unit)
}