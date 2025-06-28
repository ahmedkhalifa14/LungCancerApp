package com.graduationproject.lungcancerapp.data.repository.user

import com.graduationproject.lungcancerapp.common.utils.Resource
import com.graduationproject.lungcancerapp.data.model.User

interface UserRepo {
    suspend fun getUserInfo():User?
    suspend fun updateUserInfo(user: User):Resource<Unit>
}