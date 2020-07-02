package com.android.hootr.tsd.useCase

open class SingletonHolder<out T : Any, in A>(val creator: (A) -> T) {

    private val BLOCK = Any()

    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {

        val checkInstance = instance
        checkInstance?.let { return it }

        return synchronized(BLOCK) {

            val checkInstanceAgain = instance

            if(checkInstanceAgain != null) {
                checkInstanceAgain
            } else {

                creator(arg)

            }

        }

    }

}