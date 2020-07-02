package com.android.hootr.domen.id

import com.android.hootr.domen.id.modules.UseCasesModule
import dagger.Component

@Component(
    modules = [UseCasesModule::class]
)
@DomainScope
interface DomainComponent {

    @Component.Builder
    interface Builder {
        fun build(): DomainComponent
    }
}