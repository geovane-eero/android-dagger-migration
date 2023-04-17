package com.example.daggermigration.di

import com.example.daggermigration.first.FirstRepository
import com.example.daggermigration.first.FirstViewModel
import dagger.Module

@Module(
    injects = [
        FirstViewModel::class,
        FirstRepository::class
    ],
    library = true
)
class ApplicationModule {
}
