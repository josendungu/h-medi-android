package com.sylvia.h_medi.presentation.ui.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.common.utils.Navigator
import com.sylvia.h_medi.domain.model.Login
import com.sylvia.h_medi.domain.use_case.patient.LoginUseCase
import com.sylvia.h_medi.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val navigator: Navigator
): ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    val phoneNumberText  = mutableStateOf("")
    val passwordText  = mutableStateOf("")

    private fun requestLogin(phoneNumber: String, password: String) {

        loginUseCase.invoke(phoneNumber, password).onEach {

            when (it) {
                is Resource.Success -> {
                    checkLoginResponse(it.data)
                }

                is Resource.Error -> {
                    _state.value = LoginState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _state.value = LoginState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)

    }

    private fun checkLoginResponse(login: Login?) {
        if (login != null){

            if (login.success){
                handleSuccess()
            } else {
                _state.value = LoginState(errorResponse = login.errorMessage)
            }

        } else {
            _state.value = LoginState(errorResponse = "An unexpected error occurred")
        }
    }

    private fun handleSuccess() {
        //save the patient details and navigate to Home Screen
    }

    fun navigateToRegister(){
        navigator.navigateTo(Screen.RegisterScreen)
    }

    fun setPhoneNumber(phoneNumber: String) {
        phoneNumberText.value = phoneNumber
    }

    fun setPassword(password: String) {
        passwordText.value =  password
    }

}