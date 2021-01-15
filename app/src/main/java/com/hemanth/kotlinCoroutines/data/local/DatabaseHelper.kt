package com.hemanth.kotlinCoroutines.data.local

import com.hemanth.kotlinCoroutines.data.local.entity.User


interface DatabaseHelper {

    suspend fun getUsers(): List<User>

    suspend fun insertAll(users: List<User>)

}