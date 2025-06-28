package com.graduationproject.lungcancerapp.data.repository.auth

import com.graduationproject.lungcancerapp.common.utils.LoginResult
import com.graduationproject.lungcancerapp.data.model.User

interface AuthRepo{
    suspend fun register(email: String, password: String)
    suspend fun login(email: String, password: String): LoginResult
    suspend fun saveUserData(user: User)
    suspend fun logout()
}