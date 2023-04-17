package com.example.daggermigration.di

import com.example.daggermigration.second.SecondRepository
import com.example.daggermigration.second.SecondViewModel
import dagger.Module
import dagger.Provides

@Module(
    injects = [
        SecondRepository::class,
        SecondViewModel::class
    ],
    addsTo = ApplicationModule::class,
    library = true,
    complete = false
)
class AuxModule(private val parameter: String) {

    @Provides
    fun providesRepository() = SecondRepository(parameter)
}
