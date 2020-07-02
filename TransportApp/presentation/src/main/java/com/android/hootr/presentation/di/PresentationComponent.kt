package com.android.hootr.presentation.di

import com.android.hootr.domen.id.DomainComponent
import com.android.hootr.utilities.di.UtilsComponent
import dagger.Component

@Component(
    modules = [ScreenBindingModule::class],
    dependencies = [DomainComponent::class, UtilsComponent::class]
)
@PresentationScope
interface PresentationComponent {

    @Component.Builder
    interface Builder {

        fun domainComponent(domainComponent: DomainComponent): PresentationComponent.Builder
        fun utilsComponent(utilsComponent: UtilsComponent): PresentationComponent.Builder

        fun build(): PresentationComponent
    }
}