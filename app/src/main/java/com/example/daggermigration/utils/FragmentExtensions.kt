package com.example.daggermigration

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//import dagger.ObjectGraph

/*
* This extension function is used to avoid repeating ViewModelProvider.Factory
*/
@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel> Fragment.getViewModel(crossinline factory: () -> VM): VM {
    return ViewModelProvider(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>) = factory() as T
    }).get(VM::class.java)
}

@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel> Fragment.getSharedViewModel(crossinline factory: () -> VM): VM {
    val store = viewModelStoreManager.getStore<VM>()
    observeOnDestroy { viewModelStoreManager.clearStore<VM>() }
    return ViewModelProvider(store, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>) = factory() as T
    }).get(VM::class.java)
}

@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel> Fragment.getSharedViewModel(
    key: String,
    crossinline factory: () -> VM
): VM {
    val store = viewModelStoreManager.getStore<VM>()
    observeOnDestroy { viewModelStoreManager.clearStore<VM>() }
    return ViewModelProvider(store, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>) = factory() as T
    }).get(key, VM::class.java)
}

/*
 * Gets a ViewModel from Dagger's Graph, optionally pass a "buildModule" function to get the
 * ViewModel from a custom module
 */
//inline fun <reified VM : ViewModel> Fragment.getViewModelFromGraph(
//    noinline buildModule: (() -> Any)?
//): VM {
//    val graph = buildModule?.let { applicationComponent?.plus(it()) } ?: applicationGraph
//    return graph?.get(VM::class.java)!!
//}

/*
 * Injects a ViewModel via delegated property
 */
//inline fun <reified VM : ViewModel> Fragment.injectViewModel(
//    noinline buildModule: (() -> Any)? = null
//) = lazy {
//    getViewModel { getViewModelFromGraph<VM>(buildModule) }
//}

/*
 * Injects a shared ViewModel via delegated property. The injected ViewModel is bound to Activity's
 * lifecycle
 */
//inline fun <reified VM : ViewModel> Fragment.injectSharedViewModel(
//    noinline buildModule: (() -> Any)? = null
//) = lazy {
//    getSharedViewModel { getViewModelFromGraph<VM>(buildModule) }
//}

/*
 * Injects a shared ViewModel identified with a specific key via delegated property. The injected
 * ViewModel is bound to Activity's lifecycle. Suppose the view needs to share the view model with
 * another view that does not already pass a key, then:
 * 1. both can use the non-keyed method (in case that is not done already by other views
 * for the same view model to avoid sharing instances that shouldn't be shared), or
 * 2. both can call this method passing the exactly same key.
 */
//inline fun <reified VM : ViewModel> Fragment.injectSharedViewModel(
//    key: String,
//    noinline buildModule: (() -> Any)? = null
//) = lazy {
//    getSharedViewModel(key) { getViewModelFromGraph<VM>(buildModule) }
//}

//val Fragment.applicationComponent: ApplicationComponent?
//    get() = (context?.applicationContext as? DaggerApplication)?.applicationComponent

val Fragment.viewModelStoreManager: ViewModelStoreManager
    get() = (activity as ViewModelStoreManagerHolder).viewModelStoreManager

inline fun Fragment.observeOnDestroy(crossinline action: () -> Unit) {
    lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() = action()
    })
}

inline fun Fragment.observeOnStop(crossinline action: () -> Unit) {
    lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() = action()
    })
}

inline fun Fragment.observeOnPause(crossinline action: () -> Unit) {
    viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() = action()
    })
}

fun Fragment.observeOnResume(action: () -> Unit) {
    viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() = action()
    })
}

fun Fragment.observeOnResumeOnce(action: () -> Unit) {
    lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() {
            action()
            lifecycle.removeObserver(this)
        }
    })
}
