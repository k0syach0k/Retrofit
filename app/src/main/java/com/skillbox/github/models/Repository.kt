package com.skillbox.github.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Repository(
    val id: Int,
    val name: String,
    val description: String?,
    val owner: UserProfile
) : Parcelable
