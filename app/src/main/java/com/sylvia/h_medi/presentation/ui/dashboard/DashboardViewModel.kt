package com.sylvia.h_medi.presentation.ui.dashboard

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.common.Constants.TAG
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.common.utils.Navigator
import com.sylvia.h_medi.domain.use_case.doctor.GetAllDoctorsListUseCase
import com.sylvia.h_medi.domain.use_case.doctor.GetDashDoctorsUseCase
import com.sylvia.h_medi.domain.use_case.doctor.GetTopRatedDoctors
import com.sylvia.h_medi.domain.use_case.patient.LoadLoggedInPatientUseCase
import com.sylvia.h_medi.domain.use_case.specialist.GetSpecialistListUseCase
import com.sylvia.h_medi.presentation.Screen
import com.sylvia.h_medi.presentation.ui.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getSpecialistList: GetSpecialistListUseCase,
    private val getTopRatedDoctors: GetTopRatedDoctors,
    private val getDashDoctorsUseCase: GetDashDoctorsUseCase,
    private val loadLoggedInPatientUseCase: LoadLoggedInPatientUseCase,
    private val navigator: Navigator
):ViewModel() {

    private val _state = mutableStateOf(DashboardState())
    val state: State<DashboardState> = _state

    private val _stateDashDoctors = mutableStateOf(DashDoctorsState())
    val stateDashDoctors: State<DashDoctorsState> = _stateDashDoctors

    private val _stateTopDoctors = mutableStateOf(TopDoctorsState())
    val stateTopDoctors: State<TopDoctorsState> = _stateTopDoctors

    private val _stateSpecialists = mutableStateOf(SpecialistState())
    val stateSpecialists: State<SpecialistState> = _stateSpecialists



    init {
        loadDashDoctors()
        loadPatient()
        loadSpecialists()
        loadTopRatedDoctors()
    }

    private fun loadDashDoctors() {
        getDashDoctorsUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    _stateDashDoctors.value = DashDoctorsState(doctors = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = DashboardState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _stateDashDoctors.value = DashDoctorsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadTopRatedDoctors() {
        getTopRatedDoctors.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    _stateTopDoctors.value = TopDoctorsState(
                        doctors = it.data ?: emptyList(),
                    )

                }

                is Resource.Error -> {
                    _state.value = DashboardState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _stateTopDoctors.value = TopDoctorsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadSpecialists() {
        getSpecialistList.invoke().onEach {
            when (it) {
                is Resource.Success -> {

                    _stateSpecialists.value = SpecialistState(
                        specialists = it.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _state.value = DashboardState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _stateSpecialists.value = SpecialistState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun loadPatient() {
        loadLoggedInPatientUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = DashboardState(
                        patientFirstName = it.data!!.firstName
                    )
                }

                is Resource.Error -> {
                    _state.value = DashboardState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _state.value = DashboardState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun navigateToDoctorDetails(doctorId: Int) {
        val params = listOf<String>( doctorId.toString())
        navigator.navigateTo(Screen.DoctorDetailScreen, params)
    }

    fun navigateToDoctorList(specialistId: Int?) {
        val specialist = specialistId ?: 0
        val toAppointment = false
        val params = listOf<String>( specialist.toString(), toAppointment.toString())

        navigator.navigateTo(Screen.DoctorListScreen, params)
    }


}