package com.sylvia.h_medi.presentation.ui.profile

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.common.Constants.TAG
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.domain.model.Patient
import com.sylvia.h_medi.domain.model.PatientUpdate
import com.sylvia.h_medi.domain.use_case.patient.AddLoggedInPatientUseCase
import com.sylvia.h_medi.domain.use_case.patient.LoadLoggedInPatientUseCase
import com.sylvia.h_medi.domain.use_case.patient.UpdatePatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val loadLoggedInPatientUseCase: LoadLoggedInPatientUseCase,
    private val updatePatientUseCase: UpdatePatientUseCase,
    private val addLoggedInPatientUseCase: AddLoggedInPatientUseCase
): ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    val phoneNumberText  = mutableStateOf("")
    val firstNameText  = mutableStateOf("")
    val lastNameText  = mutableStateOf("")
    val passwordText  = mutableStateOf("")
    val confirmPasswordText  = mutableStateOf("")
    val genderText  = mutableStateOf("")


    private val currentPatientDetails =  mutableStateOf<Patient?>(null)


    init {
        loadPatient()
    }


    private fun loadPatient() {
        loadLoggedInPatientUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    setTextFieldDetails(it.data!!)
                    updatePatientState(it.data)
                    _state.value = ProfileState()
                }

                is Resource.Error -> {
                    _state.value = ProfileState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _state.value = ProfileState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun setTextFieldDetails(patient: Patient) {
        firstNameText.value = patient.firstName
        lastNameText.value = patient.lastName
        phoneNumberText.value = patient.phoneNumber
        genderText.value = patient.gender
    }

    private fun updatePatientState(patient: Patient) {
        currentPatientDetails.value = patient
    }


    fun onUpdateButtonClicked() {

        _state.value = ProfileState()

        if (firstNameText.value.isBlank()){
            _state.value = ProfileState(validateError = "Please enter your first name.")
            return
        }

        if (lastNameText.value.isBlank()){
            _state.value = ProfileState(validateError = "Please enter your last name.")
            return
        }

        if (phoneNumberText.value.isBlank()){
            _state.value = ProfileState(validateError = "Please enter your phone number.")
        }

        if (genderText.value.isBlank()){
            _state.value = ProfileState(validateError = "Please enter your gender.")
            return
        }

        if (confirmPasswordText.value.isNotBlank()){
            if (passwordText.value.isBlank()){
                _state.value = ProfileState(validateError = "Please enter your password.")
                return
            }
        }

        if (passwordText.value.isNotBlank()){
            if (confirmPasswordText.value.isBlank()){
                _state.value = ProfileState(validateError = "Please confirm your password.")
                return
            } else {
                if (passwordText.value != confirmPasswordText.value){
                    _state.value = ProfileState(validateError = "Your passwords do not match.")
                    return
                }
            }
        }

        getUpdateChanges()


    }


    private fun getCurrentPatient(): Patient {

        return currentPatientDetails.value!!

    }


    private fun getUpdateChanges() {

        var changesMade = false

        val currentPatient = getCurrentPatient()
        val updatePatient: PatientUpdate = PatientUpdate()

        if (firstNameText.value != currentPatient.firstName){
            updatePatient.firstName = firstNameText.value
            changesMade = true
        }

        if (lastNameText.value != currentPatient.lastName){
            updatePatient.lastName = lastNameText.value
            changesMade = true

        }

        if (genderText.value != currentPatient.gender){
            updatePatient.gender = genderText.value
            changesMade = true
        }

        if (phoneNumberText.value != currentPatient.phoneNumber){
            updatePatient.phoneNumber = phoneNumberText.value
            changesMade = true
        }

        if (passwordText.value.isNotBlank()){
            updatePatient.password = passwordText.value
            changesMade = true
        }
        updatePatient.patientId = currentPatient.patientId


        if (changesMade){
            requestUpdatePatient(updatePatient)
        } else {
            _state.value = ProfileState(validateError = "You have not made any changes.")
        }

    }

    private fun requestUpdatePatient(patient: PatientUpdate) {

        updatePatientUseCase.invoke(patient).onEach {
            when (it) {
                is Resource.Success -> {
                    updatePatientState(it.data!!)
                    savePatientDetails(it.data)
                    _state.value = ProfileState()
                }

                is Resource.Error -> {
                    _state.value = ProfileState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _state.value = ProfileState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun savePatientDetails(patient: Patient) {
        addLoggedInPatientUseCase.invoke(patient = patient).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = ProfileState(updated = true)
                }

                is Resource.Error -> {
                    _state.value = ProfileState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _state.value = ProfileState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun setPhoneNumber(phoneNumber: String) {
        phoneNumberText.value = phoneNumber
    }

    fun setPassword(password: String) {
        passwordText.value =  password
    }

    fun setConfirmPassword(confirmPassword: String) {
        confirmPasswordText.value =  confirmPassword
    }

    fun setFirstName(firstName: String) {
        firstNameText.value =  firstName
    }

    fun setGender(gender: String) {
        genderText.value =  gender
    }

    fun setLastName(lastName: String) {
        lastNameText.value =  lastName
    }

}