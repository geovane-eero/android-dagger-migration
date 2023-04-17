package com.example.daggermigration

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SecondViewModel @Inject constructor(
    val repository: SecondRepository
) : ViewModel() {

    fun log() = repository.log()
}
