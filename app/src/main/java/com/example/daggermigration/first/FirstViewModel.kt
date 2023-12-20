package com.example.daggermigration.first

import androidx.lifecycle.ViewModel
import javax.inject.InjectDagger1

class FirstViewModel @InjectDagger1 constructor(
    val repository: FirstRepository
) : ViewModel() {

    fun log() = repository.log()
}
