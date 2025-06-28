package com.graduationproject.lungcancerapp.ui.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduationproject.lungcancerapp.data.repository.user.UserRepo
import com.graduationproject.lungcancerapp.common.helpers.NetworkHelper
import com.graduationproject.lungcancerapp.common.utils.Event
import com.graduationproject.lungcancerapp.common.utils.Resource
import com.graduationproject.lungcancerapp.data.model.ProfileUiState
import com.graduationproject.lungcancerapp.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _userState = MutableStateFlow(ProfileUiState())
    val userState: StateFlow<ProfileUiState> = _userState.asStateFlow()

    private val _updateUserInfoState = MutableSharedFlow<Event<Resource<Unit>>>()
    val updateUserInfoState: SharedFlow<Event<Resource<Unit>>> = _updateUserInfoState.asSharedFlow()

    init {
        viewModelScope.launch {
            loadUserInfo()
        }
    }

    private suspend fun loadUserInfo() {
        _userState.update { it.copy(isLoading = true) }
        val user = userRepo.getUserInfo()
        _userState.update {
            it.copy(user = user ?: User(), isLoading = false)
        }


    }

    fun onChangeFirstName(value: String) {
        _userState.update {
            it.copy(user = it.user.copy(firstName = value))
        }
    }

    fun onChangeLastName(value: String) {
        _userState.update {
            it.copy(user = it.user.copy(lastName = value))
        }
    }

    fun onChangeLocation(value: String) {
        _userState.update {
            it.copy(user = it.user.copy(location = value))
        }
    }

    fun onChangeEmail(value: String) {
        _userState.update {
            it.copy(user = it.user.copy(email = value))
        }
    }

    fun onChangePhone(value: String) {
        _userState.update {
            it.copy(user = it.user.copy(phoneNumber = value))
        }
    }

    fun saveUserInformation() {
        viewModelScope.launch {
            if (!networkHelper.isConnected())
                _updateUserInfoState.emit(Event(Resource.Error("No internet connection")))
            _updateUserInfoState.emit(Event(Resource.Loading()))
            val result = userRepo.updateUserInfo(_userState.value.user)
            _updateUserInfoState.emit(Event(result))
        }
    }
}
