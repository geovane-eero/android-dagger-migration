package com.example.daggermigration.di

import com.example.daggermigration.second.SecondFragment
import dagger.Component

@Component(
    modules = [AuxModule::class]
)
interface SecondComponent {

    fun inject(secondFragment: SecondFragment)
}
