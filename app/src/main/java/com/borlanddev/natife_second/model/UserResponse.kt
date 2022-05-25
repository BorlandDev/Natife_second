package com.borlanddev.natife_second.model

import com.google.gson.annotations.SerializedName

data class UserResponse(val results: List<User>?)

data class User(
    val name: Name?,
    val phone: String?,
    val email: String?,
    val location: Location?,
    val picture: Picture?,
    @SerializedName("dob") val age: Age?,
    )

data class Name(
    val title: String,
    val first: String,
    val last: String
)

data class Age(
    val age: Int
    )

data class Location(
    val city: String,
    val state: String,
    val county: String
)

data class Picture(
    val thumbnail: String,
    val large: String
)

