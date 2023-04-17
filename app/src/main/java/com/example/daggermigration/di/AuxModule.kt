package com.example.daggermigration.di

import com.example.daggermigration.second.SecondRepository
import com.example.daggermigration.second.SecondViewModel
import dagger.Module
import dagger.Provides

@Module
class AuxModule(private val parameter: String) {

    @Provides
    fun providesRepository() = SecondRepository(parameter)
}
