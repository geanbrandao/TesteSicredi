package com.geanbrandao.testesicredi.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class People(
    val eventId: Int,
    val name: String,
    val email: String,
)