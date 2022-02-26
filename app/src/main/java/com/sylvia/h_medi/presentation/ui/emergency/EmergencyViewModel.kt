package com.sylvia.h_medi.presentation.ui.emergency

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.domain.model.Emergency
import com.sylvia.h_medi.domain.use_case.emergency.AddEmergencyUseCase
import com.sylvia.h_medi.domain.use_case.patient.LoadLoggedInPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EmergencyViewModel @Inject constructor(
    private val addEmergencyUseCase: AddEmergencyUseCase,
    private val loadLoggedInPatientUseCase: LoadLoggedInPatientUseCase
): ViewModel() {

    private val _state = mutableStateOf(EmergencyState())
    val state: State<EmergencyState> = _state

    val emergencyTypeText = mutableStateOf("")
    val emergencyDescriptionText = mutableStateOf("")

    private var patientId = 0

    init {
        loadPatient()
    }

    fun handleSubmitClicked(){
        _state.value = EmergencyState()
        if (emergencyTypeText.value.isBlank()){
            _state.value = EmergencyState(validateError = "Please enter emergency type.")
            return
        }

        if (emergencyDescriptionText.value.isBlank()){
            _state.value = EmergencyState(validateError = "Please enter the description.")
            return
        }

        addEmergency()
    }

    private fun addEmergency() {

        val emergency = Emergency(
            emergencyType = emergencyTypeText.value,
            description = emergencyDescriptionText.value,
            patientId = patientId
        )

        addEmergencyUseCase.invoke(emergency).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = EmergencyState(validateError = "We have received you emergency and currently working on it.")
                    emergencyDescriptionText.value = ""
                    emergencyTypeText.value = ""
                }

                is Resource.Error -> {
                    _state.value = EmergencyState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _state.value = EmergencyState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }


    private fun loadPatient() {
        loadLoggedInPatientUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = EmergencyState()
                    patientId = it.data!!.patientId!!
                }

                is Resource.Error -> {
                    _state.value = EmergencyState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _state.value = EmergencyState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setType(type: String) {
        emergencyTypeText.value = type
    }

    fun setDescription(description: String) {
        emergencyDescriptionText.value = description
    }


}