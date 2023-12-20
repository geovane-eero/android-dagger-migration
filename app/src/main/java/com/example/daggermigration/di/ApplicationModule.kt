package com.example.daggermigration.di

import com.example.daggermigration.first.FirstRepository
import com.example.daggermigration.first.FirstViewModel
import com.example.daggermigration.second.SecondRepository
import dagger.ModuleDagger1

@ModuleDagger1(
    injects = [
        FirstViewModel::class,
        FirstRepository::class,
        SecondRepository::class
    ],
    library = true
)
class ApplicationModule {
}
