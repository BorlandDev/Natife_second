package com.borlanddev.natife_second.design_pattern

import androidx.room.PrimaryKey

fun main() {
    val user1 = UserDB().Builder()
        .withID("522")
        .withName("Maxim Boyko")
        .withPhone("099-282-5240")
        .withEmail("Minsk1115596@gmail.com")
        .withLocation("Mariupol")
        .withAge("24")
        .build()

    val user2 = UserDB().Builder().userBuilder {
        withID("522")
        withName("Max")
        withPhone("55-33-22")
        withEmail("myEmail.com")
        withLocation("Somewhere")
        withAge("19")
    }
}


data class UserDB(
    @PrimaryKey var id: String = "",
    var name: String = "",
    var phone: String = "",
    var email: String = "",
    var location: String = "",
    var picture: String = "",
    var age: String = ""
) {

    inner class Builder() {
        fun withID(v: String) = apply { id = v }
        fun withName(v: String) = apply { name = v }
        fun withPhone(v: String) = apply { phone = v }
        fun withEmail(v: String) = apply { email = v }
        fun withLocation(v: String) = apply { location = v }
        fun withAge(v: String) = apply { age = v }

        fun build() =
            UserDB(
                id, name, phone,
                email, location, picture, age
            )

        fun userBuilder(init: UserDB.Builder.() -> Unit) = Builder()
            .apply(init)
            .build()
    }
}




