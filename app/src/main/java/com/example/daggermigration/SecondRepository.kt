package com.example.daggermigration

import android.util.Log
import javax.inject.Inject

class SecondRepository @Inject constructor(
    private val parameter: String
) {

    fun log() = Log.d("Dagger", "Second repository created $parameter")
}
