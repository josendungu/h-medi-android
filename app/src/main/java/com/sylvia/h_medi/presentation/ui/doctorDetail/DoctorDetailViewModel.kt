package com.sylvia.h_medi.presentation.ui.doctorDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.domain.use_case.doctor.GetDoctorDetailUseCase
import com.sylvia.h_medi.presentation.ui.doctorList.DoctorListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DoctorDetailViewModel @Inject constructor(
    private val getDoctorDetailUseCase: GetDoctorDetailUseCase
): ViewModel() {

    private val _state = mutableStateOf(DoctorDetailState())
    val state: State<DoctorDetailState> = _state

    init {
        getDoctorDetails()
    }

    private fun getDoctorDetails() {
        getDoctorDetailUseCase.invoke(3).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = DoctorDetailState(doctor = it.data)
                }

                is Resource.Error -> {
                    _state.value = DoctorDetailState(error = it.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = DoctorDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}