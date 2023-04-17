package com.example.daggermigration

import android.util.Log
import javax.inject.Inject

class FirstRepository @Inject constructor(
) {

    fun log() = Log.d("FirstRepository", "Repository created!")
}
