package com.example.daggermigration.di

import com.example.daggermigration.first.FirstRepository
import com.example.daggermigration.first.FirstViewModel
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface ApplicationModule {
}
