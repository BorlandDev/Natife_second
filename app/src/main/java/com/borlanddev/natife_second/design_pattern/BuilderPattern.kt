package com.borlanddev.natife_second.design_pattern

import androidx.room.PrimaryKey

fun main() {
    val user1 = UserDB.Builder()
        .withID("522")
        .withName("Maxim Boyko")
        .withPhone("099-282-5240")
        .withEmail("Minsk1115596@gmail.com")
        .withLocation("Mariupol")
        .withAge("24")
        .build()

    // DSL
    val user2 = UserDB.Builder().userBuilder {
        id = "522"
        name = "Max"
        phone = "55-33-22"
        email = "myEmail.com"
        location = "Somewhere"
        age = "19"
    }
}


data class UserDB (
    @PrimaryKey val id: String?,
    val name: String,
    val phone: String,
    val email: String,
    val location: String,
    val picture: String,
    val age: String
) {
    class Builder(
        var id: String? = "",
        var name: String? = "",
        var phone: String? = "",
        var email: String? = "",
        var location: String? = "",
        var picture: String? = "https:testPhotoUser/large",
        var age: String? = ""
    ) {
        fun withID(v: String) = apply { id = v }
        fun withName(v: String) = apply { name = v }
        fun withPhone(v: String) = apply { phone = v }
        fun withEmail(v: String) = apply { email = v }
        fun withLocation(v: String) = apply { location = v }
        fun withAge(v: String) = apply { age = v }

        fun build() =
            Builder(
                id, name, phone,
                email, location, picture, age
            )

        fun userBuilder(init: Builder.() -> Unit) = Builder()
            .apply(init)
            .build()
    }
}




