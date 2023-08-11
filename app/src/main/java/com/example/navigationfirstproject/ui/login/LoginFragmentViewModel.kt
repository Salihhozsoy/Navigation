package com.example.navigationfirstproject.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationfirstproject.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginFragmentViewModel : ViewModel() {

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    var loginState: StateFlow<LoginState> = _loginState


    fun login(emailText: String, passwordText: String, appDatabase: AppDatabase) {
        viewModelScope.launch {
            kotlin.runCatching {
                appDatabase.userDao().getUser(emailText,passwordText)?.let{
                    _loginState.value= LoginState.Success(it)
                    println("login başarılı")
                }?:kotlin.run {
                    _loginState.value= LoginState.UserNotFound
                    println("kullanıcı bulunamadı")
                }
            }.onFailure {
                _loginState.value= LoginState.Error(it)
                println("giriş başarısız ${it.message}")
            }
        }
    }
}
