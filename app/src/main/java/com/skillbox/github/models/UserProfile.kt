package com.skillbox.github.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserProfile(
    val id: Int,
    val login: String,
    @Json(name = "avatar_url")
    val avatar: String?,
    val location: String?
) : Parcelable
