package com.example.daggermigration.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daggermigration.di.SecondViewModelAssistedFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject

class SecondViewModel @AssistedInject constructor(
    val repository: SecondRepository,
    @Assisted val message: String
) : ViewModel() {

    class Factory(
        private val assistedFactory: SecondViewModelAssistedFactory,
        private val name: String,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(name) as T
        }
    }

    fun log() {
        repository.log(message)
    }
}
