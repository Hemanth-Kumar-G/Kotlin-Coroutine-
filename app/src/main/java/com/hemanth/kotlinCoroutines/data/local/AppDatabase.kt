package com.hemanth.kotlinCoroutines.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hemanth.kotlinCoroutines.data.local.dao.UserDao
import com.hemanth.kotlinCoroutines.data.local.entity.User

@Database(entities = [User::class],version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun userDao(): UserDao

}