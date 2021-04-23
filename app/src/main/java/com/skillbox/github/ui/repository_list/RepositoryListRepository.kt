package com.skillbox.github.ui.repository_list

import android.util.Log
import com.skillbox.github.network.Networking
import com.skillbox.github.models.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryListRepository {
    fun getRepositoryList(onSuccess: (listRepository: List<Repository>) -> Unit, onError: (e: Throwable) -> Unit): Call<List<Repository>> {
        return Networking.githubApi.getUserRepositories().apply {
            enqueue(object : Callback<List<Repository>> {
                override fun onResponse(
                    call: Call<List<Repository>>,
                    response: Response<List<Repository>>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            onSuccess(response.body()!!)
                        } else {
                            onError(Error("Server return null"))
                        }
                    } else {
                        onError(Error("Server response is unsuccessful"))
                    }
                }

                override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                    Log.e("Server", "request repository error = ${t.message}", t)
                    onError(t)
                }
            })
        }
    }
}
