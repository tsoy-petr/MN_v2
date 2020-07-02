package com.android.hootor.mn_v2.domain.usecases

import io.reactivex.Single

interface UseCase<T, R> {
    fun execute(request: T?): Single<R>
}