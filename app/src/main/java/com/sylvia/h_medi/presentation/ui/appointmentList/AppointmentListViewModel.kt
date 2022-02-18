package com.sylvia.h_medi.presentation.ui.appointmentList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.domain.use_case.appointment.GetPatientAppointmentsUseCase
import com.sylvia.h_medi.presentation.ui.doctorDetail.DoctorDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class AppointmentListViewModel @Inject constructor(
    private val getPatientAppointmentsUseCase: GetPatientAppointmentsUseCase
): ViewModel() {

    private val _state = mutableStateOf(AppointmentListState())
    val state: State<AppointmentListState> = _state

    init {
        getAppointmentList()
    }

    private fun getAppointmentList() {
        getPatientAppointmentsUseCase.invoke(1).onEach {

            when (it) {
                is Resource.Success -> {
                    _state.value = AppointmentListState(appointments = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = AppointmentListState(error = it.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = AppointmentListState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }


}