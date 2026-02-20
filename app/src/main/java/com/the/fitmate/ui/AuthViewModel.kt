package com.the.fitmate.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.the.fitmate.domain.LoginUseCase
import com.the.fitmate.domain.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val regisUseCase: RegisterUseCase,
    private val loginUseCase:LoginUseCase
) : ViewModel() {

    var _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess

    var _registerSuccess = MutableStateFlow(false)
    val registerSuccess: StateFlow<Boolean> = _registerSuccess

    private val _loginError = MutableStateFlow("")
    val loginError: StateFlow<String> = _loginError

    private val _registerError = MutableStateFlow("")
    val registerError: StateFlow<String> = _registerError

    private val _resetResult = MutableStateFlow<String?>(null)
    val resetResult = _resetResult.asStateFlow()



    fun register(email: String, password: String) {
        viewModelScope.launch {
            if (email.isBlank() || password.isBlank()) {
                _registerError.value = "Email and password is reqired"
                _registerSuccess.value = false
                return@launch
            }
            val ok = regisUseCase(email.trim(), password)
            if (ok) {
                _registerSuccess.value = true
                _registerError.value = ""
            } else {
                _registerSuccess.value = false
                _registerError.value = "Registration error"
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {

            if (email.isBlank() || password.isBlank()) {
                _loginError.value = "Email and password is reqired"
                _loginSuccess.value = false
                return@launch
            }

            val user = loginUseCase(email.trim(), password)
            val ok = user != null
            _loginSuccess.value = ok
            _loginError.value = if (ok) "" else "Anvelid email or password"
        }
        }

    fun resetPassword(email: String) {
        if (email.isBlank()) {
            _resetResult.value = "Email is required"
            return
        }

        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnSuccessListener {
                _resetResult.value = "success"
            }
            .addOnFailureListener {
                _resetResult.value = it.message ?: "Something went wrong"
            }
    }
    }
