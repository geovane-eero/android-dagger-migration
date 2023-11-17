package com.example.daggermigration.first

import android.util.Log
import javax.inject.InjectDagger1

class FirstRepository @InjectDagger1 constructor(
) {

    fun log() = Log.d("FirstRepository", "Repository created!")
}
