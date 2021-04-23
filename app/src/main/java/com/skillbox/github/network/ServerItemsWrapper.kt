package com.skillbox.github.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerItemsWrapper <T>(
    val items: List<T>
)
