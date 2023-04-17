package com.example.daggermigration

import android.app.Application
import com.example.daggermigration.di.ApplicationComponent
import com.example.daggermigration.di.DaggerApplicationComponent


class DaggerApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder().build()
    }

}
