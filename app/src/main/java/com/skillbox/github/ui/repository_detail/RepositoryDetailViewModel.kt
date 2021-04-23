package com.skillbox.github.ui.repository_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.utils.SingleLiveEvent
import retrofit2.Call

class RepositoryDetailViewModel : ViewModel() {
    private val repository = RepositoryDetailRepository()

    private var currentCall: Call<Boolean>? = null

    private val repositoryStarLiveData = MutableLiveData<Boolean>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val exceptionLiveData = SingleLiveEvent<Throwable>()

    val repositoryStar: LiveData<Boolean>
        get() = repositoryStarLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val exception: LiveData<Throwable>
        get() = exceptionLiveData

    fun getStarState(owner: String, repo: String) {
        if (repositoryStar.value == null) {
            isLoadingLiveData.postValue(true)
            currentCall = repository.getStarState(
                owner, repo,
                {
                    isLoadingLiveData.postValue(false)
                    repositoryStarLiveData.postValue(it)
                },
                {
                    isLoadingLiveData.postValue(false)
                    exceptionLiveData.postValue(it)
                }
            )
        }
    }

    fun changeStarState(owner: String, repo: String) {
        if (repositoryStar.value != null) {
            isLoadingLiveData.postValue(true)
            when (repositoryStar.value) {
                true -> repository.unStarRepository(
                    owner, repo,
                    {
                        isLoadingLiveData.postValue(false)
                        repositoryStarLiveData.postValue(false)
                    },
                    {
                        isLoadingLiveData.postValue(false)
                        exceptionLiveData.postValue(it)
                    }
                )
                false -> repository.starRepository(
                    owner, repo,
                    {
                        isLoadingLiveData.postValue(false)
                        repositoryStarLiveData.postValue(true)
                    },
                    {
                        isLoadingLiveData.postValue(false)
                        exceptionLiveData.postValue(it)
                    }
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
        currentCall = null
    }
}
