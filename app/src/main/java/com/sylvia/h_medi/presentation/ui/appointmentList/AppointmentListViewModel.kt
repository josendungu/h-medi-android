package com.sylvia.h_medi.presentation.ui.appointmentList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.common.utils.Navigator
import com.sylvia.h_medi.domain.model.Patient
import com.sylvia.h_medi.domain.use_case.appointment.GetPatientAppointmentsUseCase
import com.sylvia.h_medi.domain.use_case.patient.LoadLoggedInPatientUseCase
import com.sylvia.h_medi.presentation.Screen
import com.sylvia.h_medi.presentation.ui.doctorDetail.DoctorDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class AppointmentListViewModel @Inject constructor(
    private val getPatientAppointmentsUseCase: GetPatientAppointmentsUseCase,
    private val loadLoggedInPatientUseCase: LoadLoggedInPatientUseCase,
    private val  navigator: Navigator
): ViewModel() {

    private val _state = mutableStateOf(AppointmentListState())
    val state: State<AppointmentListState> = _state

    lateinit var patient: Patient

    init {
        loadPatientData()
    }

    private fun loadPatientData() {
        loadLoggedInPatientUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    patient = it.data!!
                    getAppointmentList(patient.patientId!!)
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

    fun navigateToAddAppointment() {
        val specialist = 0
        val toAppointment = true
        val params = listOf<String>( specialist.toString(), toAppointment.toString())
        navigator.navigateTo(Screen.DoctorListScreen, params)
    }

    private fun getAppointmentList(patientId: Int) {
        getPatientAppointmentsUseCase.invoke(patientId).onEach {

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