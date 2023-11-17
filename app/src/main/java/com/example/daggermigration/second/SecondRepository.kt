package com.example.daggermigration.second

import android.util.Log
import javax.inject.InjectDagger1

class SecondRepository @InjectDagger1 constructor() {

    fun log() = Log.d("Dagger", "Second repository created ")
}
