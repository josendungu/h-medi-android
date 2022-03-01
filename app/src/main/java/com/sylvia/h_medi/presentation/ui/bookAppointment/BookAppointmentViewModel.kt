package com.sylvia.h_medi.presentation.ui.bookAppointment


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvia.h_medi.HMediApplication
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.Constants.TAG
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.common.utils.DateUtils
import com.sylvia.h_medi.common.utils.Navigator
import com.sylvia.h_medi.domain.model.Appointment
import com.sylvia.h_medi.domain.model.AppointmentUpdate
import com.sylvia.h_medi.domain.model.Doctor
import com.sylvia.h_medi.domain.use_case.appointment.CreateAppointmentUseCase
import com.sylvia.h_medi.domain.use_case.appointment.DeleteAppointmentUseCase
import com.sylvia.h_medi.domain.use_case.appointment.GetAppointmentDetailUseCase
import com.sylvia.h_medi.domain.use_case.appointment.UpdateAppointmentUseCase
import com.sylvia.h_medi.domain.use_case.doctor.GetDoctorDetailUseCase
import com.sylvia.h_medi.domain.use_case.patient.LoadLoggedInPatientUseCase
import com.sylvia.h_medi.presentation.Screen
import com.sylvia.h_medi.presentation.ui.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BookAppointmentViewModel @Inject constructor(
    private val getAppointmentDetailUseCase: GetAppointmentDetailUseCase,
    private val getDoctorDetailUseCase: GetDoctorDetailUseCase,
    savedStateHandle: SavedStateHandle,
    private val createAppointmentUseCase: CreateAppointmentUseCase,
    private val loadLoggedInPatientUseCase: LoadLoggedInPatientUseCase,
    private val updateAppointmentUseCase: UpdateAppointmentUseCase,
    private val navigator: Navigator
) : ViewModel(){

    private val _state = mutableStateOf(BookAppointmentState(isLoading = true))
    val state: State<BookAppointmentState> = _state
    val selectedDoctor = mutableStateOf<Doctor?>(null)
    val appointment = mutableStateOf<Appointment?>(null)
    private var doctorId: Int? = null
    val isUpdate = mutableStateOf(false)
    val setTime = mutableStateOf<LocalTime?>(null)
    val setDate = mutableStateOf<LocalDate?>(null)



    init {

        savedStateHandle.get<String>(Constants.PARAM_DOCTOR_ID)?.let {
            doctorId = it.toInt()
        }

        savedStateHandle.get<String>(Constants.PARAM_APPOINTMENT_ID)?.let {
            val appointmentId = it.toInt()

            if (appointmentId != 0) {
                isUpdate.value = true
                getAppointmentDetails(it.toInt())
            } else {
                getDoctorDetails()
            }
        }

    }

    private fun getAppointmentDetails(appointmentId: Int) {
        getAppointmentDetailUseCase.invoke(appointmentId = appointmentId).onEach {

            when (it) {
                is Resource.Success -> {
                    appointment.value = it.data
                    doctorId = it.data!!.doctor.doctorId
                    selectedDoctor.value = it.data.doctor
                    setDate.value = it.data.date
                    setTime.value = it.data.time
                    _state.value = BookAppointmentState()
                }

                is Resource.Error -> {
                    _state.value =
                        BookAppointmentState(error = it.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = BookAppointmentState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }


    private fun getDoctorDetails() {


        val finalDoctorId = if (doctorId == 0) {
            appointment.value!!.doctor.doctorId
        } else {
            doctorId
        }

        getDoctorDetailUseCase.invoke(finalDoctorId!!).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = BookAppointmentState()
                    selectedDoctor.value = it.data
                }

                is Resource.Error -> {
                    _state.value = BookAppointmentState(
                        error = it.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = BookAppointmentState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)


    }


    fun navigateToHome() {
        navigator.navigateTo(Screen.HomeScreen)
    }

    fun handleButtonClicked() {

        _state.value = BookAppointmentState()

        if (setTime.value == null){
            _state.value = BookAppointmentState(validateError = "Please set time.")
            return
        }

        if (setDate.value == null){
            _state.value = BookAppointmentState(validateError = "Please set date.")
            return
        }

        if (isUpdate.value){
            handleUpdateAppointment()
        } else {
            handleSaveAppointment()
        }
    }

    private fun handleSaveAppointment() {
        loadPatient()
    }

    private fun saveAppointment(patientId: Int) {

        val newAppointment = Appointment(
            time = setTime.value!!,
            date = setDate.value!!,
            doctor = selectedDoctor.value!!,
            appointmentId = 0,
            specialist = selectedDoctor.value!!.specialist
        )

        createAppointmentUseCase.invoke(newAppointment, patientId).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = BookAppointmentState(bookingSuccess = true)
                }

                is Resource.Error -> {
                    _state.value = BookAppointmentState(
                        error = it.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = BookAppointmentState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }



    private fun loadPatient() {
        loadLoggedInPatientUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    it.data?.patientId?.let { it1 -> saveAppointment(it1) }
                }

                is Resource.Error -> {
                    _state.value = BookAppointmentState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _state.value = BookAppointmentState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun handleUpdateAppointment() {

        val appointmentUpdate = AppointmentUpdate(appointmentId = appointment.value!!.appointmentId)
        var update = false

        if (setTime.value != appointment.value!!.time){
            appointmentUpdate.time = setTime.value!!
            update = true
        }

        if (setDate.value != appointment.value!!.date){
            appointmentUpdate.date = setDate.value
            update = true
        }

        if (update){
            updateAppointment(appointmentUpdate)
        } else {
            _state.value = BookAppointmentState(validateError = "No changes have been made")
        }


    }

    private fun updateAppointment(appointmentUpdate: AppointmentUpdate) {
        updateAppointmentUseCase.invoke(appointmentUpdate).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = BookAppointmentState(bookingSuccess = it.data ?: false)
                }

                is Resource.Error -> {
                    _state.value = BookAppointmentState(error = it.message ?: "An unexpected error occurred")

                }

                is Resource.Loading -> {
                    _state.value = BookAppointmentState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }




}