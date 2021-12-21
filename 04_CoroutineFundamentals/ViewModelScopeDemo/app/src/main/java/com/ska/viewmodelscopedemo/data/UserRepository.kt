package com.ska.viewmodelscopedemo.data

import kotlinx.coroutines.delay

class UserRepository {

    suspend fun getUsers(): List<User>{
        delay(5000)
        return listOf(User(1, "Sinan"), User(2, "John"))
    }
}