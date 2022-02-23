package com.sylvia.h_medi.presentation.ui.register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.common.Constants.TAG
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.common.utils.Navigator
import com.sylvia.h_medi.domain.model.Login
import com.sylvia.h_medi.domain.model.Patient
import com.sylvia.h_medi.domain.use_case.patient.AddLoggedInPatientUseCase
import com.sylvia.h_medi.domain.use_case.patient.RegisterPatientUseCase
import com.sylvia.h_medi.presentation.Screen
import com.sylvia.h_medi.presentation.ui.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerPatientUseCase: RegisterPatientUseCase,
    private val addLoggedInPatientUseCase: AddLoggedInPatientUseCase,
    private val navigator: Navigator
): ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    val phoneNumberText  = mutableStateOf("")
    val firstNameText  = mutableStateOf("")
    val lastNameText  = mutableStateOf("")
    val passwordText  = mutableStateOf("")
    val confirmPasswordText  = mutableStateOf("")
    val genderText  = mutableStateOf("")


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

                    val result: Patient? = it.data

                    if (result !=  null){
                        addPatient(result)
                    } else {
                        _state.value = RegisterState(validateError = "An unexpected error occurred while creating your account. Please try again later")
                    }

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


    private fun addPatient(patient: Patient) {
        addLoggedInPatientUseCase.invoke(patient = patient).onEach {
            when (it) {
                is Resource.Success -> {
                    navigator.navigateTo(Screen.HomeScreen)
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

    fun onRegisterButtonClicked() {

        state.value.validateError = ""

        if (firstNameText.value.isBlank()){
            _state.value = RegisterState(validateError = "Please enter your first name.")
            return
        }

        if (lastNameText.value.isBlank()){
            _state.value = RegisterState(validateError = "Please enter your last name.")
            return
        }

        if (phoneNumberText.value.isBlank()){
            _state.value = RegisterState(validateError = "Please enter your phone number.")
        }

        if (genderText.value.isBlank()){
            _state.value = RegisterState(validateError = "Please enter your gender.")
            return
        }

        if (passwordText.value.isBlank()){
            _state.value = RegisterState(validateError = "Please enter your password.")
            return
        }

        if (confirmPasswordText.value.isBlank()){
            _state.value = RegisterState(validateError = "Please confirm your password.")
            return
        }



        if (passwordText.value == confirmPasswordText.value){
            val patient = Patient(
                firstName = firstNameText.value,
                lastName = lastNameText.value,
                gender = genderText.value,
                phoneNumber = phoneNumberText.value,
                password = passwordText.value,
                patientId = null
            )

            registerPatient(patient = patient)

        } else  {
            _state.value = RegisterState(validateError = "Your passwords do not match.")
            return
        }

    }


    fun navigateToLogin(){
        navigator.navigateTo(Screen.LoginScreen)
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