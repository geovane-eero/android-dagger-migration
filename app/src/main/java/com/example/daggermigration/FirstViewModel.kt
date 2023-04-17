package com.example.daggermigration

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class FirstViewModel @Inject constructor(
    val repository: FirstRepository
) : ViewModel() {

    fun log() = repository.log()
}
