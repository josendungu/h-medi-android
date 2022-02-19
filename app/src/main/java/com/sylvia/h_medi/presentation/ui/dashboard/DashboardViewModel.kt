package com.sylvia.h_medi.presentation.ui.dashboard

import androidx.lifecycle.ViewModel
import com.sylvia.h_medi.domain.use_case.doctor.GetDoctorsListUseCase
import com.sylvia.h_medi.domain.use_case.specialist.GetSpecialistListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDoctorsListUseCase: GetDoctorsListUseCase,
    private val getSpecialistList: GetSpecialistListUseCase
):ViewModel() {




}