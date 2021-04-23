package com.skillbox.github.ui.current_user

import android.util.Log
import com.skillbox.github.network.Networking
import com.skillbox.github.models.UserProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Error

class CurrentUserRepository {
    fun getUser(onSuccess: (userProfile: UserProfile) -> Unit, onError: (e: Throwable) -> Unit): Call<UserProfile> {
        return Networking.githubApi.getUserInfo().apply {
            enqueue(object : Callback<UserProfile> {
                override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
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

                override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                    Log.e("Server", "request user error = ${t.message}", t)
                    onError(t)
                }
            })
        }
    }
}
