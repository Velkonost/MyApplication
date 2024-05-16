package com.velkonost.sample.myapplication.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

open class BaseViewModel: ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exc ->
        Log.d(this::class.java.toString(), exc.stackTraceToString())
    }

    val vmScope: CoroutineScope
        get() = CoroutineScope(SupervisorJob() + coroutineExceptionHandler)

}