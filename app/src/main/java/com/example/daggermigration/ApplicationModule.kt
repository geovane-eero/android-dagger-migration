package com.example.daggermigration

import dagger.Module
import dagger.Provides

@Module(
    injects = [
        FirstViewModel::class,
        FirstRepository::class
    ],
    library = true
)
class ApplicationModule {
}
