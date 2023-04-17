package com.example.daggermigration.first

import android.util.Log
import javax.inject.Inject

class FirstRepository @Inject constructor(
) {

    fun log() = Log.d("FirstRepository", "Repository created!")
}
