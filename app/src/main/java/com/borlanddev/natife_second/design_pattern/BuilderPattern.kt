package com.borlanddev.natife_second.design_pattern

/*

fun main() {
    val user1 = UserDB.Builder()
        .id("522")
        .name("Maxim Boyko")
        .phone("099-282-5240")
        .email("Minsk1115596@gmail.com")
        .location("Mariupol")
        .age("24")
        .build()

    val user2 = UserDB.Builder().userBuilder {
        id("522")
        name("Max")
        phone("55-33-22")
        email("myEmail.com")
        location("Somewhere")
        age("19")
    }

    println(user1)
    println(user2)
}


data class UserDB(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val location: String,
    val age: String,
    val company: String
) {

    class Builder {
        private var id: String = ""
        private var name: String = ""
        private var phone: String = ""
        private var email: String = ""
        private var location: String = ""
        private var age: String = ""
        private var company = "Natife"

        fun id(v: String) = apply { id = v }
        fun name(v: String) = apply { name = v }
        fun phone(v: String) = apply { phone = v }
        fun email(v: String) = apply { email = v }
        fun location(v: String) = apply { location = v }
        fun age(v: String) = apply { age = v }

        fun build() = UserDB(
            id = id,
            name = name,
            phone = phone,
            email = email,
            location = location,
            age = age,
            company = company
        )

        fun userBuilder(init: Builder.() -> Unit) = Builder()
            .apply(init)
            .build()
    }
}



 */


