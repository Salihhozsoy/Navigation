package com.example.navigationfirstproject.ui.login

import com.example.navigationfirstproject.entity.User

sealed class LoginState{
    object Idle : LoginState()
    object UserNotFound: LoginState()
    class Success(val user: User) : LoginState()
    class Error(val throwable: Throwable) : LoginState()
}
