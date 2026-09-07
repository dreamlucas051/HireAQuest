package com.example

import android.app.Application
import androidx.room.Room
import com.example.data.AppDatabase
import com.example.data.QuestRepository

class HireaquestApplication : Application() {
    val database by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "hireaquest_database"
        ).build()
    }
    val repository by lazy { QuestRepository(database.questDao()) }
}
