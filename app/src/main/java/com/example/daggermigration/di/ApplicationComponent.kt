package com.example.daggermigration.di

import com.example.daggermigration.first.FirstFragment
import dagger.Component

@Component(
    modules = [ApplicationModule::class]
)
interface ApplicationComponent {

    fun inject(firstFragment: FirstFragment)

}
