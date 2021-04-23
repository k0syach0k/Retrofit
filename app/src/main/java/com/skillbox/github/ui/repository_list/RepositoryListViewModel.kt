package com.skillbox.github.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.models.Repository
import com.skillbox.github.utils.SingleLiveEvent
import retrofit2.Call

class RepositoryListViewModel : ViewModel() {
    private val repository = RepositoryListRepository()

    private var currentCall: Call<List<Repository>>? = null

    private val listRepositoryLiveData = MutableLiveData<List<Repository>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val exceptionLiveData = SingleLiveEvent<Throwable>()

    val listRepository: LiveData<List<Repository>>
        get() = listRepositoryLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val exception: LiveData<Throwable>
        get() = exceptionLiveData

    fun getRepositoryList() {
        if (listRepository.value == null) {
            isLoadingLiveData.postValue(true)
            currentCall = repository.getRepositoryList(
                {
                    isLoadingLiveData.postValue(false)
                    listRepositoryLiveData.postValue(it)
                },
                {
                    isLoadingLiveData.postValue(false)
                    exceptionLiveData.postValue(it)
                }
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
        currentCall = null
    }
}
