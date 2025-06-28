package com.graduationproject.lungcancerapp.data.repository.user


import com.graduationproject.lungcancerapp.common.utils.Resource
import com.graduationproject.lungcancerapp.data.model.User
import com.graduationproject.lungcancerapp.data.network.firebase.FirebaseService
import javax.inject.Inject

class UserRepoImp @Inject constructor(
    private val firebaseService: FirebaseService
)
: UserRepo {
    override suspend fun getUserInfo(): User? {
        return firebaseService.getUserInfo()
    }

    override suspend fun updateUserInfo(user: User): Resource<Unit> {
        return try {
            firebaseService.updateUserInfo(user)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

}