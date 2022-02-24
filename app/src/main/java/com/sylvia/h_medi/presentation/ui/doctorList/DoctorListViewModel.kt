package com.sylvia.h_medi.presentation.ui.doctorList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.domain.use_case.doctor.GetAllDoctorsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DoctorListViewModel @Inject constructor(
    private val getDoctorsListUseCase: GetAllDoctorsListUseCase
): ViewModel() {

    private val _state = mutableStateOf(DoctorListState())
    val state: State<DoctorListState> = _state

    init {
        getDoctorList()
    }

    private fun getDoctorList() {
        getDoctorsListUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = DoctorListState(doctors = it.data ?: emptyList())

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