package com.example.daggermigration.di

import com.example.daggermigration.second.SecondViewModel
import dagger.assisted.AssistedFactory

@AssistedFactory
interface SecondViewModelAssistedFactory {

    fun create(name: String): SecondViewModel
}

