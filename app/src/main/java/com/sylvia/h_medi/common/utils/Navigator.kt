package com.sylvia.h_medi.common.utils

import com.sylvia.h_medi.presentation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Navigator {

    private val _sharedFlow =
        MutableSharedFlow<Screen>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(navTarget: Screen) {
        _sharedFlow.tryEmit(navTarget)
    }

}