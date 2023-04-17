/*
 * Copyright 2015-2019 Amazon.com, Inc. or its affiliates.  All Rights Reserved.
 */

package com.example.daggermigration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import kotlin.reflect.KClass

/*
 * This class is used to manage individual ViewModel stores. They are used to handle `shared viewModels`.
 * As shared viewModels are linked to the Activity Lifecycle we need a way to destroy those ViewModels
 * when they are not being used anymore. The default ViewModelStore implementation holds all ViewModels
 * on the same store, which would clear all ViewModel instances if we call ViewModelStore::clear. This
 * implementation creates a ViewModelStore for each ViewModel class, this way we can clear the store
 * individually. For default `getViewModel` this implementation is not used, we still use the default one.
 */
class ViewModelStoreManager {

    val individualModelStores = mutableMapOf<KClass<out ViewModel>, ViewModelStoreCounter>()

    inline fun <reified VM : ViewModel> getStore(): ViewModelStore {
        return individualModelStores.getOrPut(VM::class) { ViewModelStoreCounter() }.apply {
            counter++
        }.store
    }

    inline fun <reified VM : ViewModel> clearStore() {
        individualModelStores[VM::class]?.apply {
            counter--
            if (counter == 0) {
                store.clear()
                individualModelStores.remove(VM::class)
            }
        }
    }
}

data class ViewModelStoreCounter(
    var counter: Int = 0,
    val store: ViewModelStore = ViewModelStore()
)

fun lazyViewModelStoreManager() = lazy { ViewModelStoreManager() }

interface ViewModelStoreManagerHolder {

    val viewModelStoreManager: ViewModelStoreManager
}
