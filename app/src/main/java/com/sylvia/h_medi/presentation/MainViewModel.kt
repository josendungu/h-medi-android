package com.sylvia.h_medi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.common.utils.Navigator
import com.sylvia.h_medi.domain.use_case.doctor.LoadDoctorsUseCase
import com.sylvia.h_medi.domain.use_case.patient.LoadLoggedInPatientUseCase
import com.sylvia.h_medi.domain.use_case.specialist.LoadSpecialistsUseCase
import com.sylvia.h_medi.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadDoctorsUseCase: LoadDoctorsUseCase,
    private val loadSpecialistsUseCase: LoadSpecialistsUseCase,
    private val loadLoggedInPatientUseCase: LoadLoggedInPatientUseCase,
    private val navigator: Navigator
): ViewModel() {

    private val _isLoading= MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadDoctors()
    }

    private fun loadDoctors() {
        loadDoctorsUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    loadSpecialist()
                }

                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }

            }
        }.launchIn(viewModelScope)
    }


    private fun loadSpecialist() {
        loadSpecialistsUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {

                    loadPatientDetails()
                }

                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadPatientDetails() {
        loadLoggedInPatientUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    val patient = it.data

                    if (patient != null){
                        navigator.navigateTo(Screen.HomeScreen)
                        _isLoading.value = false
                    } else {
                        navigator.navigateTo(Screen.LoginScreen)
                        _isLoading.value = false
                    }
                }

                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }
            }

        }.launchIn(viewModelScope)
    }

}