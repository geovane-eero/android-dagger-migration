package com.example.daggermigration.di

import com.example.daggermigration.second.SecondRepository
import dagger.Module
import dagger.ObjectGraph
import dagger.Provides

@Module()
class AuxModule(private val objectGraph: ObjectGraph) {

    @Provides
    fun providesRepository(): SecondRepository {
        return objectGraph.get(SecondRepository::class.java)
    }
}

