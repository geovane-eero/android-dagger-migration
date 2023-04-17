package com.example.daggermigration.di

import com.example.daggermigration.second.SecondFragment
import dagger.Component

@Component(
    modules = [SecondModule::class]
)
interface SecondComponent {

    fun inject(secondFragment: SecondFragment)
}
