package com.android.hootr.utilities.di

import android.app.Application
import android.content.Context
import com.android.hootr.utilities.resources.IResourceProvider
import com.android.hootr.utilities.resources.ResourceProvider
import dagger.*

@Component(modules = [UtilsModule::class])
interface UtilsComponent {

    fun getResourceProvider() : IResourceProvider

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): UtilsComponent
    }
}

@Module
abstract class UtilsModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun bindResourceProvider(context: Context): IResourceProvider {
            return ResourceProvider(context = context)
        }
    }

}