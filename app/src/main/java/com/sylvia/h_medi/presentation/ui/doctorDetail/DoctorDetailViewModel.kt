package com.sylvia.h_medi.presentation.ui.doctorDetail

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.HMediApplication
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.common.utils.Navigator
import com.sylvia.h_medi.domain.model.Doctor
import com.sylvia.h_medi.domain.use_case.doctor.GetDoctorDetailUseCase
import com.sylvia.h_medi.presentation.Screen
import com.sylvia.h_medi.presentation.ui.doctorList.DoctorListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DoctorDetailViewModel @Inject constructor(
    private val getDoctorDetailUseCase: GetDoctorDetailUseCase,
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
    private val hMediApplication: HMediApplication
): ViewModel() {

    private val _state = mutableStateOf(DoctorDetailState())
    val state: State<DoctorDetailState> = _state

    val currentDoctor = mutableStateOf<Doctor?>(null)

    init {

        savedStateHandle.get<String>(Constants.PARAM_DOCTOR_ID)?.let {
            getDoctorDetails(it.toInt())
        }

    }

    private fun getDoctorDetails(doctorId: Int) {
        getDoctorDetailUseCase.invoke(doctorId).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = DoctorDetailState()
                    currentDoctor.value = it.data
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


    fun makeAppointment() {
        val appointmentId = 0
        val params = listOf<String>( appointmentId.toString(),currentDoctor.value!!.doctorId.toString())
        navigator.navigateTo(Screen.AppointmentDetailScreen, params)
    }

    fun emailToClipBoard()  {
        val clipboardManager = hMediApplication.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", currentDoctor.value!!.email)
        clipboardManager.setPrimaryClip(clipData)

        Toast.makeText(hMediApplication.applicationContext,"Email copied to clipboard",Toast.LENGTH_LONG).show()
    }

    fun phoneToClipBoard()  {
        val clipboardManager = hMediApplication.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("phone", currentDoctor.value!!.phoneNumber)
        clipboardManager.setPrimaryClip(clipData)

        Toast.makeText(hMediApplication.applicationContext,"Phone number copied to clipboard",Toast.LENGTH_LONG).show()

    }

}