package com.yeditepe.acm43project.week7.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

data class UserRegisterState(
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var gender: String = "",
    var dateOfBirth: String? = null,
)

class UserRegisterViewModel(): ViewModel() {

    private var _uiState = MutableStateFlow<UserRegisterState>(UserRegisterState())
    val uiState = _uiState.asStateFlow()

    fun updateEmal(email: String){
        _uiState.value.email= email
    }

    fun updatePassword(password: String) {
        _uiState.value.password = password

    }

    fun updateConfirmPassword(confirmPassword: String) {
        _uiState.value.confirmPassword = confirmPassword
    }
    fun updateGender(gender: String) {
        _uiState.value.gender = gender
    }
    fun updateDateOfBirth(dateOfBirth: String) {
        _uiState.value.dateOfBirth = dateOfBirth
    }





}