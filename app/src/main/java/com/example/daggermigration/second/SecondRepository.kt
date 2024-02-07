package com.example.daggermigration.second

import android.util.Log
import javax.inject.Inject

class SecondRepository @Inject constructor() {

    fun log(message: String) {
        Log.d("Dagger", "Second repository created $message")
    }
}
