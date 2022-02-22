package com.sylvia.h_medi.presentation.ui.bookingConfirmation

import androidx.lifecycle.ViewModel
import com.sylvia.h_medi.domain.use_case.appointment.GetAppointmentDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookingConfirmationViewModel @Inject constructor(
    private val getAppointmentDetailUseCase: GetAppointmentDetailUseCase
): ViewModel() {
}