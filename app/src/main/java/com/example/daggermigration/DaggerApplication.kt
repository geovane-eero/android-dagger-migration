package com.example.daggermigration

import android.app.Application
import com.example.daggermigration.di.ApplicationModule
import dagger.ObjectGraph

const val OBJECT_GRAPH_NAME = "dagger-object-graph"

class DaggerApplication : Application() {

    lateinit var objectGraph: ObjectGraph

    override fun onCreate() {
        super.onCreate()
        objectGraph = ObjectGraph.create(ApplicationModule())
    }

    override fun getSystemService(name: String): Any? {
        return if (name == OBJECT_GRAPH_NAME) objectGraph else super.getSystemService(name)
    }
}
