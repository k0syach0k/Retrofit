package com.skillbox.github.ui.current_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.models.UserProfile
import com.skillbox.github.utils.SingleLiveEvent
import retrofit2.Call

class CurrentUserViewModel : ViewModel() {
    private val userRepository = CurrentUserRepository()

    private var currentCall: Call<UserProfile>? = null

    private val userLiveData = MutableLiveData<UserProfile>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val exceptionLiveData = SingleLiveEvent<Throwable>()

    val user: LiveData<UserProfile>
        get() = userLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val exception: LiveData<Throwable>
        get() = exceptionLiveData

    fun getUser() {
        isLoadingLiveData.postValue(true)
        currentCall = userRepository.getUser(
            {
                isLoadingLiveData.postValue(false)
                userLiveData.postValue(it)
            },
            {
                isLoadingLiveData.postValue(false)
                exceptionLiveData.postValue(it)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
        currentCall = null
    }
}
