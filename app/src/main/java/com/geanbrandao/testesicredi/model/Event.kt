package com.geanbrandao.testesicredi.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
class Event(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Float,
    val date: Long,
//    val people: ArrayList<People> = arrayListOf()
): Serializable {
    override fun toString(): String {
        return "$id, $title, $description, $image, $latitude, $longitude, $price, $date"
    }
}