package com.sylvia.h_medi.presentation.ui.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.domain.model.Patient
import com.sylvia.h_medi.domain.use_case.patient.RegisterPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerPatientUseCase: RegisterPatientUseCase
): ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    init {
        val patient = Patient(
            firstName = "Josephat",
            lastName = "Ndungu",
            gender = "male",
            phoneNumber = "0721316767",
            password = "password",
            patientId = null
        )

        state.value.patient = patient

    }

    private fun registerPatient(patient: Patient) {
        registerPatientUseCase.invoke(patient = patient).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = RegisterState(patient = it.data)

                }

                is Resource.Error -> {
                    _state.value = RegisterState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _state.value = RegisterState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}