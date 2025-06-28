package com.graduationproject.lungcancerapp.data.repository.auth

import com.graduationproject.lungcancerapp.common.utils.LoginResult
import com.graduationproject.lungcancerapp.data.model.User
import com.graduationproject.lungcancerapp.data.network.firebase.FirebaseService
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val firebaseService: FirebaseService
) : AuthRepo {
    override suspend fun register(email: String, password: String) {
        firebaseService.registerUser(email, password)
    }

    override suspend fun login(email: String, password: String): LoginResult {
        return firebaseService.loginUser(email, password)
    }



    override suspend fun saveUserData(user: User) {
       firebaseService.saveUserInfo(user)
    }

    override suspend fun logout() {
        firebaseService.logoutUser()
    }

}