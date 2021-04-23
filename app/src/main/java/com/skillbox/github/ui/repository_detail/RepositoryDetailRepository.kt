package com.skillbox.github.ui.repository_detail

import android.util.Log
import com.skillbox.github.network.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryDetailRepository {
    fun getStarState(owner: String, repo: String, onSuccess: (starState: Boolean) -> Unit, onError: (e: Throwable) -> Unit): Call<Boolean> {
        return Networking.githubApi.getStarState(owner, repo).apply {
            enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    when (response.code()) {
                        404 -> onSuccess(false)
                        403 -> onError(Error("Forbidden"))
                        401 -> onError(Error("Unauthorized"))
                        304 -> onError(Error("Not Modified"))
                        204 -> onSuccess(true)
                        else -> onError(Error("Unknown error"))
                    }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.e("Server", "request star error = ${t.message}", t)
                    onError(t)
                }
            }
            )
        }
    }

    fun starRepository(owner: String, repo: String, onSuccess: (success: Boolean) -> Unit, onError: (e: Throwable) -> Unit): Call<Boolean> {
        return Networking.githubApi.starRepository(owner, repo).apply {
            enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    responseProcessing(response.code(), onSuccess, onError)
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.e("Server", "star error = ${t.message}", t)
                    onError(t)
                }
            }
            )
        }
    }

    fun unStarRepository(owner: String, repo: String, onSuccess: (success: Boolean) -> Unit, onError: (e: Throwable) -> Unit): Call<Boolean> {
        return Networking.githubApi.unStarRepository(owner, repo).apply {
            enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    responseProcessing(response.code(), onSuccess, onError)
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.e("Server", "unstar error = ${t.message}", t)
                    onError(t)
                }
            }
            )
        }
    }

    private fun responseProcessing(responseCode: Int, onSuccess: (success: Boolean) -> Unit, onError: (e: Throwable) -> Unit){
        when (responseCode) {
            404 -> onError(Error("Not found"))
            403 -> onError(Error("Forbidden"))
            401 -> onError(Error("Unauthorized"))
            304 -> onError(Error("Not Modified"))
            204 -> onSuccess(true)
            else -> onError(Error("Unknown error"))
        }
    }
}
