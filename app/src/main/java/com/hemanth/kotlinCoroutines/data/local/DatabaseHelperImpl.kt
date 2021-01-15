package com.hemanth.kotlinCoroutines.data.local

import com.hemanth.kotlinCoroutines.data.local.entity.User


class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getUsers(): List<User> = appDatabase.userDao().getAll()

    override suspend fun insertAll(users: List<User>) = appDatabase.userDao().insertAll(users)

}