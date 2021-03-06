package com.borlanddev.natife_second.model

import com.google.gson.annotations.SerializedName

data class UserResponse(val results: List<User>?)

data class User(
    @SerializedName("login") val id: Id?,
    val name: Name?,
    val phone: String?,
    val email: String?,
    val location: Location?,
    val picture: Picture?,
    @SerializedName("dob") val age: Age?,
)

data class Id(
    val uuid: String
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
    val country: String,
    val state: String,
    val city: String
)

data class Picture(
    val large: String
)

