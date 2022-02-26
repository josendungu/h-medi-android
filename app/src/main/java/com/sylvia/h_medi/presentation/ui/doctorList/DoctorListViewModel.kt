package com.sylvia.h_medi.presentation.ui.doctorList

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.Constants.TAG
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.common.utils.Navigator
import com.sylvia.h_medi.domain.model.Doctor
import com.sylvia.h_medi.domain.model.Specialist
import com.sylvia.h_medi.domain.use_case.doctor.GetAllDoctorsListUseCase
import com.sylvia.h_medi.domain.use_case.doctor.GetDoctorListBySpecialization
import com.sylvia.h_medi.domain.use_case.doctor.GetDoctorSearchListUseCase
import com.sylvia.h_medi.domain.use_case.specialist.GetSpecialistDetailsUSeCase
import com.sylvia.h_medi.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DoctorListViewModel @Inject constructor(
    private val getDoctorsListUseCase: GetAllDoctorsListUseCase,
    private val getDoctorListBySpecialization: GetDoctorListBySpecialization,
    private val getSpecialistDetailsUSeCase: GetSpecialistDetailsUSeCase,
    private val getDoctorSearchListUseCase: GetDoctorSearchListUseCase,
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator
): ViewModel() {

    private val _state = mutableStateOf(DoctorListState())
    val state: State<DoctorListState> = _state

    val searchString = mutableStateOf("")
    val directToAppointment = mutableStateOf(false)
    val specialist = mutableStateOf<Specialist?>(null);

    val doctors = mutableStateOf<List<Doctor>>(emptyList())

    init {

        savedStateHandle.get<String>(Constants.PARAM_TO_APPOINTMENT)?.let {
            directToAppointment.value = it.toBoolean()
        }

        savedStateHandle.get<String>(Constants.PARAM_SPECIALIST_ID)?.let {
            val specialistId = it
            getSpecialistDetails(specialistId.toInt())
        }

    }

    fun removeSpecialist() {
        specialist.value = null
        doctors.value = emptyList()
        handleFetch()
    }


    private fun handleFetch() {
        if (specialist.value == null){
            getAllDoctorList()
        } else {
            getDoctorBySpeciality()
        }
    }

    private fun getSpecialistDetails(specialistId: Int) {
        getSpecialistDetailsUSeCase.invoke(specialistId).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = DoctorListState()
                    specialist.value = it.data
                    handleFetch()
                }

                is Resource.Error -> {
                    _state.value = DoctorListState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _state.value = DoctorListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getDoctorBySpeciality() {

        if (specialist.value != null) {
            getDoctorListBySpecialization.invoke(specialist.value!!.specialistName).onEach {
                when (it) {
                    is Resource.Success -> {
                        _state.value = DoctorListState()
                        doctors.value = it.data ?: emptyList()

                    }

                    is Resource.Error -> {
                        _state.value = DoctorListState(error = it.message ?: "An unexpected error occurred")

                    }

                    is Resource.Loading -> {
                        _state.value = DoctorListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)

        } else  {
            getAllDoctorList()
        }

    }

    private fun getAllDoctorList() {
        getDoctorsListUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = DoctorListState()
                    doctors.value = it.data ?: emptyList()

                }

                is Resource.Error -> {
                    _state.value = DoctorListState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _state.value = DoctorListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun handleSearch() {
        doctors.value = emptyList()


        if (specialist.value ==  null){
            getDoctorSearchListUseCase.invoke(searchString.value).onEach {
                when (it) {
                    is Resource.Success -> {
                        _state.value = DoctorListState()
                        doctors.value = it.data ?: emptyList()

                    }

                    is Resource.Error -> {
                        _state.value = DoctorListState(error = it.message ?: "An unexpected error occurred")

                    }

                    is Resource.Loading -> {
                        _state.value = DoctorListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        } else {
            getDoctorSearchListUseCase.invoke(searchString.value, specialist.value!!.specialistName).onEach {
                when (it) {
                    is Resource.Success -> {
                        _state.value = DoctorListState()
                        doctors.value = it.data ?: emptyList()

                    }

                    is Resource.Error -> {
                        _state.value = DoctorListState(error = it.message ?: "An unexpected error occurred")

                    }

                    is Resource.Loading -> {
                        _state.value = DoctorListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)

        }


    }

    fun handleDoctorClicked(doctor: Doctor) {
        val appointmentId = 0
        if (directToAppointment.value){
            val params = listOf<String>( appointmentId.toString(),doctor.doctorId.toString())
            navigator.navigateTo(Screen.AppointmentDetailScreen, params)
        } else {
            val params = listOf<String>(doctor.doctorId.toString())
            navigator.navigateTo(Screen.DoctorDetailScreen, params)
        }

    }

    fun searchStringChange(string: String) {
        searchString.value = string
    }

}