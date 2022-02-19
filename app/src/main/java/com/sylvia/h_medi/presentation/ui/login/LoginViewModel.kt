package com.sylvia.h_medi.presentation.ui.login

import com.sylvia.h_medi.domain.use_case.patient.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) {




}