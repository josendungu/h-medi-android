package com.sylvia.h_medi.common.utils

import com.sylvia.h_medi.presentation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Navigator {

    private val _sharedFlow =
        MutableSharedFlow<String>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(navTarget: Screen, params: List<String> = emptyList()) {
        if (params.isNotEmpty()){

            var target: String = navTarget.route

            for (param in params){
                target = "$target/$param"
            }

            _sharedFlow.tryEmit(target)

        } else {
            _sharedFlow.tryEmit(navTarget.route)
        }
    }

}