package com.sylvia.h_medi.presentation.ui.bookAppointment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.domain.use_case.appointment.GetAppointmentDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookAppointmentViewModel @Inject constructor(
    private val getAppointmentDetailUseCase: GetAppointmentDetailUseCase
): ViewModel() {

    private val _state = mutableStateOf(BookAppointmantState())
    val state: State<BookAppointmantState> = _state

    init {
        getAppointmentList()
    }

    private fun getAppointmentList() {
        getAppointmentDetailUseCase.invoke(1).onEach {

            when (it) {
                is Resource.Success -> {
                    _state.value = BookAppointmantState(appointment = it.data)
                }

                is Resource.Error -> {
                    _state.value = BookAppointmantState(error = it.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = BookAppointmantState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

}